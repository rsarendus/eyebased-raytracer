package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;

public interface RayTraceable extends CompiledObject {

    void interactWith(TracingRayContext tracingRayContext, RayTracedState rayTracedState);

    AABB getAABB();

}
