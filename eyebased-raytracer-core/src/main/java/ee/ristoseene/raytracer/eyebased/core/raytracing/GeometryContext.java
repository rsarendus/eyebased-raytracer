package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector3;

public interface GeometryContext {

    Vector3.Accessible getPosition();
    Vector3.Accessible getSurfaceNormal();

    default <T> T getAttributeValue(final Attribute<T> attribute) {
        return attribute.getDefaultValue();
    }

    interface Attribute<T> {

        Class<T> getValueType();
        T getDefaultValue();

    }

}
