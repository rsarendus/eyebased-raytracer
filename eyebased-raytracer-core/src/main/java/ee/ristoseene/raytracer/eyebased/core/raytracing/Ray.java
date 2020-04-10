package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public interface Ray {

    Vector3.Accessible getOrigin();
    Vector3.Accessible getDirection();

    default <R> R getPointOnRay(final double distanceFromOrigin, final Vector3.Factory<R> resultFactory) {
        return VecMath.multiplyAdd(getDirection(), distanceFromOrigin, getOrigin(), resultFactory);
    }

    default void getPointOnRay(final Vector3.Consumer resultConsumer, final double distanceFromOrigin) {
        VecMath.multiplyAdd(resultConsumer, getDirection(), distanceFromOrigin, getOrigin());
    }

}
