package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;
import ee.ristoseene.raytracer.eyebased.rasterization.SampleProcessor;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractPixelProcessorTest {

    protected static final double DELTA = 0.000001;

    @Mock
    protected RasterToViewMapper horizontalRasterToViewMapper;
    @Mock
    protected RasterToViewMapper verticalRasterToViewMapper;
    @Mock
    protected TracingRayProducer tracingRayProducer;
    @Mock
    protected SampleProcessor sampleProcessor;

    protected PixelProcessor.Configuration configuration;

    protected PixelLocation pixelLocation = new PixelLocation(7, 3);

    @BeforeEach
    public void setUpDefaultConfiguration() {
        configuration = PixelProcessor.configuration()
                .withHorizontalPixelToViewMapper(horizontalRasterToViewMapper)
                .withVerticalPixelToViewMapper(verticalRasterToViewMapper)
                .withRayProducer(tracingRayProducer);
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingConfiguration() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(null)
        );
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingHorizontalRasterToViewMapper() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration.withHorizontalPixelToViewMapper(null))
        );
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingVerticalRasterToViewMapper() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration.withVerticalPixelToViewMapper(null))
        );
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingTracingRayProducer() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration.withRayProducer(null))
        );
    }

    protected abstract PixelProcessor createValidPixelProcessorWithConfiguration(PixelProcessor.Configuration configuration);

    protected void setUpAutomaticMocksForMultisampling() {
        Mockito.doAnswer(invocationOnMock -> {
            double input = invocationOnMock.getArgument(0, Double.class).doubleValue();
            return input / 12.0;
        }).when(horizontalRasterToViewMapper).map(Mockito.anyDouble());

        Mockito.doAnswer(invocationOnMock -> {
            double input = invocationOnMock.getArgument(0, Double.class).doubleValue();
            return input / 9.0;
        }).when(verticalRasterToViewMapper).map(Mockito.anyDouble());

        Mockito.doAnswer(invocationOnMock -> {
            double horizontal = invocationOnMock.getArgument(0, Double.class);
            double vertical = invocationOnMock.getArgument(1, Double.class);
            return createTestRayForMapping(horizontal, vertical);
        }).when(tracingRayProducer).produceRay(Mockito.anyDouble(), Mockito.anyDouble());
    }

    protected static Ray createTestRayForMapping(final double horizontal, final double vertical) {
        double h = 7.7 * horizontal;
        double v = 3.3 * vertical;

        Vector3.Accessible origin = new ImmutableVector3(h + v, h - v, v - h);
        Vector3.Accessible direction = new ImmutableVector3(v - h, h + v, h - v);

        return new SimpleRay(origin, direction);
    }

}
