package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;

public final class BoundlessAABB implements AABB {

    public static final BoundlessAABB INSTANCE = new BoundlessAABB();

    private BoundlessAABB() {}

    @Override
    public double getMinimumX() {
        return Double.NaN;
    }

    @Override
    public double getMaximumX() {
        return Double.NaN;
    }

    @Override
    public double getMinimumY() {
        return Double.NaN;
    }

    @Override
    public double getMaximumY() {
        return Double.NaN;
    }

    @Override
    public double getMinimumZ() {
        return Double.NaN;
    }

    @Override
    public double getMaximumZ() {
        return Double.NaN;
    }

    @Override
    public boolean intersects(final Ray ray, final DepthTest depthTest) {
        return false;
    }

    @Override
    public boolean intersects(final TracingRayContext tracingRayContext, final DepthTest depthTest) {
        return false;
    }

    @Override
    public boolean isInBounds(final Vector3.Accessible position) {
        return false;
    }

    @Override
    public Vector3.Accessible getMinimum() {
        return Vectors.VECTOR3_NaN_NaN_NaN;
    }

    @Override
    public Vector3.Accessible getMaximum() {
        return Vectors.VECTOR3_NaN_NaN_NaN;
    }

}
