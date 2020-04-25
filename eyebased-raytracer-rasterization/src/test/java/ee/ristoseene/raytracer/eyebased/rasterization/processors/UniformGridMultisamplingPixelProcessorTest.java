package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class UniformGridMultisamplingPixelProcessorTest extends MultisamplingPixelProcessorTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void pixelProcessorShouldNotAllowLessThanOneSampleCountX(int sampleCountX) {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new UniformGridMultisamplingPixelProcessor(configuration, sampleCountX, 1)
        );
        Assertions.assertEquals("Invalid sample count: " + sampleCountX + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void pixelProcessorShouldNotAllowLessThanOneSampleCountY(int sampleCountY) {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new UniformGridMultisamplingPixelProcessor(configuration, 1, sampleCountY)
        );
        Assertions.assertEquals("Invalid sample count: 1 x " + sampleCountY, exception.getMessage());
    }

    @Test
    public void pixelProcessorShouldAllowSampleCount1x1() {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();

        UniformGridMultisamplingPixelProcessor pixelProcessor = new UniformGridMultisamplingPixelProcessor(configuration, 1, 1);
        Assertions.assertEquals(1, pixelProcessor.getSampleCountX());
        Assertions.assertEquals(1, pixelProcessor.getSampleCountY());
    }

    @Test
    public void pixelProcessorShouldAllowArbitraryPositiveSampleCount() {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();

        UniformGridMultisamplingPixelProcessor pixelProcessor = new UniformGridMultisamplingPixelProcessor(configuration, 3, 7);
        Assertions.assertEquals(3, pixelProcessor.getSampleCountX());
        Assertions.assertEquals(7, pixelProcessor.getSampleCountY());
    }

    @Test
    public void processPixelFor1x1ShouldInvokeSampleProcessingExactlyOnce() {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();
        SampleValueAccumulator sampleValueAccumulator = Mockito.mock(SampleValueAccumulator.class);
        Mockito.doReturn(sampleValueAccumulator).when(sampleValueAccumulatorFactory).create();
        SampleValue accumulatedSampleValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(accumulatedSampleValue).when(sampleValueAccumulator).getValue();

        Mockito.doReturn(3.7).when(horizontalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(2.9).when(verticalRasterToViewMapper).map(Mockito.anyDouble());

        Ray ray = Mockito.mock(Ray.class);
        Mockito.doReturn(ray).when(tracingRayProducer).produceRay(Mockito.anyDouble(), Mockito.anyDouble());

        SampleValue tracedSampleValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(tracedSampleValue).when(sampleProcessor).processSample(Mockito.any(Ray.class), Mockito.anyDouble());

        PixelValue result = new UniformGridMultisamplingPixelProcessor(configuration, 1, 1).processPixel(pixelLocation, sampleProcessor);

        Assertions.assertNotNull(result);
        Assertions.assertSame(pixelLocation, result.getPixelLocation());
        Assertions.assertSame(accumulatedSampleValue, result.getSampleValue());
        Assertions.assertEquals(1, result.getSampleCount());

        Mockito.verify(sampleValueAccumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(horizontalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getX() + 0.5, DELTA));
        Mockito.verify(verticalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getY() + 0.5, DELTA));
        Mockito.verify(tracingRayProducer, Mockito.times(1))
                .produceRay(AdditionalMatchers.eq(3.7, DELTA), AdditionalMatchers.eq(2.9, DELTA));
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray, 1.0);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).accumulate(tracedSampleValue, 1.0);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(sampleProcessor, sampleValueAccumulator);
    }

    @Test
    public void processPixelFor2x2ShouldInvokeSampleProcessingExactlyFourTimes() {
        UniformGridMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration();
        SampleValueAccumulator sampleValueAccumulator = Mockito.mock(SampleValueAccumulator.class);
        Mockito.doReturn(sampleValueAccumulator).when(sampleValueAccumulatorFactory).create();
        SampleValue accumulatedSampleValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(accumulatedSampleValue).when(sampleValueAccumulator).getValue();

        Mockito.doReturn(1.25).when(horizontalRasterToViewMapper).map(AdditionalMatchers.eq(pixelLocation.getX() + 0.25, DELTA));
        Mockito.doReturn(2.25).when(verticalRasterToViewMapper).map(AdditionalMatchers.eq(pixelLocation.getY() + 0.25, DELTA));
        Mockito.doReturn(1.75).when(horizontalRasterToViewMapper).map(AdditionalMatchers.eq(pixelLocation.getX() + 0.75, DELTA));
        Mockito.doReturn(2.75).when(verticalRasterToViewMapper).map(AdditionalMatchers.eq(pixelLocation.getY() + 0.75, DELTA));

        Ray ray1 = Mockito.mock(Ray.class);
        Mockito.doReturn(ray1).when(tracingRayProducer).produceRay(1.25, 2.25);
        Ray ray2 = Mockito.mock(Ray.class);
        Mockito.doReturn(ray2).when(tracingRayProducer).produceRay(1.75, 2.25);
        Ray ray3 = Mockito.mock(Ray.class);
        Mockito.doReturn(ray3).when(tracingRayProducer).produceRay(1.25, 2.75);
        Ray ray4 = Mockito.mock(Ray.class);
        Mockito.doReturn(ray4).when(tracingRayProducer).produceRay(1.75, 2.75);

        SampleValue tracedSampleValue1 = Mockito.mock(SampleValue.class);
        Mockito.doReturn(tracedSampleValue1).when(sampleProcessor).processSample(ray1, 0.25);
        SampleValue tracedSampleValue2 = Mockito.mock(SampleValue.class);
        Mockito.doReturn(tracedSampleValue2).when(sampleProcessor).processSample(ray2, 0.25);
        SampleValue tracedSampleValue3 = Mockito.mock(SampleValue.class);
        Mockito.doReturn(tracedSampleValue3).when(sampleProcessor).processSample(ray3, 0.25);
        SampleValue tracedSampleValue4 = Mockito.mock(SampleValue.class);
        Mockito.doReturn(tracedSampleValue4).when(sampleProcessor).processSample(ray4, 0.25);

        PixelValue result = new UniformGridMultisamplingPixelProcessor(configuration, 2, 2).processPixel(pixelLocation, sampleProcessor);

        Assertions.assertNotNull(result);
        Assertions.assertSame(pixelLocation, result.getPixelLocation());
        Assertions.assertSame(accumulatedSampleValue, result.getSampleValue());
        Assertions.assertEquals(4, result.getSampleCount());

        Mockito.verify(sampleValueAccumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(tracingRayProducer, Mockito.times(4)).produceRay(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray1, 0.25);
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray2, 0.25);
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray3, 0.25);
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(ray4, 0.25);
        Mockito.verifyNoMoreInteractions(sampleProcessor);

        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).accumulate(tracedSampleValue1, 0.25);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).accumulate(tracedSampleValue2, 0.25);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).accumulate(tracedSampleValue3, 0.25);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).accumulate(tracedSampleValue4, 0.25);
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(sampleValueAccumulator);
    }

    @Override
    protected UniformGridMultisamplingPixelProcessor createValidPixelProcessorWithConfiguration(UniformGridMultisamplingPixelProcessor.Configuration configuration) {
        return new UniformGridMultisamplingPixelProcessor(configuration, 1, 1);
    }

}
