package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;

public class UntraceableAABB extends MinimumMaximumAABB implements AABB {

    public UntraceableAABB(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        super(p0, p1);
    }

    @Override
    public boolean intersects(final Ray ray) {
        return false;
    }

    @Override
    public boolean intersects(final TracingRayContext tracingRayContext) {
        return false;
    }

}
