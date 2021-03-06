package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.vecmath.Vector3;

public class OutwardFacingShadeableRayTraceableSphere extends AbstractShadeableRayTraceableSphere {

    public OutwardFacingShadeableRayTraceableSphere(final Configuration configuration, final double diameter) {
        super(configuration, diameter);
    }

    @Override
    protected void processRayInteraction(RayTracedState rayTracedState, double negSqrtDDiv2A, double negBDiv2A) {
        rayTracedState.registerRayInteraction(negBDiv2A + negSqrtDDiv2A, this);
    }

    @Override
    protected Vector3.Accessible resolveSurfaceNormal(final RayIntersectionContext rayIntersectionContext) {
        return transformIntersectionPointToNormal(rayIntersectionContext, Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz);
    }

}
