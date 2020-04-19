package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class SimpleTracingRayContext implements TracingRayContext {

    private final Ray tracingRay;
    private final Vector3.Accessible inverseRayDirection;

    public SimpleTracingRayContext(final Ray tracingRay) {
        this.inverseRayDirection = VecMath.divide(1.0, tracingRay.getDirection(), Factories.FACTORY_CONST_VECTOR3_xyz);
        this.tracingRay = tracingRay;
    }

    @Override
    public Ray getTracingRay() {
        return tracingRay;
    }

    @Override
    public Vector3.Accessible getInverseRayDirection() {
        return inverseRayDirection;
    }

}
