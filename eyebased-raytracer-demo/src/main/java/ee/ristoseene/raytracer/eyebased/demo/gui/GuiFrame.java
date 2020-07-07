package ee.ristoseene.raytracer.eyebased.demo.gui;

import ee.ristoseene.raytracer.eyebased.demo.gui.listener.ConfigurableMouseListener;
import ee.ristoseene.raytracer.eyebased.demo.rendering.Render;
import ee.ristoseene.raytracer.eyebased.demo.scene.SceneHolder;
import ee.ristoseene.raytracer.eyebased.demo.threading.BackgroundExecutor;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.RenderTarget;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public final class GuiFrame extends JFrame {

    private final GuiMenu menuBar;
    private final GuiPanel contentPane;
    private final SceneHolder sceneHolder;
    private Future<?> pendingRender;

    public GuiFrame() {
        super(ResourceBundle.getBundle("gui").getString("frame.title"));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initializeFrameSize();

        this.sceneHolder = new SceneHolder();
        this.menuBar = new GuiMenu(sceneHolder);
        this.contentPane = new GuiPanel(sceneHolder);
        this.initializeFrameContent();
    }

    private void initializeFrameSize() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension minimumSize = new Dimension(Math.min(screenSize.width, 800), Math.min(screenSize.height, 600));
        final Dimension frameSize = new Dimension(
                Math.max(minimumSize.width, (int) (screenSize.width * 0.75f)),
                Math.max(minimumSize.height, (int) (screenSize.height * 0.8f))
        );

        super.setMinimumSize(minimumSize);
        super.setLocation((screenSize.width - frameSize.width) >> 1, (screenSize.height - frameSize.height) >> 1);
        super.setSize(frameSize);
    }

    private void initializeFrameContent() {
        this.menuBar.getStartRender().addActionListener(e -> startRender());
        this.menuBar.getStartRender().setEnabled(true);

        this.menuBar.getStopRender().addActionListener(e -> stopRender(false));
        this.menuBar.getStopRender().setEnabled(false);

        this.menuBar.getSingleSampling().doClick();
        this.menuBar.getSimpleScenery().doClick();

        this.contentPane.addMouseWheelListener(e -> stopRender(true));
        this.contentPane.addMouseListener(new ConfigurableMouseListener()
                .withMousePressedHandler(e -> stopRender(true)));

        super.setJMenuBar(this.menuBar);
        super.setContentPane(this.contentPane);
    }

    private void startRender() {
        final BufferedImage image = new BufferedImage(contentPane.getWidth(), contentPane.getHeight(), BufferedImage.TYPE_INT_RGB);
        contentPane.setImage(image);

        final Consumer<PixelLocation> renderUpdateListener = pixelLocation -> {
            if (pixelLocation.getX() == 0) contentPane.repaint();
        };
        final Runnable renderFinishListener = () -> {
            stopRender(false);
            contentPane.repaint();
        };

        final RenderTarget renderTarget = new GuiRenderTarget(image, renderUpdateListener);
        final Render render = new Render(renderTarget, sceneHolder, renderFinishListener);
        pendingRender = BackgroundExecutor.EXECUTOR_SERVICE.submit(render);

        changeRenderState(true);
    }

    private void stopRender(final boolean clear) {
        if (pendingRender != null) {
            pendingRender.cancel(true);
            pendingRender = null;

            changeRenderState(false);
        }
        if (clear) {
            contentPane.setImage(null);
        }
    }

    private void changeRenderState(boolean rendering) {
        this.menuBar.getStartRender().setEnabled(!rendering);
        this.menuBar.getStopRender().setEnabled(rendering);
    }

}
