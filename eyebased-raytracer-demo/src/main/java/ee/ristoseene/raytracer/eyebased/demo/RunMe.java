package ee.ristoseene.raytracer.eyebased.demo;

import ee.ristoseene.raytracer.eyebased.demo.gui.GuiFrame;
import ee.ristoseene.raytracer.eyebased.demo.gui.GuiPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class RunMe {

    public static void main(String[] args) {
        processArguments(args);

        SwingUtilities.invokeLater(() -> {
            initializeLookAndFeel();

            final JFrame frame = new GuiFrame();
            frame.setVisible(true);
        });
    }

    private static void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void processArguments(String[] args) {
        for (final String arg : args) {
            if ("--debug-wireframe".equals(arg)) {
                GuiPanel.DEBUG = true;
            }
        }
    }

}
