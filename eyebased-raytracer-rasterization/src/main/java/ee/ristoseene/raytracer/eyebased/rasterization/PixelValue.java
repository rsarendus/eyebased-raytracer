package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;

public class PixelValue {

    private final PixelLocation pixelLocation;
    private final SampleValue sampleValue;
    private final int sampleCount;

    public PixelValue(final PixelLocation pixelLocation, final SampleValue sampleValue, final int sampleCount) {
        this.pixelLocation = pixelLocation;
        this.sampleValue = sampleValue;
        this.sampleCount = sampleCount;
    }

    public PixelLocation getPixelLocation() {
        return pixelLocation;
    }

    public SampleValue getSampleValue() {
        return sampleValue;
    }

    public int getSampleCount() {
        return sampleCount;
    }

}
