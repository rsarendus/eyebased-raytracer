package ee.ristoseene.raytracer.eyebased.demo.gui;

import ee.ristoseene.raytracer.eyebased.demo.gui.listener.ConfigurableMouseListener;
import ee.ristoseene.raytracer.eyebased.demo.gui.listener.ConfigurableMouseMotionListener;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.impl.RasterizerImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.WireframeRenderers;
import ee.ristoseene.raytracer.eyebased.demo.scene.Camera;
import ee.ristoseene.raytracer.eyebased.demo.scene.SceneHolder;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.mutable.MutableVector2;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

public final class GuiPanel extends JPanel {

    public static boolean DEBUG;

    private final SceneHolder sceneHolder;
    private BufferedImage image;
    private Vector2.AccessibleAndMutable lastPosition;

    GuiPanel(SceneHolder sceneHolder) {
        this.sceneHolder = sceneHolder;
        this.setOpaque(true);

        this.addMouseListener(new ConfigurableMouseListener()
                .withMousePressedHandler(e -> lastPosition = new MutableVector2(e.getX(), e.getY()))
                .withMouseReleasedHandler(e -> { lastPosition = null; repaint(); }));

        this.addMouseMotionListener(new ConfigurableMouseMotionListener()
                .withMouseDraggedHandler(this::handleDragEvent));

        this.addMouseWheelListener(this::handleZoomEvent);
    }

    void setImage(final BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        if (image != null) {
            paintImageAtCenter(graphics, image);
        } else {
            renderWireframe(graphics);
        }
    }

    private void paintImageAtCenter(final Graphics graphics, final BufferedImage image) {
        final int componentWidth = this.getWidth();
        final int componentHeight = this.getHeight();

        final int imageWidth = image.getWidth();
        final int imageHeight = image.getHeight();

        graphics.drawImage(image,
                (componentWidth - imageWidth) >> 1,
                (componentHeight - imageHeight) >> 1,
                imageWidth, imageHeight,
                this
        );
    }

    private void renderWireframe(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final RasterizerImpl rasterizer = new RasterizerImpl(graphics, getWidth(), getHeight());
        final Matrix4x4.Accessible projection = sceneHolder.getCamera().createProjectionMatrix(getWidth(), getHeight());

        graphics2D.setColor(Constants.WIREFRAME_GEOMETRY_COLOR);
        sceneHolder.getGeometry().forEach(object -> WireframeRenderers.GEOMETRY_RENDERER.render(rasterizer, projection, object));

        if (!DEBUG || lastPosition != null) return;

        graphics2D.setColor(Constants.WIREFRAME_DEBUG_COLOR);
        sceneHolder.getGeometry().map(object -> object.compile(Optional.empty()).getAABB())
                .forEach(aabb -> WireframeRenderers.AABB_RENDERER.render(rasterizer, projection, aabb));
    }

    private void handleDragEvent(final MouseEvent mouseEvent) {
        final Camera camera = sceneHolder.getCamera();
        if (camera != null && lastPosition != null) {
            final double multiplier = 5.0 / Math.min(getWidth(), getHeight());
            camera.rotate(
                    (lastPosition.x() - mouseEvent.getX()) * multiplier,
                    (lastPosition.y() - mouseEvent.getY()) * multiplier
            );
            lastPosition.xy(mouseEvent.getX(), mouseEvent.getY());
            repaint();
        }
    }

    private void handleZoomEvent(final MouseWheelEvent mouseWheelEvent) {
        final Camera camera = sceneHolder.getCamera();
        if (camera != null) {
            final boolean alt = mouseWheelEvent.isShiftDown();
            camera.zoom(mouseWheelEvent.getPreciseWheelRotation() * 0.01, alt);
            repaint();
        }
    }

}
