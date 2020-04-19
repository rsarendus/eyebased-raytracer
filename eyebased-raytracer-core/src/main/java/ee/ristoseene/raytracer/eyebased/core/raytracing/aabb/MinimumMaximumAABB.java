package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public abstract class MinimumMaximumAABB implements AABB {

    protected final Vector3.Accessible minimum;
    protected final Vector3.Accessible maximum;

    protected MinimumMaximumAABB(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        minimum = VecMath.min(p0, p1, Factories.FACTORY_CONST_VECTOR3_xyz);
        maximum = VecMath.max(p0, p1, Factories.FACTORY_CONST_VECTOR3_xyz);
    }

    @Override
    public double getMinimumX() {
        return minimum.x();
    }

    @Override
    public double getMaximumX() {
        return maximum.x();
    }

    @Override
    public double getMinimumY() {
        return minimum.y();
    }

    @Override
    public double getMaximumY() {
        return maximum.y();
    }

    @Override
    public double getMinimumZ() {
        return minimum.z();
    }

    @Override
    public double getMaximumZ() {
        return maximum.z();
    }

    @Override
    public Vector3.Accessible getMinimum() {
        return minimum;
    }

    @Override
    public Vector3.Accessible getMaximum() {
        return maximum;
    }

}
