package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.SampleProcessor;
import ee.ristoseene.vecmath.Vector4;

public class SingleSamplePixelProcessor extends AbstractPixelProcessor implements PixelProcessor {

    public SingleSamplePixelProcessor(final Configuration configuration) {
        super(configuration);
    }

    @Override
    public Vector4.Accessible processPixel(final PixelLocation pixelLocation, final SampleProcessor sampleProcessor) {
        final double x = horizontalRasterToViewMapper.map(pixelLocation.getX() + 0.5);
        final double y = verticalRasterToViewMapper.map(pixelLocation.getY() + 0.5);

        return sampleProcessor.processSample(rayProducer.produceRay(x, y), 1.0);
    }

}
