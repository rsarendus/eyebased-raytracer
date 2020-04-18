package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector4;

@FunctionalInterface
public interface ShadingProcessor {

    Vector4.Accessible processShading(
            GeometryContext geometryContext,
            RayIntersectionContext rayIntersectionContext,
            ShadingPipeline shadingPipeline
    );

}
