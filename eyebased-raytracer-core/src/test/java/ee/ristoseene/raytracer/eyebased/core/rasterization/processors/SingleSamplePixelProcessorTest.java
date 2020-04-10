package ee.ristoseene.raytracer.eyebased.core.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.DefaultRay;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class SingleSamplePixelProcessorTest extends AbstractPixelProcessorTest {

    @Test
    public void processPixelShouldInvokeSampleProcessingExactlyOnce() {
        Mockito.doReturn(3.7).when(horizontalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(2.9).when(verticalRasterToViewMapper).map(Mockito.anyDouble());
        Mockito.doReturn(new DefaultRay(new ImmutableVector3(1.1, 2.2, 3.3), new ImmutableVector3(4.4, 5.5, 6.6)))
                .when(tracingRayProducer).produceRay(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(new ImmutableVector4(1.4, 2.3, 3.2, 4.1))
                .when(sampleProcessor).processSample(Mockito.any(Ray.class), Mockito.anyDouble());

        Vector4.Accessible result = new SingleSamplePixelProcessor(configuration).processPixel(pixelLocation, sampleProcessor);
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

    @Override
    protected PixelProcessor createValidPixelProcessorWithConfiguration(PixelProcessor.Configuration configuration) {
        return new SingleSamplePixelProcessor(configuration);
    }

}