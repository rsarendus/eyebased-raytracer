package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector4;

@FunctionalInterface
public interface Shadeable {

    Vector4.Accessible shade(RayIntersectionContext rayIntersectionContext, ShadingProcessor shadingProcessor);

}
