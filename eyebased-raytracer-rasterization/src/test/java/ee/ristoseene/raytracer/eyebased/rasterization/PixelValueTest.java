package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PixelValueTest {

    @Test
    public void testPixelValue() {
        PixelLocation pixelLocation = new PixelLocation(7, 3);
        SampleValue sampleValue = Mockito.mock(SampleValue.class);

        PixelValue pixelValue = new PixelValue(pixelLocation, sampleValue, 5);

        Assertions.assertSame(pixelLocation, pixelValue.getPixelLocation());
        Assertions.assertSame(sampleValue, pixelValue.getSampleValue());
        Assertions.assertEquals(5, pixelValue.getSampleCount());
    }

}
