package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.group.EmptyRayTraceable;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;

public class UnRayTraceableGeometry implements CompiledGeometry, EmptyRayTraceable {

    public static final UnRayTraceableGeometry INSTANCE = new UnRayTraceableGeometry();

    private UnRayTraceableGeometry() {}

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final RayTracedState rayTracedState) {}

    @Override
    public AABB getAABB() {
        return BoundlessAABB.INSTANCE;
    }

}
