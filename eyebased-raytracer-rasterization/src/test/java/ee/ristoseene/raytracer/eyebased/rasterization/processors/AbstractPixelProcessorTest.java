package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;
import ee.ristoseene.raytracer.eyebased.rasterization.SampleProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

    protected PixelLocation pixelLocation = new PixelLocation(7, 3);

    @Test
    public void pixelProcessorShouldNotAllowMissingConfiguration() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(null)
        );
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingHorizontalRasterToViewMapper() {
        AbstractPixelProcessor.Configuration configuration = createDefaultConfiguration()
                .withHorizontalPixelToViewMapper(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration)
        );

        Assertions.assertEquals("Horizontal raster to view mapper not provided", exception.getMessage());
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingVerticalRasterToViewMapper() {
        AbstractPixelProcessor.Configuration configuration = createDefaultConfiguration()
                .withVerticalPixelToViewMapper(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration)
        );

        Assertions.assertEquals("Vertical raster to view mapper not provided", exception.getMessage());
    }

    @Test
    public void pixelProcessorShouldNotAllowMissingTracingRayProducer() {
        AbstractPixelProcessor.Configuration configuration = createDefaultConfiguration()
                .withRayProducer(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration)
        );

        Assertions.assertEquals("Ray producer not provided", exception.getMessage());
    }

    protected abstract AbstractPixelProcessor createValidPixelProcessorWithConfiguration(
            AbstractPixelProcessor.Configuration configuration
    );

    protected AbstractPixelProcessor.Configuration createDefaultConfiguration() {
        return AbstractPixelProcessor.configuration()
                .withHorizontalPixelToViewMapper(horizontalRasterToViewMapper)
                .withVerticalPixelToViewMapper(verticalRasterToViewMapper)
                .withRayProducer(tracingRayProducer);
    }

}
