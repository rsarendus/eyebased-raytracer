package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public abstract class AbstractBoundedAABB implements AABB {

    protected final Vector3.Accessible minimum;
    protected final Vector3.Accessible maximum;

    protected AbstractBoundedAABB(final Vector3.Accessible minimum, final Vector3.Accessible maximum) {
        this.minimum = Objects.requireNonNull(minimum, "Minimum bounds not provided");
        this.maximum = Objects.requireNonNull(maximum, "Maximum bounds not provided");
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
