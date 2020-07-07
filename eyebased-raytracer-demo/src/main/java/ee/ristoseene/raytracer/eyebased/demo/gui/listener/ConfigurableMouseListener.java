package ee.ristoseene.raytracer.eyebased.demo.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class ConfigurableMouseListener implements MouseListener {

    private Consumer<MouseEvent> mouseClickedHandler;
    private Consumer<MouseEvent> mousePressedHandler;
    private Consumer<MouseEvent> mouseReleasedHandler;
    private Consumer<MouseEvent> mouseEnteredHandler;
    private Consumer<MouseEvent> mouseExitedHandler;

    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseClickedHandler != null) mouseClickedHandler.accept(mouseEvent);
    }

    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mousePressedHandler != null) mousePressedHandler.accept(mouseEvent);
    }

    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseReleasedHandler != null) mouseReleasedHandler.accept(mouseEvent);
    }

    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEnteredHandler != null) mouseEnteredHandler.accept(mouseEvent);
    }

    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseExitedHandler != null) mouseExitedHandler.accept(mouseEvent);
    }

    public Consumer<MouseEvent> getMouseClickedHandler() {
        return mouseClickedHandler;
    }

    public void setMouseClickedHandler(final Consumer<MouseEvent> mouseClickedHandler) {
        this.mouseClickedHandler = mouseClickedHandler;
    }

    public ConfigurableMouseListener withMouseClickedHandler(final Consumer<MouseEvent> mouseClickedHandler) {
        setMouseClickedHandler(mouseClickedHandler);
        return this;
    }

    public Consumer<MouseEvent> getMousePressedHandler() {
        return mousePressedHandler;
    }

    public void setMousePressedHandler(final Consumer<MouseEvent> mousePressedHandler) {
        this.mousePressedHandler = mousePressedHandler;
    }

    public ConfigurableMouseListener withMousePressedHandler(final Consumer<MouseEvent> mousePressedHandler) {
        setMousePressedHandler(mousePressedHandler);
        return this;
    }

    public Consumer<MouseEvent> getMouseReleasedHandler() {
        return mouseReleasedHandler;
    }

    public void setMouseReleasedHandler(final Consumer<MouseEvent> mouseReleasedHandler) {
        this.mouseReleasedHandler = mouseReleasedHandler;
    }

    public ConfigurableMouseListener withMouseReleasedHandler(final Consumer<MouseEvent> mouseReleasedHandler) {
        setMouseReleasedHandler(mouseReleasedHandler);
        return this;
    }

    public Consumer<MouseEvent> getMouseEnteredHandler() {
        return mouseEnteredHandler;
    }

    public void setMouseEnteredHandler(final Consumer<MouseEvent> mouseEnteredHandler) {
        this.mouseEnteredHandler = mouseEnteredHandler;
    }

    public ConfigurableMouseListener withMouseEnteredHandler(final Consumer<MouseEvent> mouseEnteredHandler) {
        setMouseEnteredHandler(mouseEnteredHandler);
        return this;
    }

    public Consumer<MouseEvent> getMouseExitedHandler() {
        return mouseExitedHandler;
    }

    public void setMouseExitedHandler(final Consumer<MouseEvent> mouseExitedHandler) {
        this.mouseExitedHandler = mouseExitedHandler;
    }

    public ConfigurableMouseListener withMouseExitedHandler(final Consumer<MouseEvent> mouseExitedHandler) {
        setMouseExitedHandler(mouseExitedHandler);
        return this;
    }

}
