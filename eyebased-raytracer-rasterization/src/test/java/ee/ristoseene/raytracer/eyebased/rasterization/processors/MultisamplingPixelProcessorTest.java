package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class MultisamplingPixelProcessorTest extends BaselinePixelProcessorTest {

    @Mock
    protected SampleValueAccumulatorFactory sampleValueAccumulatorFactory;

    @Test
    public void pixelProcessorShouldNotAllowMissingSampleValueAccumulatorFactory() {
        MultisamplingPixelProcessor.Configuration configuration = createDefaultConfiguration()
                .withSampleValueAccumulatorFactory(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createValidPixelProcessorWithConfiguration(configuration)
        );

        Assertions.assertEquals("Sample value accumulator factory not provided", exception.getMessage());
    }

    protected abstract MultisamplingPixelProcessor createValidPixelProcessorWithConfiguration(
            MultisamplingPixelProcessor.Configuration configuration
    );

    @Override
    protected BaselinePixelProcessor createValidPixelProcessorWithConfiguration(
            BaselinePixelProcessor.Configuration configuration
    ) {
        return createValidPixelProcessorWithConfiguration((MultisamplingPixelProcessor.Configuration) configuration);
    }

    @Override
    protected MultisamplingPixelProcessor.Configuration createDefaultConfiguration() {
        return MultisamplingPixelProcessor.configuration()
                .withSampleValueAccumulatorFactory(sampleValueAccumulatorFactory)
                .withConfiguration(super.createDefaultConfiguration());
    }

}
