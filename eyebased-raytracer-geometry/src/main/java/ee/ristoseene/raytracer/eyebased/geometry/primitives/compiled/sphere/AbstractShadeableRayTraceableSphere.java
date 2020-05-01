package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.AbstractShadeableRayTraceablePrimitive;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.mutable.MutableMatrix4x4;

public abstract class AbstractShadeableRayTraceableSphere extends AbstractShadeableRayTraceablePrimitive {

    protected final Matrix4x4.Accessible intersectionPointToNormalTransform;

    protected AbstractShadeableRayTraceableSphere(final Configuration configuration, final double diameter) {
        super(configuration, diameter * 0.5, AbstractShadeableRayTraceableSphere::calculateUnitSphereAABB);

        final Matrix4x4.AccessibleAndMutable normalMatrix = new MutableMatrix4x4(1.0, 1.0, 1.0, 1.0);
        normalMatrix.XYZxyz(globalToUnitLocalSpaceTransform.const$xyzXYZ());

        intersectionPointToNormalTransform = VecMath.multiply(normalMatrix, globalToUnitLocalSpaceTransform, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw);
    }

    protected <R> R transformIntersectionPointToNormal(final RayIntersectionContext rayIntersectionContext, final Vector3.Factory<R> resultFactory) {
        return VecMath.transformPosition(intersectionPointToNormalTransform, rayIntersectionContext.getRayIntersectionPoint(), resultFactory);
    }

    /**
     * This method is called by {@link AbstractShadeableRayTraceableSphere#interactWith(TracingRayContext, TracedState)}
     * if and only if the discriminant (b * b - 4 * a * c) is greater than zero.
     *
     * @param tracedState an instance of {@link TracedState}
     * @param negSqrtDDiv2A -sqrt(b * b - 4 * a * c) / (2 * a)
     * @param negBDiv2A -b / (2 * a)
     */
    protected abstract void processRayInteraction(final TracedState tracedState, final double negSqrtDDiv2A, final double negBDiv2A);

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final TracedState tracedState) {
        final Vector3.Accessible localRayOrigin = localizeRayOrigin(tracingRayContext.getTracingRay());
        final Vector3.Accessible localRayDirection = localizeRayDirection(tracingRayContext.getTracingRay());

        final double a = VecMath.dotSelf(localRayDirection);
        final double b = VecMath.dot(localRayOrigin, localRayDirection) * 2.0;
        final double c = VecMath.dotSelf(localRayOrigin) - 1.0;
        final double discriminant = b * b - 4.0 * a * c;

        if (discriminant <= 0.0) return;

        final double negativeInverseA2 = -0.5 / a;
        final double negSqrtDDiv2A = Math.sqrt(discriminant) * negativeInverseA2;
        final double negBDiv2A = b * negativeInverseA2;

        processRayInteraction(tracedState, negSqrtDDiv2A, negBDiv2A);
    }

    private static AABB calculateUnitSphereAABB(final Matrix4x4.Accessible transform) {
        final Vector3.Accessible extent = new ImmutableVector3(
                VecMath.length(transform.const$xXYZ()),
                VecMath.length(transform.const$yXYZ()),
                VecMath.length(transform.const$zXYZ())
        );
        return new TraceableAABB(
                VecMath.subtract(transform.const$Txyz(), extent, Factories.FACTORY_CONST_VECTOR3_xyz),
                VecMath.add(transform.const$Txyz(), extent, Factories.FACTORY_CONST_VECTOR3_xyz)
        );
    }

}
