package ee.ristoseene.raytracer.eyebased.demo.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

public class ConfigurableMouseMotionListener implements MouseMotionListener {

    private Consumer<MouseEvent> mouseDraggedHandler;
    private Consumer<MouseEvent> mouseMovedHandler;

    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseDraggedHandler != null) mouseDraggedHandler.accept(mouseEvent);
    }

    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseMovedHandler != null) mouseMovedHandler.accept(mouseEvent);
    }

    public Consumer<MouseEvent> getMouseDraggedHandler() {
        return mouseDraggedHandler;
    }

    public void setMouseDraggedHandler(final Consumer<MouseEvent> mouseDraggedHandler) {
        this.mouseDraggedHandler = mouseDraggedHandler;
    }

    public ConfigurableMouseMotionListener withMouseDraggedHandler(final Consumer<MouseEvent> mouseDraggedHandler) {
        setMouseDraggedHandler(mouseDraggedHandler);
        return this;
    }

    public Consumer<MouseEvent> getMouseMovedHandler() {
        return mouseMovedHandler;
    }

    public void setMouseMovedHandler(final Consumer<MouseEvent> mouseMovedHandler) {
        this.mouseMovedHandler = mouseMovedHandler;
    }

    public ConfigurableMouseMotionListener withMouseMovedHandler(final Consumer<MouseEvent> mouseMovedHandler) {
        setMouseMovedHandler(mouseMovedHandler);
        return this;
    }

}
