package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.vecmath.Vector3;

public class SimpleRayIntersectionContext implements RayIntersectionContext {

    private final Ray intersectingRay;
    private final Vector3.Accessible rayIntersectionPoint;
    private final double rayIntersectionDistance;

    public SimpleRayIntersectionContext(final Ray tracingRay, final double intersectionDistance) {
        intersectingRay = tracingRay;
        rayIntersectionPoint = tracingRay.getPointOnRay(intersectionDistance, Factories.FACTORY_CONST_VECTOR3_xyz);
        rayIntersectionDistance = intersectionDistance;
    }

    @Override
    public Ray getIntersectingRay() {
        return intersectingRay;
    }

    @Override
    public Vector3.Accessible getRayIntersectionPoint() {
        return rayIntersectionPoint;
    }

    @Override
    public double getRayIntersectionDistance() {
        return rayIntersectionDistance;
    }

}
