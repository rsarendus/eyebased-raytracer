package ee.ristoseene.raytracer.eyebased.rasterization.helpers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;
import org.junit.jupiter.api.Assertions;

public final class RasterAreaAssertions {

    public static void assertEquals(final RasterArea expected, final RasterArea actual) {
        Assertions.assertEquals(expected.getX(), actual.getX(), "RasterArea.x mismatch");
        Assertions.assertEquals(expected.getY(), actual.getY(), "RasterArea.y mismatch");
        Assertions.assertEquals(expected.getWidth(), actual.getWidth(), "RasterArea.width mismatch");
        Assertions.assertEquals(expected.getHeight(), actual.getHeight(), "RasterArea.height mismatch");
        Assertions.assertEquals(expected.getMinimumX(), actual.getMinimumX(), "RasterArea.minimumX mismatch");
        Assertions.assertEquals(expected.getMinimumY(), actual.getMinimumY(), "RasterArea.minimumY mismatch");
        Assertions.assertEquals(expected.getMaximumX(), actual.getMaximumX(), "RasterArea.maximumX mismatch");
        Assertions.assertEquals(expected.getMaximumY(), actual.getMaximumY(), "RasterArea.maximumY mismatch");
    }

}
