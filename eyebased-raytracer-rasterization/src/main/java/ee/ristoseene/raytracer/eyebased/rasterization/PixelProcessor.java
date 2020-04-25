package ee.ristoseene.raytracer.eyebased.rasterization;

@FunctionalInterface
public interface PixelProcessor {

    PixelValue processPixel(PixelLocation pixelLocation, SampleProcessor sampleProcessor);

}
