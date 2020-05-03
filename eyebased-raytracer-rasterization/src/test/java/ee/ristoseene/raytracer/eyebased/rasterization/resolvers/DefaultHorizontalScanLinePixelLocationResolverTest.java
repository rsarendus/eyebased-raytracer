package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;
import ee.ristoseene.raytracer.eyebased.rasterization.helpers.PixelLocationAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultHorizontalScanLinePixelLocationResolverTest extends AbstractScanLineRasterBlockResolverTest<PixelLocation> {

    protected DefaultHorizontalScanLinePixelLocationResolver createInstanceWith(int numberOfScanLines, int blocksPerScanLine) {
        return new DefaultHorizontalScanLinePixelLocationResolver(new RasterArea(0, 0, blocksPerScanLine, numberOfScanLines));
    }

    @Test
    public void pixelLocationResolverShouldNotAllowMissingRasterArea() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultHorizontalScanLinePixelLocationResolver(null)
        );

        Assertions.assertEquals("Raster area not provided", exception.getMessage());
    }

    @Test
    public void resolveBlockShouldReturnCorrectPixelLocationCorrespondingToItsIndex() {
        RasterArea rasterArea = new RasterArea(5, 7, 3, 2);
        DefaultHorizontalScanLinePixelLocationResolver pixelLocationResolver = new DefaultHorizontalScanLinePixelLocationResolver(rasterArea);

        PixelLocationAssertions.assertEquals(new PixelLocation(5, 7), pixelLocationResolver.resolveBlock(0L));
        PixelLocationAssertions.assertEquals(new PixelLocation(6, 7), pixelLocationResolver.resolveBlock(1L));
        PixelLocationAssertions.assertEquals(new PixelLocation(7, 7), pixelLocationResolver.resolveBlock(2L));
        PixelLocationAssertions.assertEquals(new PixelLocation(5, 8), pixelLocationResolver.resolveBlock(3L));
        PixelLocationAssertions.assertEquals(new PixelLocation(6, 8), pixelLocationResolver.resolveBlock(4L));
        PixelLocationAssertions.assertEquals(new PixelLocation(7, 8), pixelLocationResolver.resolveBlock(5L));
    }

}
