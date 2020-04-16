package ee.ristoseene.raytracer.eyebased.core.constants;

import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableQuaternion;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;

public interface Factories {

    Vector3.Factory<Vector3.Accessible> FACTORY_CONST_VECTOR3_xyz = ImmutableVector3::new;

    Vector3.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyz0 = (x, y, z) -> new ImmutableVector4(x, y, z, 0.0);
    Vector3.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyz1 = (x, y, z) -> new ImmutableVector4(x, y, z, 1.0);

    Vector4.Factory<Vector4.Accessible> FACTORY_CONST_VECTOR4_xyzw = ImmutableVector4::new;

    Matrix3x3.Factory<Matrix3x3.Accessible> FACTORY_CONST_MATRIX3x3_XYZxyz = ImmutableMatrix3x3::new;

    Matrix3x3.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_XYZxyz_IDENTITY = (Xx, Xy, Xz, Yx, Yy, Yz, Zx, Zy, Zz) ->
            new ImmutableMatrix4x4(Xx, Xy, Xz, 0.0, Yx, Yy, Yz, 0.0, Zx, Zy, Zz, 0.0, 0.0, 0.0, 0.0, 1.0);

    Matrix4x4.Factory<Matrix4x4.Accessible> FACTORY_CONST_MATRIX4x4_XYZTxyzw = ImmutableMatrix4x4::new;

    Quaternion.Factory<Quaternion.Accessible> FACTORY_CONST_QUATERNION_xyzw = ImmutableQuaternion::new;

}
