package ee.ristoseene.raytracer.eyebased.core.constants;

import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableQuaternion;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions.assertEquals;

class FactoriesTest implements Factories {

    @Test
    public void constVector3xyzShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector3.Accessible result = FACTORY_CONST_VECTOR3_xyz.create(1.1, 2.2, 3.3);
        assertEquals(new ImmutableVector3(1.1, 2.2, 3.3), result, 0.0);
        Assertions.assertFalse(result instanceof Vector3.Mutable);
    }

    @Test
    public void constVector3xyzNegatedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector3.Accessible result = FACTORY_CONST_VECTOR3_NEGATED_xyz.create(1.1, 2.2, 3.3);
        assertEquals(new ImmutableVector3(-1.1, -2.2, -3.3), result, 0.0);
        Assertions.assertFalse(result instanceof Vector3.Mutable);
    }

    @Test
    public void constVector3xyzNormalizedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector3.Accessible result = FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.1, 2.2, 3.3);
        assertEquals(VecMath.normalize(new ImmutableVector3(1.1, 2.2, 3.3), ImmutableVector3::new), result, 0.000001);
        Assertions.assertFalse(result instanceof Vector3.Mutable);
    }

    @Test
    public void constVector3xyzNormalizedNegatedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector3.Accessible result = FACTORY_CONST_VECTOR3_NORMALIZED_NEGATED_xyz.create(1.1, 2.2, 3.3);
        assertEquals(VecMath.normalize(new ImmutableVector3(-1.1, -2.2, -3.3), ImmutableVector3::new), result, 0.000001);
        Assertions.assertFalse(result instanceof Vector3.Mutable);
    }

    @Test
    public void constVector4xyzwShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_xyzw.create(1.1, 2.2, 3.3, 4.4);
        assertEquals(new ImmutableVector4(1.1, 2.2, 3.3, 4.4), result, 0.0);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constVector4xyzwNegatedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_NEGATED_xyzw.create(1.1, 2.2, 3.3, 4.4);
        assertEquals(new ImmutableVector4(-1.1, -2.2, -3.3, -4.4), result, 0.0);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constVector4xyzwNormalizedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_NORMALIZED_xyzw.create(1.1, 2.2, 3.3, 4.4);
        assertEquals(VecMath.normalize(new ImmutableVector4(1.1, 2.2, 3.3, 4.4), ImmutableVector4::new), result, 0.000001);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constVector4xyzwNormalizedNegatedShouldCreateImmutableVectorWithSpecifiedComponents() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_NORMALIZED_NEGATED_xyzw.create(1.1, 2.2, 3.3, 4.4);
        assertEquals(VecMath.normalize(new ImmutableVector4(-1.1, -2.2, -3.3, -4.4), ImmutableVector4::new), result, 0.000001);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constVector4xyz0ShouldCreateImmutableVectorWithSpecifiedComponentsAndZeroAsLastComponent() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_xyz0.create(1.1, 2.2, 3.3);
        assertEquals(new ImmutableVector4(1.1, 2.2, 3.3, 0.0), result, 0.0);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constVector4xyz0ShouldCreateImmutableVectorWithSpecifiedComponentsAndOneAsLastComponent() {
        Vector4.Accessible result = FACTORY_CONST_VECTOR4_xyz1.create(1.1, 2.2, 3.3);
        assertEquals(new ImmutableVector4(1.1, 2.2, 3.3, 1.0), result, 0.0);
        Assertions.assertFalse(result instanceof Vector4.Mutable);
    }

    @Test
    public void constMatrix3x3DxyzShouldCreateImmutableMatrixWithSpecifiedDiagonal() {
        Matrix3x3.Accessible result = FACTORY_CONST_MATRIX3x3_Dxyz.create(
                1.1, 2.2, 3.3
        );
        assertEquals(new ImmutableMatrix3x3(
                1.1, 0.0, 0.0,
                0.0, 2.2, 0.0,
                0.0, 0.0, 3.3
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix3x3.Mutable);
    }

    @Test
    public void constMatrix3x3XYZxyzShouldCreateImmutableMatrixWithSpecifiedElements() {
        Matrix3x3.Accessible result = FACTORY_CONST_MATRIX3x3_XYZxyz.create(
                1.1, 2.2, 3.3,
                4.4, 5.5, 6.6,
                7.7, 8.8, 9.9
        );
        assertEquals(new ImmutableMatrix3x3(
                1.1, 2.2, 3.3,
                4.4, 5.5, 6.6,
                7.7, 8.8, 9.9
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix3x3.Mutable);
    }

    @Test
    public void constMatrix4x4DxyzIdentityShouldCreateImmutableMatrixWithDiagonal3AndExtendItByIdentity() {
        Matrix4x4.Accessible result = FACTORY_CONST_MATRIX4x4_Dxyz_IDENTITY.create(
                1.1, 2.2, 3.3
        );
        assertEquals(new ImmutableMatrix4x4(
                1.1, 0.0, 0.0, 0.0,
                0.0, 2.2, 0.0, 0.0,
                0.0, 0.0, 3.3, 0.0,
                0.0, 0.0, 0.0, 1.0
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix4x4.Mutable);
    }

    @Test
    public void constMatrix4x4XYZxyzIdentityShouldCreateImmutableMatrixWithSpecified3x3ElementsAndExtendItByIdentity() {
        Matrix4x4.Accessible result = FACTORY_CONST_MATRIX4x4_XYZxyz_IDENTITY.create(
                1.1, 2.2, 3.3,
                4.4, 5.5, 6.6,
                7.7, 8.8, 9.9
        );
        assertEquals(new ImmutableMatrix4x4(
                1.1, 2.2, 3.3, 0.0,
                4.4, 5.5, 6.6, 0.0,
                7.7, 8.8, 9.9, 0.0,
                0.0, 0.0, 0.0, 1.0
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix4x4.Mutable);
    }

    @Test
    public void constMatrix4x4DxyzwShouldCreateImmutableMatrixWithSpecifiedDiagonal() {
        Matrix4x4.Accessible result = FACTORY_CONST_MATRIX4x4_Dxyzw.create(
                1.1, 2.2, 3.3, 4.4
        );
        assertEquals(new ImmutableMatrix4x4(
                1.1, 0.0, 0.0, 0.0,
                0.0, 2.2, 0.0, 0.0,
                0.0, 0.0, 3.3, 0.0,
                0.0, 0.0, 0.0, 4.4
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix4x4.Mutable);
    }

    @Test
    public void constMatrix4x4XYZTxyzwShouldCreateImmutableMatrixWithSpecifiedElements() {
        Matrix4x4.Accessible result = FACTORY_CONST_MATRIX4x4_XYZTxyzw.create(
                1.1, 2.2, 3.3, 4.4,
                5.5, 6.6, 7.7, 8.8,
                9.9, 10.1, 11.11, 12.12,
                13.13, 14.14, 15.15, 16.16
        );
        assertEquals(new ImmutableMatrix4x4(
                1.1, 2.2, 3.3, 4.4,
                5.5, 6.6, 7.7, 8.8,
                9.9, 10.1, 11.11, 12.12,
                13.13, 14.14, 15.15, 16.16
        ), result, 0.0);
        Assertions.assertFalse(result instanceof Matrix4x4.Mutable);
    }

    @Test
    public void constQuaternion_xyzwShouldCreateImmutableQuaternionWithSpecifiedComponents() {
        Quaternion.Accessible result = FACTORY_CONST_QUATERNION_xyzw.create(1.1, 2.2, 3.3, 4.4);
        assertEquals(new ImmutableQuaternion(1.1, 2.2, 3.3, 4.4), result, 0.0);
        Assertions.assertFalse(result instanceof Quaternion.Mutable);
    }

}
