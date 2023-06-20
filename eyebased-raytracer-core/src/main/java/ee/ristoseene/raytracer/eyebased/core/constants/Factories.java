package ee.ristoseene.raytracer.eyebased.core.constants;

import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableQuaternion;
import ee.ristoseene.vecmath.immutable.ImmutableVector2;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;

public interface Factories {

    Vector2.Factory<Vector2.Accessible> FACTORY_CONST_VECTOR2_xy = ImmutableVector2::new;
    Vector2.Factory<Vector2.Accessible> FACTORY_CONST_VECTOR2_NEGATED_xy = (x, y) -> FACTORY_CONST_VECTOR2_xy.create(-x, -y);
    Vector2.Factory<Vector2.Accessible> FACTORY_CONST_VECTOR2_NORMALIZED_xy = (x, y) -> VecMathExtended.normalize(x, y, FACTORY_CONST_VECTOR2_xy);
    Vector2.Factory<Vector2.Accessible> FACTORY_CONST_VECTOR2_NORMALIZED_NEGATED_xy = (x, y) -> FACTORY_CONST_VECTOR2_NORMALIZED_xy.create(-x, -y);

    Vector3.Factory<Vector3.Accessible> FACTORY_CONST_VECTOR3_xyz = ImmutableVector3::new;
    Vector3.Factory<Vector3.Accessible> FACTORY_CONST_VECTOR3_NEGATED_xyz = (x, y, z) -> FACTORY_CONST_VECTOR3_xyz.create(-x, -y, -z);
    Vector3.Factory<Vector3.Accessible> FACTORY_CONST_VECTOR3_NORMALIZED_xyz = (x, y, z) -> VecMathExtended.normalize(x, y, z, FACTORY_CONST_VECTOR3_xyz);
    Vector3.Factory<Vector3.Accessible> FACTORY_CONST_VECTOR3_NORMALIZED_NEGATED_xyz = (x, y, z) -> FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(-x, -y, -z);

    Vector4.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyzw = ImmutableVector4::new;
    Vector4.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_NEGATED_xyzw = (x, y, z, w) -> FACTORY_CONST_VECTOR4_xyzw.create(-x, -y, -z, -w);
    Vector4.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_NORMALIZED_xyzw = (x, y, z, w) -> VecMathExtended.normalize(x, y, z, w, FACTORY_CONST_VECTOR4_xyzw);
    Vector4.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_NORMALIZED_NEGATED_xyzw = (x, y, z, w) -> FACTORY_CONST_VECTOR4_NORMALIZED_xyzw.create(-x, -y, -z, -w);

    Vector3.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyz0 = (x, y, z) -> FACTORY_CONST_VECTOR4_xyzw.create(x, y, z, 0.0);
    Vector3.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyz1 = (x, y, z) -> FACTORY_CONST_VECTOR4_xyzw.create(x, y, z, 1.0);

    Vector3.Factory<Matrix3x3.Accessible> FACTORY_CONST_MATRIX3x3_Dxyz = ImmutableMatrix3x3::new;
    Matrix3x3.Factory<Matrix3x3.Accessible> FACTORY_CONST_MATRIX3x3_XYZxyz = ImmutableMatrix3x3::new;

    Vector3.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_Dxyz_IDENTITY = (Xx, Yy, Zz) -> new ImmutableMatrix4x4(Xx, Yy, Zz, 1.0);
    Matrix3x3.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_XYZxyz_IDENTITY = (Xx, Xy, Xz, Yx, Yy, Yz, Zx, Zy, Zz) ->
            new ImmutableMatrix4x4(Xx, Xy, Xz, 0.0, Yx, Yy, Yz, 0.0, Zx, Zy, Zz, 0.0, 0.0, 0.0, 0.0, 1.0);

    Vector4.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_Dxyzw = ImmutableMatrix4x4::new;
    Matrix4x4.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_XYZTxyzw = ImmutableMatrix4x4::new;

    Quaternion.Factory<Quaternion.Accessible> FACTORY_CONST_QUATERNION_xyzw = ImmutableQuaternion::new;

}
