package ee.ristoseene.raytracer.eyebased.rasterization.helpers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;
import org.mockito.ArgumentMatcher;

public class RasterAreaMatcher implements ArgumentMatcher<RasterArea> {

    private final int x, y, width, height;

    public RasterAreaMatcher(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RasterAreaMatcher(final RasterArea rasterArea) {
        this(rasterArea.getX(), rasterArea.getY(), rasterArea.getWidth(), rasterArea.getHeight());
    }

    @Override
    public boolean matches(final RasterArea rasterArea) {
        return rasterArea != null
                && x == rasterArea.getX()
                && y == rasterArea.getY()
                && width == rasterArea.getWidth()
                && height == rasterArea.getHeight()
        ;
    }

}
