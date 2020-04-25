package ee.ristoseene.raytracer.eyebased.core.raytracing;

@FunctionalInterface
public interface ShadingProcessor {

    SampleValue processShading(
            GeometryContext geometryContext,
            RayIntersectionContext rayIntersectionContext,
            ShadingPipeline shadingPipeline
    );

}
