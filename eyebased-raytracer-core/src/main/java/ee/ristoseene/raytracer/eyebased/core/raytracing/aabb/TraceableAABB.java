package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.utilities.EfficientSlabTesting;
import ee.ristoseene.vecmath.Vector3;

public class TraceableAABB extends AbstractBoundedAABB implements AABB {

    public TraceableAABB(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        super(p0, p1);
    }

    @Override
    public boolean intersects(final Ray ray, final DepthTest depthTest) {
        return EfficientSlabTesting.testIntersectionWithInversion(
                minimum, maximum, ray.getOrigin(), ray.getDirection(), depthTest
        );
    }

    @Override
    public boolean intersects(final TracingRayContext tracingRayContext, final DepthTest depthTest) {
        return EfficientSlabTesting.testIntersectionOptimized(
                minimum, maximum,
                tracingRayContext.getTracingRay().getOrigin(),
                tracingRayContext.getInverseRayDirection(),
                depthTest
        );
    }

}
