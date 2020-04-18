package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.SampleProcessor;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.mutable.MutableVector4;

public class UniformGridMultisamplePixelProcessor extends AbstractPixelProcessor implements PixelProcessor {

    private final int samplesX;
    private final int samplesY;

    private final double sampleWidth;
    private final double sampleHeight;
    private final double multiplier;

    public UniformGridMultisamplePixelProcessor(final Configuration configuration, final int samplesX, final int samplesY) {
        super(configuration);

        if (samplesX < 1 || samplesY < 1) {
            throw new IllegalArgumentException("Invalid sample count: " + samplesX + " x " + samplesY);
        }

        this.samplesX = samplesX;
        this.samplesY = samplesY;

        sampleWidth = 1.0 / samplesX;
        sampleHeight = 1.0 / samplesY;

        multiplier = 1.0 / (samplesX * samplesY);
    }

    @Override
    public Vector4.Accessible processPixel(final PixelLocation pixelLocation, final SampleProcessor sampleProcessor) {
        final Vector4.AccessibleAndMutable accumulator = new MutableVector4(0.0);

        final double pixelX = (double) pixelLocation.getX();
        final double pixelY = (double) pixelLocation.getY();

        for (int sampleY = 0; sampleY < samplesY; ++sampleY) {
            final double y = verticalRasterToViewMapper.map(pixelY + (0.5 + sampleY) * sampleHeight);

            for (int sampleX = 0; sampleX < samplesX; ++sampleX) {
                final double x = horizontalRasterToViewMapper.map(pixelX + (0.5 + sampleX) * sampleWidth);

                final Vector4.Accessible sampleValue = sampleProcessor.processSample(rayProducer.produceRay(x, y), multiplier);

                VecMath.multiplyAdd(accumulator, sampleValue, multiplier, accumulator);
            }
        }

        return accumulator;
    }

    public double getSampleCountX() {
        return samplesX;
    }

    public double getSampleCountY() {
        return samplesY;
    }

}
