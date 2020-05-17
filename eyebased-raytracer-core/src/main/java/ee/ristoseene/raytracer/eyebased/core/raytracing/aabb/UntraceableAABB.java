package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;

public class UntraceableAABB extends AbstractBoundedAABB implements AABB {

    public UntraceableAABB(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        super(p0, p1);
    }

    @Override
    public boolean intersects(final Ray ray, final DepthTest depthTest) {
        return false;
    }

    @Override
    public boolean intersects(final TracingRayContext tracingRayContext, final DepthTest depthTest) {
        return false;
    }

}
