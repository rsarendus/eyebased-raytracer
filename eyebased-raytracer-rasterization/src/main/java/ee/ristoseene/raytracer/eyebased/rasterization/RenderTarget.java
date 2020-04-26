package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.image.Image2D;

public interface RenderTarget extends Image2D {

    void writePixel(PixelValue pixelValue);

}
