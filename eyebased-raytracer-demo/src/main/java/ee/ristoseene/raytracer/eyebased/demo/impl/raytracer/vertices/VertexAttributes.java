package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.GeometryAttributes;
import ee.ristoseene.vecmath.Vector3;

public interface VertexAttributes extends GeometryAttributes {

    TypedAttribute<Vector3.Accessible> VERTEX_POSITION = new TypedAttribute<>() {

        @Override
        public Class<Vector3.Accessible> getValueType() {
            return Vector3.Accessible.class;
        }

        @Override
        public Vector3.Accessible getDefaultValue() {
            return Vectors.VECTOR3_NaN_NaN_NaN;
        }

    };

    TypedAttribute<Vector3.Accessible> VERTEX_NORMAL = new TypedAttribute<>() {

        @Override
        public Class<Vector3.Accessible> getValueType() {
            return Vector3.Accessible.class;
        }

        @Override
        public Vector3.Accessible getDefaultValue() {
            return Vectors.VECTOR3_ZERO_ZERO_ZERO;
        }

    };

}
