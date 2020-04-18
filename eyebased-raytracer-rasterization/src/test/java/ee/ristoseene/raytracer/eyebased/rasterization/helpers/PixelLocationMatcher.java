package ee.ristoseene.raytracer.eyebased.rasterization.helpers;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import org.mockito.ArgumentMatcher;

public class PixelLocationMatcher implements ArgumentMatcher<PixelLocation> {

    private final int x, y;

    public PixelLocationMatcher(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PixelLocationMatcher(PixelLocation pixelLocation) {
        this(pixelLocation.getX(), pixelLocation.getY());
    }

    @Override
    public boolean matches(PixelLocation pixelLocation) {
        return pixelLocation != null
                && x == pixelLocation.getX()
                && y == pixelLocation.getY()
        ;
    }

}
