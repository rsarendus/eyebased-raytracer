package ee.ristoseene.raytracer.eyebased.core.image;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public interface Sampler2D {

    void sample(Vector4.Mutable destinationRGBA, double x, double y);

}
