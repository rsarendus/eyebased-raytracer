package ee.ristoseene.raytracer.eyebased.core.constants;

import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableVector2;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;

public interface Vectors {

    Vector2.Accessible VECTOR2_NaN_NaN = new ImmutableVector2(Double.NaN);
    Vector2.Accessible VECTOR2_ZERO_ZERO = new ImmutableVector2(0.0);
    Vector2.Accessible VECTOR2_ONE_ONE = new ImmutableVector2(1.0);

    Vector3.Accessible VECTOR3_NaN_NaN_NaN = new ImmutableVector3(Double.NaN);
    Vector3.Accessible VECTOR3_ZERO_ZERO_ZERO = new ImmutableVector3(0.0);
    Vector3.Accessible VECTOR3_ONE_ONE_ONE = new ImmutableVector3(1.0);

    Vector4.Accessible VECTOR4_NaN_NaN_NaN_NaN = new ImmutableVector4(Double.NaN);
    Vector4.Accessible VECTOR4_ZERO_ZERO_ZERO_ZERO = new ImmutableVector4(0.0);
    Vector4.Accessible VECTOR4_ONE_ONE_ONE_ONE = new ImmutableVector4(1.0);

    Vector4.Accessible VECTOR4_ZERO_ZERO_ZERO_ONE = new ImmutableVector4(VECTOR3_ZERO_ZERO_ZERO, 1.0);

}
