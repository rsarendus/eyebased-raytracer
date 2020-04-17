package ee.ristoseene.raytracer.eyebased.core.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.DefaultRay;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class UniformGridMultisamplePixelProcessorTest extends AbstractPixelProcessorTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void pixelProcessorShouldNotAllowLessThanOneSampleCountX(int sampleCountX) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new UniformGridMultisamplePixelProcessor(configuration, sampleCountX, 1)
        );
        Assertions.assertEquals("Invalid sample count: " + sampleCountX + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void pixelProcessorShouldNotAllowLessThanOneSampleCountY(int sampleCountY) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new UniformGridMultisamplePixelProcessor(configuration, 1, sampleCountY)
        );
        Assertions.assertEquals("Invalid sample count: 1 x " + sampleCountY, exception.getMessage());
    }

    @Test
    public void pixelProcessorShouldAllowSampleCount1x1() {
        UniformGridMultisamplePixelProcessor pixelProcessor = new UniformGridMultisamplePixelProcessor(configuration, 1, 1);
        Assertions.assertEquals(1, pixelProcessor.getSampleCountX());
        Assertions.assertEquals(1, pixelProcessor.getSampleCountY());
    }

    @Test
    public void pixelProcessorShouldAllowArbitraryPositiveSampleCount() {
        UniformGridMultisamplePixelProcessor pixelProcessor = new UniformGridMultisamplePixelProcessor(configuration, 3, 7);
        Assertions.assertEquals(3, pixelProcessor.getSampleCountX());
        Assertions.assertEquals(7, pixelProcessor.getSampleCountY());
    }

    @Test
    public void processPixelFor1x1ShouldInvokeSampleProcessingExactlyOnce() {
        Mockito.doReturn(3.7).when(horizontalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(2.9).when(verticalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(new DefaultRay(new ImmutableVector3(1.1, 2.2, 3.3), new ImmutableVector3(4.4, 5.5, 6.6)))
                .when(tracingRayProducer).produceRay(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(new ImmutableVector4(1.4, 2.3, 3.2, 4.1))
                .when(sampleProcessor).processSample(Mockito.any(Ray.class), Mockito.anyDouble());

        Vector4.Accessible result = new UniformGridMultisamplePixelProcessor(configuration, 1, 1).processPixel(pixelLocation, sampleProcessor);
        VecMathAssertions.assertEquals(new ImmutableVector4(1.4, 2.3, 3.2, 4.1), result, DELTA);

        Mockito.verify(horizontalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getX() + 0.5, DELTA));
        Mockito.verify(verticalRasterToViewMapper, Mockito.times(1))
                .map(AdditionalMatchers.eq(pixelLocation.getY() + 0.5, DELTA));
        Mockito.verify(tracingRayProducer, Mockito.times(1))
                .produceRay(AdditionalMatchers.eq(3.7, DELTA), AdditionalMatchers.eq(2.9, DELTA));
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(
                Mockito.argThat(new RayMatcher(new ImmutableVector3(1.1, 2.2, 3.3), new ImmutableVector3(4.4, 5.5, 6.6), DELTA)),
                Mockito.eq(1.0)
        );
    }

    @Test
    public void processPixelFor2x2ShouldInvokeSampleProcessingExactlyFourTimes() {
        Mockito.doReturn(new ImmutableVector4(1.1, 1.0, 9.9, 0.0)).when(sampleProcessor)
                .processSample(Mockito.argThat(new RayMatcher(createTestRayForMapping(7.25 / 12, 3.25 / 9), DELTA)), Mockito.anyDouble());
        Mockito.doReturn(new ImmutableVector4(1.2, 2.0, 9.8, 0.1)).when(sampleProcessor)
                .processSample(Mockito.argThat(new RayMatcher(createTestRayForMapping(7.75 / 12, 3.25 / 9), DELTA)), Mockito.anyDouble());
        Mockito.doReturn(new ImmutableVector4(1.4, 4.0, 9.0, 0.9)).when(sampleProcessor)
                .processSample(Mockito.argThat(new RayMatcher(createTestRayForMapping(7.25 / 12, 3.75 / 9), DELTA)), Mockito.anyDouble());
        Mockito.doReturn(new ImmutableVector4(1.8, 8.0, 5.0, 7.2)).when(sampleProcessor)
                .processSample(Mockito.argThat(new RayMatcher(createTestRayForMapping(7.75 / 12, 3.75 / 9), DELTA)), Mockito.anyDouble());
        setUpAutomaticMocksForMultisampling();

        Vector4.Accessible result = new UniformGridMultisamplePixelProcessor(configuration, 2, 2).processPixel(pixelLocation, sampleProcessor);
        VecMathAssertions.assertEquals(new ImmutableVector4(5.5 / 4, 15.0 / 4, 33.7 / 4, 8.2 / 4), result, DELTA);

        Mockito.verify(tracingRayProducer, Mockito.times(4)).produceRay(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(
                Mockito.argThat(new RayMatcher(createTestRayForMapping(7.25 / 12, 3.25 / 9), DELTA)),
                Mockito.eq(0.25)
        );
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(
                Mockito.argThat(new RayMatcher(createTestRayForMapping(7.75 / 12, 3.25 / 9), DELTA)),
                Mockito.eq(0.25)
        );
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(
                Mockito.argThat(new RayMatcher(createTestRayForMapping(7.25 / 12, 3.75 / 9), DELTA)),
                Mockito.eq(0.25)
        );
        Mockito.verify(sampleProcessor, Mockito.times(1)).processSample(
                Mockito.argThat(new RayMatcher(createTestRayForMapping(7.75 / 12, 3.75 / 9), DELTA)),
                Mockito.eq(0.25)
        );
        Mockito.verifyNoMoreInteractions(tracingRayProducer, sampleProcessor);
    }

    @Override
    protected PixelProcessor createValidPixelProcessorWithConfiguration(PixelProcessor.Configuration configuration) {
        return new UniformGridMultisamplePixelProcessor(configuration, 1, 1);
    }

}
