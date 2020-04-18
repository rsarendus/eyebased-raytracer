package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

public interface AABB {

    double getMinimumX();
    double getMaximumX();

    double getMinimumY();
    double getMaximumY();

    double getMinimumZ();
    double getMaximumZ();

    boolean intersects(Ray ray);

    default boolean intersects(final TracingRayContext tracingRayContext) {
        return intersects(tracingRayContext.getTracingRay());
    }

    default boolean isInBounds(final Vector3.Accessible position) {
        return position.x() >= getMinimumX() && position.x() <= getMaximumX()
            && position.y() >= getMinimumY() && position.y() <= getMaximumY()
            && position.z() >= getMinimumZ() && position.z() <= getMaximumZ()
        ;
    }

    default Vector3.Accessible getMinimum() {
        return new ImmutableVector3(getMinimumX(), getMinimumY(), getMinimumZ());
    }

    default Vector3.Accessible getMaximum() {
        return new ImmutableVector3(getMaximumX(), getMaximumY(), getMaximumZ());
    }

}
