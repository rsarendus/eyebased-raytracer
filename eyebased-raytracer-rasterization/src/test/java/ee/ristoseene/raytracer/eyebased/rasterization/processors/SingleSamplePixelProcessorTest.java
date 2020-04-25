package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class SingleSamplePixelProcessorTest extends BaselinePixelProcessorTest {

    @Test
    public void processPixelShouldInvokeSampleProcessingExactlyOnce() {
        SingleSamplePixelProcessor.Configuration configuration = createDefaultConfiguration();
        Mockito.doReturn(3.7).when(horizontalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(2.9).when(verticalRasterToViewMapper).map(Mockito.anyDouble());

        Ray ray = Mockito.mock(Ray.class);
        Mockito.doReturn(ray).when(tracingRayProducer).produceRay(Mockito.anyDouble(), Mockito.anyDouble());

        SampleValue sampleValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(sampleValue).when(sampleProcessor).processSample(Mockito.any(Ray.class), Mockito.anyDouble());

        PixelValue result = new SingleSamplePixelProcessor(configuration).processPixel(pixelLocation, sampleProcessor);

        Assertions.assertNotNull(result);
        Assertions.assertSame(pixelLocation, result.getPixelLocation());
        Assertions.assertSame(sampleValue, result.getSampleValue());
        Assertions.assertEquals(1, result.getSampleCount());

        Mockito.verify(horizontalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getX() + 0.5, DELTA));
        Mockito.verify(verticalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getY() + 0.5, DELTA));
        Mockito.verify(tracingRayProducer, Mockito.times(1))
                .produceRay(AdditionalMatchers.eq(3.7, DELTA), AdditionalMatchers.eq(2.9, DELTA));
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray, 1.0);
    }

    @Override
    protected SingleSamplePixelProcessor createValidPixelProcessorWithConfiguration(SingleSamplePixelProcessor.Configuration configuration) {
        return new SingleSamplePixelProcessor(configuration);
    }

}
