package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.vecmath.Vector2;

public interface GeometryAttributes {

    TypedAttribute<Vector2.Accessible> TEXTURE_COORDINATES = new TypedAttribute<>() {

        @Override
        public Class<Vector2.Accessible> getValueType() {
            return Vector2.Accessible.class;
        }

        @Override
        public Vector2.Accessible getDefaultValue() {
            return Vectors.VECTOR2_NaN_NaN;
        }

    };

}
