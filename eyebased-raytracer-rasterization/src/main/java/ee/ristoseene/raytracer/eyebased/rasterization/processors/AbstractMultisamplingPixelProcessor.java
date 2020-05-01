package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;

import java.util.Objects;

public abstract class AbstractMultisamplingPixelProcessor extends AbstractPixelProcessor implements PixelProcessor {

    protected final SampleValueAccumulatorFactory sampleValueAccumulatorFactory;

    protected AbstractMultisamplingPixelProcessor(final Configuration configuration) {
        super(configuration);

        sampleValueAccumulatorFactory = Objects.requireNonNull(
                configuration.getSampleValueAccumulatorFactory(),
                "Sample value accumulator factory not provided"
        );
    }

    public static class Configuration extends AbstractPixelProcessor.Configuration {

        private SampleValueAccumulatorFactory sampleValueAccumulatorFactory;

        public SampleValueAccumulatorFactory getSampleValueAccumulatorFactory() {
            return sampleValueAccumulatorFactory;
        }

        public void setSampleValueAccumulatorFactory(final SampleValueAccumulatorFactory sampleValueAccumulatorFactory) {
            this.sampleValueAccumulatorFactory = sampleValueAccumulatorFactory;
        }

        public Configuration withSampleValueAccumulatorFactory(final SampleValueAccumulatorFactory sampleValueAccumulatorFactory) {
            this.setSampleValueAccumulatorFactory(sampleValueAccumulatorFactory);
            return this;
        }

        @Override
        public Configuration withConfiguration(final AbstractPixelProcessor.Configuration configuration) {
            if (configuration instanceof Configuration) {
                final Configuration multisamplingConfiguration = (Configuration) configuration;
                setSampleValueAccumulatorFactory(multisamplingConfiguration.getSampleValueAccumulatorFactory());
            }
            return (Configuration) super.withConfiguration(configuration);
        }

        @Override
        public Configuration withHorizontalPixelToViewMapper(final RasterToViewMapper horizontalRasterToViewMapper) {
            return (Configuration) super.withHorizontalPixelToViewMapper(horizontalRasterToViewMapper);
        }

        @Override
        public Configuration withVerticalPixelToViewMapper(final RasterToViewMapper verticalRasterToViewMapper) {
            return (Configuration) super.withVerticalPixelToViewMapper(verticalRasterToViewMapper);
        }

        @Override
        public Configuration withRayProducer(final TracingRayProducer rayProducer) {
            return (Configuration) super.withRayProducer(rayProducer);
        }

    }

    public static Configuration configuration() {
        return new Configuration();
    }

}
