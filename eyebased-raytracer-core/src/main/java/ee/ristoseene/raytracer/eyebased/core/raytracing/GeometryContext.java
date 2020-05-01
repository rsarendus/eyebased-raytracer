package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.vecmath.Vector3;

public interface GeometryContext {

    GeometryContext NO_OP_INSTANCE = new GeometryContext() {};

    default Vector3.Accessible getPosition() {
        return Vectors.VECTOR3_NaN_NaN_NaN;
    }

    default Vector3.Accessible getSurfaceNormal() {
        return Vectors.VECTOR3_ZERO_ZERO_ZERO;
    }

    default <T> T getAttributeValue(final TypedAttribute<T> key) {
        return key.getDefaultValue();
    }

}
