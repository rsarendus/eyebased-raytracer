package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class DoubleSidedShadeableRayTraceableDisk extends AbstractShadeableRayTraceableDisk {

    private final Vector3.Accessible backFacingTransformedNormal;

    public DoubleSidedShadeableRayTraceableDisk(final Axis normalAxis, final Configuration configuration, final double diameter) {
        super(normalAxis, configuration, diameter);
        backFacingTransformedNormal = VecMath.negate(transformedNormal, Factories.FACTORY_CONST_VECTOR3_xyz);
    }

    @Override
    protected Vector3.Accessible resolveSurfaceNormal(final RayIntersectionContext rayIntersectionContext) {
        if (VecMath.dot(rayIntersectionContext.getIntersectingRay().getDirection(), transformedNormal) > 0.0) {
            return backFacingTransformedNormal;
        } else {
            return transformedNormal;
        }
    }

    @Override
    protected boolean isNotTraceable(final double dotRayNormal) {
        return dotRayNormal == 0.0;
    }

}
