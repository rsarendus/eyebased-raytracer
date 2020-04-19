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
    Vector2.Accessible VECTOR2_HALF_HALF = new ImmutableVector2(0.5);
    Vector2.Accessible VECTOR2_ONE_ONE = new ImmutableVector2(1.0);

    Vector2.Accessible VECTOR2_minusONE_minusONE = new ImmutableVector2(-1.0);
    Vector2.Accessible VECTOR2_minusHALF_minusHALF = new ImmutableVector2(-0.5);
    Vector2.Accessible VECTOR2_plusHALF_plusHALF = VECTOR2_HALF_HALF;
    Vector2.Accessible VECTOR2_plusONE_plusONE = VECTOR2_ONE_ONE;

    Vector3.Accessible VECTOR3_NaN_NaN_NaN = new ImmutableVector3(Double.NaN);
    Vector3.Accessible VECTOR3_ZERO_ZERO_ZERO = new ImmutableVector3(0.0);
    Vector3.Accessible VECTOR3_HALF_HALF_HALF = new ImmutableVector3(0.5);
    Vector3.Accessible VECTOR3_ONE_ONE_ONE = new ImmutableVector3(1.0);

    Vector3.Accessible VECTOR3_minusONE_minusONE_minusONE = new ImmutableVector3(-1.0);
    Vector3.Accessible VECTOR3_minusHALF_minusHALF_minusHALF = new ImmutableVector3(-0.5);
    Vector3.Accessible VECTOR3_plusHALF_plusHALF_plusHALF = VECTOR3_HALF_HALF_HALF;
    Vector3.Accessible VECTOR3_plusONE_plusONE_plusONE = VECTOR3_ONE_ONE_ONE;

    Vector4.Accessible VECTOR4_NaN_NaN_NaN_NaN = new ImmutableVector4(Double.NaN);
    Vector4.Accessible VECTOR4_ZERO_ZERO_ZERO_ZERO = new ImmutableVector4(0.0);
    Vector4.Accessible VECTOR4_HALF_HALF_HALF_HALF = new ImmutableVector4(0.5);
    Vector4.Accessible VECTOR4_ONE_ONE_ONE_ONE = new ImmutableVector4(1.0);

    Vector4.Accessible VECTOR4_minusONE_minusONE_minusONE_minusONE = new ImmutableVector4(-1.0);
    Vector4.Accessible VECTOR4_minusHALF_minusHALF_minusHALF_minusHALF = new ImmutableVector4(-0.5);
    Vector4.Accessible VECTOR4_plusHALF_plusHALF_plusHALF_plusHALF = VECTOR4_HALF_HALF_HALF_HALF;
    Vector4.Accessible VECTOR4_plusONE_plusONE_plusONE_plusONE = VECTOR4_ONE_ONE_ONE_ONE;

    Vector4.Accessible VECTOR4_ZERO_ZERO_ZERO_ONE = new ImmutableVector4(VECTOR3_ZERO_ZERO_ZERO, 1.0);

}
