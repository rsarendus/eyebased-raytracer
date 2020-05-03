package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;
import ee.ristoseene.raytracer.eyebased.rasterization.helpers.RasterAreaAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class DefaultHorizontalScanLineRasterAreaResolverTest extends AbstractScanLineRasterAreaResolverTest {

    @Override
    protected DefaultHorizontalScanLineRasterAreaResolver createInstanceWith(final int numberOfScanLines, final int blocksPerScanLine) {
        return new DefaultHorizontalScanLineRasterAreaResolver(new RasterArea(0, 0, blocksPerScanLine, numberOfScanLines), w -> 1, h -> 1);
    }

    @Test
    public void rasterAreaResolverShouldNotAllowMissingRasterArea() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultHorizontalScanLineRasterAreaResolver(null, w -> 1, h -> 1)
        );

        Assertions.assertEquals("Raster area not provided", exception.getMessage());
    }

    @Test
    public void rasterAreaResolverShouldNotAllowMissingHorizontalBlockSizeResolver() {
        RasterArea rasterArea = new RasterArea(0, 0, 1, 1);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, null, h -> 1)
        );

        Assertions.assertEquals("Horizontal block size resolver not provided", exception.getMessage());
    }

    @Test
    public void rasterAreaResolverShouldNotAllowMissingVerticalBlockSizeResolver() {
        RasterArea rasterArea = new RasterArea(0, 0, 1, 1);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> 1, null)
        );

        Assertions.assertEquals("Vertical block size resolver not provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaResolverShouldNotAllowHorizontalBlockSizeLessThanOne(final int blockSize) {
        RasterArea rasterArea = new RasterArea(0, 0, 1, 1);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> blockSize, h -> 1)
        );

        Assertions.assertEquals("Invalid block size: " + blockSize, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaResolverShouldNotAllowVerticalBlockSizeLessThanOne(final int blockSize) {
        RasterArea rasterArea = new RasterArea(0, 0, 1, 1);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> 1, h -> blockSize)
        );

        Assertions.assertEquals("Invalid block size: " + blockSize, exception.getMessage());
    }

    @Test
    public void rasterAreaResolverShouldAllowBlockSizesOfOne() {
        RasterArea rasterArea = new RasterArea(1, 2, 7, 3);
        AbstractScanLineRasterAreaResolver.BlockSizeResolver horizontalSizeResolver = Mockito.mock(AbstractScanLineRasterAreaResolver.BlockSizeResolver.class);
        AbstractScanLineRasterAreaResolver.BlockSizeResolver verticalSizeResolver = Mockito.mock(AbstractScanLineRasterAreaResolver.BlockSizeResolver.class);
        Mockito.doReturn(1).when(horizontalSizeResolver).resolveBlockSize(Mockito.anyInt());
        Mockito.doReturn(1).when(verticalSizeResolver).resolveBlockSize(Mockito.anyInt());

        DefaultHorizontalScanLineRasterAreaResolver rasterAreaResolver = new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, horizontalSizeResolver, verticalSizeResolver);

        Assertions.assertEquals(21, rasterAreaResolver.blockCount());
        Mockito.verify(horizontalSizeResolver, Mockito.times(1)).resolveBlockSize(7);
        Mockito.verify(verticalSizeResolver, Mockito.times(1)).resolveBlockSize(3);
    }

    @Test
    public void resolveBlockShouldReturnCorrectRasterAreaForOnePixelBlockSize() {
        RasterArea rasterArea = new RasterArea(5, 7, 3, 2);
        DefaultHorizontalScanLineRasterAreaResolver rasterAreaResolver = new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> 1, h -> 1);

        RasterAreaAssertions.assertEquals(new RasterArea(5, 7, 1, 1), rasterAreaResolver.resolveBlock(0L));
        RasterAreaAssertions.assertEquals(new RasterArea(6, 7, 1, 1), rasterAreaResolver.resolveBlock(1L));
        RasterAreaAssertions.assertEquals(new RasterArea(7, 7, 1, 1), rasterAreaResolver.resolveBlock(2L));
        RasterAreaAssertions.assertEquals(new RasterArea(5, 8, 1, 1), rasterAreaResolver.resolveBlock(3L));
        RasterAreaAssertions.assertEquals(new RasterArea(6, 8, 1, 1), rasterAreaResolver.resolveBlock(4L));
        RasterAreaAssertions.assertEquals(new RasterArea(7, 8, 1, 1), rasterAreaResolver.resolveBlock(5L));
    }

    @Test
    public void resolveBlockShouldReturnCorrectRasterAreaForArbitraryBlockSize() {
        RasterArea rasterArea = new RasterArea(5, 7, 6, 8);
        DefaultHorizontalScanLineRasterAreaResolver rasterAreaResolver = new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> 3, h -> 4);

        RasterAreaAssertions.assertEquals(new RasterArea(5, 7, 3, 4), rasterAreaResolver.resolveBlock(0L));
        RasterAreaAssertions.assertEquals(new RasterArea(8, 7, 3, 4), rasterAreaResolver.resolveBlock(1L));
        RasterAreaAssertions.assertEquals(new RasterArea(5, 11, 3, 4), rasterAreaResolver.resolveBlock(2L));
        RasterAreaAssertions.assertEquals(new RasterArea(8, 11, 3, 4), rasterAreaResolver.resolveBlock(3L));
    }

    @Test
    public void resolveBlockShouldReturnCorrectRasterAreaForBlockSizesNotFittingExactlyIntoTheArea() {
        RasterArea rasterArea = new RasterArea(7, 9, 12, 11);
        DefaultHorizontalScanLineRasterAreaResolver rasterAreaResolver = new DefaultHorizontalScanLineRasterAreaResolver(rasterArea, w -> 5, h -> 4);

        RasterAreaAssertions.assertEquals(new RasterArea(7, 9, 5, 4), rasterAreaResolver.resolveBlock(0L));
        RasterAreaAssertions.assertEquals(new RasterArea(12, 9, 5, 4), rasterAreaResolver.resolveBlock(1L));
        RasterAreaAssertions.assertEquals(new RasterArea(17, 9, 2, 4), rasterAreaResolver.resolveBlock(2L));
        RasterAreaAssertions.assertEquals(new RasterArea(7, 13, 5, 4), rasterAreaResolver.resolveBlock(3L));
        RasterAreaAssertions.assertEquals(new RasterArea(12, 13, 5, 4), rasterAreaResolver.resolveBlock(4L));
        RasterAreaAssertions.assertEquals(new RasterArea(17, 13, 2, 4), rasterAreaResolver.resolveBlock(5L));
        RasterAreaAssertions.assertEquals(new RasterArea(7, 17, 5, 3), rasterAreaResolver.resolveBlock(6L));
        RasterAreaAssertions.assertEquals(new RasterArea(12, 17, 5, 3), rasterAreaResolver.resolveBlock(7L));
        RasterAreaAssertions.assertEquals(new RasterArea(17, 17, 2, 3), rasterAreaResolver.resolveBlock(8L));
    }

}
