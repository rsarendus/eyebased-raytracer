package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractShadeableRayTraceableFlatSurface extends AbstractShadeableRayTraceablePrimitive {

    protected final Axis normalAxis;
    protected final Vector3.Accessible transformedNormal;

    protected AbstractShadeableRayTraceableFlatSurface(
            final Axis normalAxis, final Configuration configuration, final double scale,
            final Function<Matrix4x4.Accessible, AABB> aabbResolver
    ) {
        super(configuration, scale, matrix -> aabbResolver.apply(flattenTransform(matrix, normalAxis)));
        this.normalAxis = Objects.requireNonNull(normalAxis, "Normal axis not provided");

        final Matrix3x3.Accessible normalMatrix = globalToUnitLocalSpaceTransform.const$xyzXYZ();
        this.transformedNormal = VecMath.multiply(normalMatrix, normalAxis, Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz);
    }

    private static Matrix4x4.Accessible flattenTransform(final Matrix4x4.Accessible transform, final Axis normalAxis) {
        final Matrix4x4.Accessible flatteningTransform = Factories.FACTORY_CONST_MATRIX4x4_Dxyz_IDENTITY.create(
                normalAxis.x() == 0.0 ? 1.0 : 0.0,
                normalAxis.y() == 0.0 ? 1.0 : 0.0,
                normalAxis.z() == 0.0 ? 1.0 : 0.0
        );
        return VecMath.multiply(transform, flatteningTransform, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw);
    }

    protected abstract boolean isNotTraceable(double dotRayNormal);
    protected abstract boolean isIntersectionPointInBounds(double x, double y, double z);

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final TracedState tracedState) {
        final Vector3.Accessible localRayOrigin = localizeRayOrigin(tracingRayContext.getTracingRay());
        final Vector3.Accessible localRayDirection = localizeRayDirection(tracingRayContext.getTracingRay());

        final double dotDirectionNormal = VecMath.dot(localRayDirection, normalAxis);

        if (isNotTraceable(dotDirectionNormal)) return;

        final double distance = -VecMath.dot(localRayOrigin, normalAxis) / dotDirectionNormal;

        if (isIntersectionPointInBounds(
                localRayDirection.x() * distance + localRayOrigin.x(),
                localRayDirection.y() * distance + localRayOrigin.y(),
                localRayDirection.z() * distance + localRayOrigin.z()
        )) {
            tracedState.registerRayInteraction(distance, this);
        }
    }

}
