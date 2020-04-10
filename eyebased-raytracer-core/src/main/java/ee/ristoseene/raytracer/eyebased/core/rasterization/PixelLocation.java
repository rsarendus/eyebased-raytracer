package ee.ristoseene.raytracer.eyebased.core.rasterization;

public final class PixelLocation {

    private final int x;
    private final int y;

    public PixelLocation(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
