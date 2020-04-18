package ee.ristoseene.raytracer.eyebased.image;

import ee.ristoseene.vecmath.Vector4;

public interface Sampler2D {

    void sample(Vector4.Consumer destinationRGBA, double x, double y);

}
