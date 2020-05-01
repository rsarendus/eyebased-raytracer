package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.vecmath.Vector3;

public class DoubleSidedShadeableRayTraceableSphere extends AbstractShadeableRayTraceableSphere {

    public DoubleSidedShadeableRayTraceableSphere(final Configuration configuration, final double diameter) {
        super(configuration, diameter);
    }

    @Override
    protected void processRayInteraction(TracedState tracedState, double negSqrtDDiv2A, double negBDiv2A) {
        tracedState.registerRayInteraction(negBDiv2A + negSqrtDDiv2A, this);
        tracedState.registerRayInteraction(negBDiv2A - negSqrtDDiv2A, this);
    }

    @Override
    protected Vector3.Accessible resolveSurfaceNormal(final RayIntersectionContext rayIntersectionContext) {
        final Vector3.Accessible rayDirection = rayIntersectionContext.getIntersectingRay().getDirection();
        return transformIntersectionPointToNormal(rayIntersectionContext, (x, y, z) -> {
            if (rayDirection.x() * x + rayDirection.y() * y + rayDirection.z() * z > 0.0) {
                return Factories.FACTORY_CONST_VECTOR3_NORMALIZED_NEGATED_xyz.create(x, y, z);
            } else {
                return Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(x, y, z);
            }
        });
    }

}
