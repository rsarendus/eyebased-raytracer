package ee.ristoseene.raytracer.eyebased.core.raytracing;

@FunctionalInterface
public interface Shadeable {

    SampleValue shade(RayIntersectionContext rayIntersectionContext, ShadingProcessor shadingProcessor);

}
