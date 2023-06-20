package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public abstract class AbstractBoundedRayTraceableTriangle extends TraceableAABB implements CompiledGeometry {

    protected AbstractBoundedRayTraceableTriangle(final AABB bounds) {
        super(Objects.requireNonNull(bounds, "Bounds not provided").getMinimum(), bounds.getMaximum());
    }

    protected AbstractBoundedRayTraceableTriangle(final Vector3.Accessible minimum, final Vector3.Accessible maximum) {
        super(minimum, maximum);
    }

    protected abstract boolean isNotTraceable(double determinant);
    protected abstract void processRayInteraction(RayTracedState rayTracedState, Ray tracingRay, double intersectionDistance);

    protected void interactWith(
            final RayTracedState rayTracedState, final Ray tracingRay,
            final Vector3.Accessible v0, final Vector3.Accessible v0v1, final Vector3.Accessible v0v2
    ) {
        final Vector3.Accessible pVec = VecMath.cross(tracingRay.getDirection(), v0v2, Factories.FACTORY_CONST_VECTOR3_xyz);
        final double det = VecMath.dot(v0v1, pVec);

        if (isNotTraceable(det)) return;

        final double invDet = 1.0 / det;

        final Vector3.Accessible tVec = VecMath.subtract(tracingRay.getOrigin(), v0, Factories.FACTORY_CONST_VECTOR3_xyz);
        final double u = VecMath.dot(tVec, pVec) * invDet;
        if (u < 0.0 | u > 1.0) return;

        final Vector3.Accessible qVec = VecMath.cross(tVec, v0v1, Factories.FACTORY_CONST_VECTOR3_xyz);
        final double v = VecMath.dot(tracingRay.getDirection(), qVec) * invDet;
        if (v < 0.0 | u + v > 1.0) return;

        processRayInteraction(rayTracedState, tracingRay, VecMath.dot(v0v2, qVec) * invDet);
    }

    @Override
    public AABB getAABB() {
        return this;
    }

}
