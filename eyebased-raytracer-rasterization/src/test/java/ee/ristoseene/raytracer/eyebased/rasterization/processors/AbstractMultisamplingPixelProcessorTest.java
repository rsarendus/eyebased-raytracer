package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractMultisamplingPixelProcessorTest extends AbstractPixelProcessorTest {

    @Mock
    protected SampleValueAccumulatorFactory sampleValueAccumulatorFactory;

    @Test
    public void pixelProcessorShouldNotAllowMissingSampleValueAccumulatorFactory() {
        AbstractMultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration()
                .withSampleValueAccumulatorFactory(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration)
        );

        Assertions.assertEquals("Sample value accumulator factory not provided", exception.getMessage());
    }

    protected abstract AbstractMultisamplingPixelProcessor createValidPixelProcessorWithConfiguration(
            AbstractMultisamplingPixelProcessor.Configuration configuration
    );

    @Override
    protected AbstractPixelProcessor createValidPixelProcessorWithConfiguration(
            AbstractPixelProcessor.Configuration configuration
    ) {
        return createValidPixelProcessorWithConfiguration((AbstractMultisamplingPixelProcessor.Configuration) configuration);
    }

    @Override
    protected AbstractMultisamplingPixelProcessor.Configuration createDefaultConfiguration() {
        return AbstractMultisamplingPixelProcessor.configuration()
                .withSampleValueAccumulatorFactory(sampleValueAccumulatorFactory)
                .withConfiguration(super.createDefaultConfiguration());
    }

}
