package ee.ristoseene.raytracer.eyebased.demo.gui;

import ee.ristoseene.raytracer.eyebased.demo.scene.SceneHolder;
import ee.ristoseene.raytracer.eyebased.demo.scene.Scenery;
import ee.ristoseene.raytracer.eyebased.demo.scene.interfaces.MeshShading;
import ee.ristoseene.raytracer.eyebased.demo.scene.interfaces.VertexSourcing;
import ee.ristoseene.raytracer.eyebased.demo.scene.scenery.SimpleScenery;
import ee.ristoseene.raytracer.eyebased.demo.scene.scenery.SuzanneScenery;
import ee.ristoseene.raytracer.eyebased.rasterization.processors.SingleSamplePixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.processors.UniformGridMultisamplingPixelProcessor;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class GuiMenu extends JMenuBar {

    private final JMenu renderMenu;

    private final JMenuItem startRender;
    private final JMenuItem stopRender;

    private final JRadioButtonMenuItem singleSampling;
    private final JMenu gridMultisampling;
    private final JRadioButtonMenuItem[] gridMultisamplingItems;

    private final JMenu sceneMenu;
    private final JMenu sceneConfigMenu;
    private final JRadioButtonMenuItem simpleScenery;
    private final JRadioButtonMenuItem suzanneScenery;

    GuiMenu(final SceneHolder sceneHolder) {
        ResourceBundle rb = resourceBundle();

        // Render
        renderMenu = createMenuItem(rb, "menu.render", JMenu::new);
        add(renderMenu);

        startRender = createMenuItem(rb, "menu.render.start-render", JMenuItem::new);
        renderMenu.add(startRender);

        stopRender = createMenuItem(rb, "menu.render.stop-render", JMenuItem::new);
        renderMenu.add(stopRender);

        renderMenu.addSeparator();

        ButtonGroup buttonGroup = new ButtonGroup();

        singleSampling = createMenuItem(rb, "menu.render.single-sampling", JRadioButtonMenuItem::new);
        singleSampling.addActionListener(e -> sceneHolder.setPixelProcessorFactory(SingleSamplePixelProcessor::new));
        buttonGroup.add(renderMenu.add(singleSampling));

        gridMultisampling = createMenuItem(rb, "menu.render.grid-multisampling", JMenu::new);
        renderMenu.add(gridMultisampling);

        gridMultisamplingItems = new JRadioButtonMenuItem[15];

        for (int i = 0; i < gridMultisamplingItems.length; ++i) {
            final int samples = i + 2;

            gridMultisamplingItems[i] = createMenuItem(rb, String.format("%d x %d", samples, samples), JRadioButtonMenuItem::new);
            gridMultisamplingItems[i].addActionListener(e -> sceneHolder.setPixelProcessorFactory(c -> new UniformGridMultisamplingPixelProcessor(c, samples, samples)));
            buttonGroup.add(gridMultisampling.add(gridMultisamplingItems[i]));
        }

        // Scene
        sceneMenu = createMenuItem(rb, "menu.scene", JMenu::new);
        add(sceneMenu);

        buttonGroup = new ButtonGroup();

        simpleScenery = createMenuItem(rb, "menu.scene.simple-scenery", JRadioButtonMenuItem::new);
        simpleScenery.addActionListener(e -> switchScene(sceneHolder, new SimpleScenery()));
        buttonGroup.add(sceneMenu.add(simpleScenery));

        suzanneScenery = createMenuItem(rb, "menu.scene.suzanne-scenery", JRadioButtonMenuItem::new);
        suzanneScenery.addActionListener(e -> switchScene(sceneHolder, new SuzanneScenery()));
        buttonGroup.add(sceneMenu.add(suzanneScenery));

        sceneMenu.addSeparator();
        sceneConfigMenu = createMenuItem(rb, "menu.scene.config", JMenu::new);
        sceneConfigMenu.setEnabled(false);
        sceneMenu.add(sceneConfigMenu);
    }

    private void switchScene(final SceneHolder sceneHolder, final Scenery newScene) {
        sceneHolder.setScenery(newScene);

        sceneConfigMenu.setEnabled(false);
        sceneConfigMenu.removeAll();

        String key = "menu.scene.config";

        if (newScene instanceof MeshShading) {
            final MeshShading meshShading = (MeshShading) newScene;
            addRadioButtonsToMenu(sceneConfigMenu, key + ".mesh-shading", MeshShading.MeshShadingMode.values(), (item, mode) -> {
                item.setSelected(meshShading.getMeshShadingMode().equals(mode));
                item.addActionListener(e -> meshShading.setMeshShadingMode(mode));
            });
        }

        if (newScene instanceof VertexSourcing) {
            final VertexSourcing vertexSourcing = (VertexSourcing) newScene;
            addRadioButtonsToMenu(sceneConfigMenu, key + ".vertex-sourcing", VertexSourcing.VertexSourceMode.values(), (item, mode) -> {
                item.setSelected(vertexSourcing.getVertexSourceMode().equals(mode));
                item.addActionListener(e -> vertexSourcing.setVertexSourceMode(mode));
            });
        }
    }

    private static <T extends JMenuItem> T createMenuItem(ResourceBundle rb, String key, Function<String, T> factory) {
        T menuItem = factory.apply(getStringResource(rb, key + ".title").orElse(key));

        getStringResource(rb, key + ".accelerator").map(KeyStroke::getKeyStroke).ifPresent(menuItem::setAccelerator);
        getCharacterResource(rb, key + ".mnemonic").ifPresent(menuItem::setMnemonic);

        return menuItem;
    }

    private static <T> void addRadioButtonsToMenu(JMenu menu, String key, T[] values, BiConsumer<JRadioButtonMenuItem, T> configurator) {
        ButtonGroup buttonGroup = new ButtonGroup();
        ResourceBundle rb = resourceBundle();
        key += ".";

        if (menu.getItemCount() > 0) {
            menu.addSeparator();
        }

        for (T value : values) {
            String itemKey = key + value.toString().toLowerCase().replace('_', '-');
            JRadioButtonMenuItem item = createMenuItem(rb, itemKey, JRadioButtonMenuItem::new);
            configurator.accept(item, value);
            buttonGroup.add(menu.add(item));
        }

        menu.setEnabled(true);
    }

    private static Optional<String> getStringResource(ResourceBundle resourceBundle, String key) {
        return resourceBundle.containsKey(key) ? Optional.of(resourceBundle.getString(key)) : Optional.empty();
    }

    private static Optional<Character> getCharacterResource(ResourceBundle resourceBundle, String key) {
        return getStringResource(resourceBundle, key).filter(str -> str.length() > 0).map(str -> str.charAt(0));
    }

    private static ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("gui");
    }


    public JMenu getRenderMenu() {
        return renderMenu;
    }

    public JMenuItem getStartRender() {
        return startRender;
    }

    public JMenuItem getStopRender() {
        return stopRender;
    }

    public JRadioButtonMenuItem getSingleSampling() {
        return singleSampling;
    }

    public JRadioButtonMenuItem getGridMultisampling(int index) {
        return gridMultisamplingItems[index];
    }

    public JMenu getSceneMenu() {
        return sceneMenu;
    }

    public Map<Class<? extends Scenery>, JRadioButtonMenuItem> getSceneItems() {
        return Map.of(
                SimpleScenery.class, simpleScenery,
                SuzanneScenery.class, suzanneScenery
        );
    }

}
