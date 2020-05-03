package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.image.Image2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class RasterAreaTest {

    @Test
    public void testRasterAreaFromImage2DOnly() {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(2).when(image2D).getWidth();
        Mockito.doReturn(3).when(image2D).getHeight();

        RasterArea rasterArea = new RasterArea(image2D);

        Assertions.assertEquals(0, rasterArea.getX());
        Assertions.assertEquals(0, rasterArea.getY());
        Assertions.assertEquals(2, rasterArea.getWidth());
        Assertions.assertEquals(3, rasterArea.getHeight());
        Assertions.assertEquals(0, rasterArea.getMinimumX());
        Assertions.assertEquals(0, rasterArea.getMinimumY());
        Assertions.assertEquals(2, rasterArea.getMaximumX());
        Assertions.assertEquals(3, rasterArea.getMaximumY());
    }

    @Test
    public void testRasterAreaFromPrimitiveValuesAndImage2D() {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(4).when(image2D).getWidth();
        Mockito.doReturn(8).when(image2D).getHeight();

        RasterArea rasterArea = new RasterArea(1, 2, image2D);

        Assertions.assertEquals(1, rasterArea.getX());
        Assertions.assertEquals(2, rasterArea.getY());
        Assertions.assertEquals(4, rasterArea.getWidth());
        Assertions.assertEquals(8, rasterArea.getHeight());
        Assertions.assertEquals(1, rasterArea.getMinimumX());
        Assertions.assertEquals(2, rasterArea.getMinimumY());
        Assertions.assertEquals(5, rasterArea.getMaximumX());
        Assertions.assertEquals(10, rasterArea.getMaximumY());
    }

    @Test
    public void testRasterAreaFromPrimitiveValues() {
        RasterArea rasterArea = new RasterArea(1, 2, 4, 8);

        Assertions.assertEquals(1, rasterArea.getX());
        Assertions.assertEquals(2, rasterArea.getY());
        Assertions.assertEquals(4, rasterArea.getWidth());
        Assertions.assertEquals(8, rasterArea.getHeight());
        Assertions.assertEquals(1, rasterArea.getMinimumX());
        Assertions.assertEquals(2, rasterArea.getMinimumY());
        Assertions.assertEquals(5, rasterArea.getMaximumX());
        Assertions.assertEquals(10, rasterArea.getMaximumY());
    }

    @Test
    public void rasterAreaShouldNotAllowMissingImageForImageOnlyConstructor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RasterArea(null)
        );

        Assertions.assertEquals("Image not provided", exception.getMessage());
    }

    @Test
    public void rasterAreaShouldNotAllowMissingImageForMixedConstructor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RasterArea(1, 2, null)
        );

        Assertions.assertEquals("Image not provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowWidthLessThanOneForImageOnlyConstructor(final int width) {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(width).when(image2D).getWidth();
        Mockito.doReturn(1).when(image2D).getHeight();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(image2D)
        );

        Assertions.assertEquals("Invalid raster area size: " + width + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowHeightLessThanOneForImageOnlyConstructor(final int height) {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(1).when(image2D).getWidth();
        Mockito.doReturn(height).when(image2D).getHeight();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(image2D)
        );

        Assertions.assertEquals("Invalid raster area size: 1 x " + height, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowWidthLessThanOneForMixedConstructor(final int width) {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(width).when(image2D).getWidth();
        Mockito.doReturn(1).when(image2D).getHeight();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(0, 0, image2D)
        );

        Assertions.assertEquals("Invalid raster area size: " + width + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowHeightLessThanOneForMixedConstructor(final int height) {
        Image2D image2D = Mockito.mock(Image2D.class);
        Mockito.doReturn(1).when(image2D).getWidth();
        Mockito.doReturn(height).when(image2D).getHeight();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(0, 0, image2D)
        );

        Assertions.assertEquals("Invalid raster area size: 1 x " + height, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowWidthLessThanOneForPrimitivesConstructor(final int width) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(0, 0, width, 1)
        );

        Assertions.assertEquals("Invalid raster area size: " + width + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void rasterAreaShouldNotAllowHeightLessThanOneForPrimitivesOnlyConstructor(final int height) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterArea(0, 0, 1, height)
        );

        Assertions.assertEquals("Invalid raster area size: 1 x " + height, exception.getMessage());
    }

}
