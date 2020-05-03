package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.image.Image2D;

import java.util.Objects;

public final class RasterArea {

    private final int x, width;
    private final int y, height;

    public RasterArea(final Image2D image2D) {
        this(0, 0, image2D);
    }

    public RasterArea(final int x, final int y, final Image2D image2D) {
        this(
                x, y,
                Objects.requireNonNull(image2D, "Image not provided").getWidth(),
                Objects.requireNonNull(image2D, "Image not provided").getHeight()
        );
    }

    public RasterArea(final int x, final int y, final int width, final int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Invalid raster area size: " + width + " x " + height);
        }

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinimumX() {
        return x;
    }

    public int getMaximumX() {
        return x + width;
    }

    public int getMinimumY() {
        return y;
    }

    public int getMaximumY() {
        return y + height;
    }

}
