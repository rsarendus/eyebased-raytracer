package ee.ristoseene.raytracer.eyebased.rasterization.helpers;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import org.junit.jupiter.api.Assertions;

public final class PixelLocationAssertions {

    public static void assertEquals(final PixelLocation expected, final PixelLocation actual) {
        Assertions.assertEquals(expected.getX(), actual.getX(), "PixelLocation.x mismatch");
        Assertions.assertEquals(expected.getY(), actual.getY(), "PixelLocation.y mismatch");
    }

}
