package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelValue;
import ee.ristoseene.raytracer.eyebased.rasterization.SampleProcessor;

public class UniformGridMultisamplingPixelProcessor extends AbstractMultisamplingPixelProcessor implements PixelProcessor {

    private final int samplesX;
    private final int samplesY;
    private final int sampleCount;

    private final double sampleWidth;
    private final double sampleHeight;
    private final double multiplier;

    public UniformGridMultisamplingPixelProcessor(final Configuration configuration, final int samplesX, final int samplesY) {
        super(configuration);

        if (samplesX < 1 || samplesY < 1) {
            throw new IllegalArgumentException("Invalid sample count: " + samplesX + " x " + samplesY);
        }

        this.samplesX = samplesX;
        this.samplesY = samplesY;

        sampleWidth = 1.0 / samplesX;
        sampleHeight = 1.0 / samplesY;

        sampleCount = samplesX * samplesY;
        multiplier = 1.0 / sampleCount;
    }

    @Override
    public PixelValue processPixel(final PixelLocation pixelLocation, final SampleProcessor sampleProcessor) {
        final SampleValueAccumulator accumulator = sampleValueAccumulatorFactory.create();

        final double pixelX = (double) pixelLocation.getX();
        final double pixelY = (double) pixelLocation.getY();

        for (int sampleY = 0; sampleY < samplesY; ++sampleY) {
            final double y = verticalRasterToViewMapper.map(pixelY + (0.5 + sampleY) * sampleHeight);

            for (int sampleX = 0; sampleX < samplesX; ++sampleX) {
                final double x = horizontalRasterToViewMapper.map(pixelX + (0.5 + sampleX) * sampleWidth);

                accumulator.accumulate(
                        sampleProcessor.processSample(rayProducer.produceRay(x, y), multiplier),
                        multiplier
                );
            }
        }

        return new PixelValue(pixelLocation, accumulator.getValue(), sampleCount);
    }

    public double getSampleCountX() {
        return samplesX;
    }

    public double getSampleCountY() {
        return samplesY;
    }

}
