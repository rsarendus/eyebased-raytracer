package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class AABBBuilder {

    public interface Factory<T extends AABB> {
        T create(Vector3.Accessible minimum, Vector3.Accessible maximum);
    }

    private double minimumX, minimumY, minimumZ;
    private double maximumX, maximumY, maximumZ;
    private boolean hasX, hasY, hasZ;

    private void addX(final double x) {
        if (hasX) {
            minimumX = VecMath.min(minimumX, x);
            maximumX = VecMath.max(maximumX, x);
        } else if (!Double.isNaN(x)) {
            minimumX = x;
            maximumX = x;
            hasX = true;
        }
    }

    private void addY(final double y) {
        if (hasY) {
            minimumY = VecMath.min(minimumY, y);
            maximumY = VecMath.max(maximumY, y);
        } else if (!Double.isNaN(y)) {
            minimumY = y;
            maximumY = y;
            hasY = true;
        }
    }

    private void addZ(final double z) {
        if (hasZ) {
            minimumZ = VecMath.min(minimumZ, z);
            maximumZ = VecMath.max(maximumZ, z);
        } else if (!Double.isNaN(z)) {
            minimumZ = z;
            maximumZ = z;
            hasZ = true;
        }
    }

    public AABBBuilder addPoint(final Vector3.Accessible point) {
        addX(point.x());
        addY(point.y());
        addZ(point.z());
        return this;
    }

    public AABBBuilder addAABB(final AABB aabb) {
        addPoint(aabb.getMinimum());
        addPoint(aabb.getMaximum());
        return this;
    }

    public double getMinimumX() {
        if (hasX) {
            return minimumX;
        } else {
            throw new IllegalStateException("Minimum X not set");
        }
    }

    public double getMaximumX() {
        if (hasX) {
            return maximumX;
        } else {
            throw new IllegalStateException("Maximum X not set");
        }
    }

    public double getMinimumY() {
        if (hasY) {
            return minimumY;
        } else {
            throw new IllegalStateException("Minimum Y not set");
        }
    }

    public double getMaximumY() {
        if (hasY) {
            return maximumY;
        } else {
            throw new IllegalStateException("Maximum Y not set");
        }
    }

    public double getMinimumZ() {
        if (hasZ) {
            return minimumZ;
        } else {
            throw new IllegalStateException("Minimum Z not set");
        }
    }

    public double getMaximumZ() {
        if (hasZ) {
            return maximumZ;
        } else {
            throw new IllegalStateException("Maximum Z not set");
        }
    }

    public Vector3.Accessible getMinimum() {
        return Factories.FACTORY_CONST_VECTOR3_xyz.create(getMinimumX(), getMinimumY(), getMinimumZ());
    }

    public Vector3.Accessible getMaximum() {
        return Factories.FACTORY_CONST_VECTOR3_xyz.create(getMaximumX(), getMaximumY(), getMaximumZ());
    }

    public <T extends AABB> T build(final Factory<T> aabbFactory) {
        return aabbFactory.create(getMinimum(), getMaximum());
    }

}
