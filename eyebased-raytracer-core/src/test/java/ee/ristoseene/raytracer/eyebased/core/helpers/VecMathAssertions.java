package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import org.junit.jupiter.api.Assertions;

public final class VecMathAssertions {

    public static void assertEquals(final Vector2.Accessible expected, final Vector2.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector2.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector2.y mismatch");
    }

    public static void assertEquals(final Vector2.Accessible expected, final Vector2.Accessible actual, final Vector2.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector2.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector2.y mismatch");
    }

    public static void assertEquals(final Vector3.Accessible expected, final Vector3.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector3.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector3.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta, "Vector3.z mismatch");
    }

    public static void assertEquals(final Vector3.Accessible expected, final Vector3.Accessible actual, final Vector3.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector3.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector3.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta.z(), "Vector3.z mismatch");
    }

    public static void assertEquals(final Vector4.Accessible expected, final Vector4.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector4.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector4.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta, "Vector4.z mismatch");
        Assertions.assertEquals(expected.w(), actual.w(), delta, "Vector4.w mismatch");
    }

    public static void assertEquals(final Vector4.Accessible expected, final Vector4.Accessible actual, final Vector4.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector4.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector4.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta.z(), "Vector4.z mismatch");
        Assertions.assertEquals(expected.w(), actual.w(), delta.w(), "Vector4.w mismatch");
    }

    public static void assertEquals(final Matrix3x3.Accessible expected, final Matrix3x3.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta, "Matrix3x3.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta, "Matrix3x3.Xy mismatch");
        Assertions.assertEquals(expected.Xz(), actual.Xz(), delta, "Matrix3x3.Xz mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta, "Matrix3x3.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta, "Matrix3x3.Yy mismatch");
        Assertions.assertEquals(expected.Yz(), actual.Yz(), delta, "Matrix3x3.Yz mismatch");
        Assertions.assertEquals(expected.Zx(), actual.Zx(), delta, "Matrix3x3.Zx mismatch");
        Assertions.assertEquals(expected.Zy(), actual.Zy(), delta, "Matrix3x3.Zy mismatch");
        Assertions.assertEquals(expected.Zz(), actual.Zz(), delta, "Matrix3x3.Zz mismatch");
    }

    public static void assertEquals(final Matrix3x3.Accessible expected, final Matrix3x3.Accessible actual, final Matrix3x3.Accessible delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta.Xx(), "Matrix3x3.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta.Xy(), "Matrix3x3.Xy mismatch");
        Assertions.assertEquals(expected.Xz(), actual.Xz(), delta.Xz(), "Matrix3x3.Xz mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta.Yx(), "Matrix3x3.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta.Yy(), "Matrix3x3.Yy mismatch");
        Assertions.assertEquals(expected.Yz(), actual.Yz(), delta.Yz(), "Matrix3x3.Yz mismatch");
        Assertions.assertEquals(expected.Zx(), actual.Zx(), delta.Zx(), "Matrix3x3.Zx mismatch");
        Assertions.assertEquals(expected.Zy(), actual.Zy(), delta.Zy(), "Matrix3x3.Zy mismatch");
        Assertions.assertEquals(expected.Zz(), actual.Zz(), delta.Zz(), "Matrix3x3.Zz mismatch");
    }

    public static void assertEquals(final Matrix4x4.Accessible expected, final Matrix4x4.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta, "Matrix4x4.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta, "Matrix4x4.Xy mismatch");
        Assertions.assertEquals(expected.Xz(), actual.Xz(), delta, "Matrix4x4.Xz mismatch");
        Assertions.assertEquals(expected.Xw(), actual.Xw(), delta, "Matrix4x4.Xw mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta, "Matrix4x4.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta, "Matrix4x4.Yy mismatch");
        Assertions.assertEquals(expected.Yz(), actual.Yz(), delta, "Matrix4x4.Yz mismatch");
        Assertions.assertEquals(expected.Yw(), actual.Yw(), delta, "Matrix4x4.Yw mismatch");
        Assertions.assertEquals(expected.Zx(), actual.Zx(), delta, "Matrix4x4.Zx mismatch");
        Assertions.assertEquals(expected.Zy(), actual.Zy(), delta, "Matrix4x4.Zy mismatch");
        Assertions.assertEquals(expected.Zz(), actual.Zz(), delta, "Matrix4x4.Zz mismatch");
        Assertions.assertEquals(expected.Zw(), actual.Zw(), delta, "Matrix4x4.Zw mismatch");
        Assertions.assertEquals(expected.Tx(), actual.Tx(), delta, "Matrix4x4.Tx mismatch");
        Assertions.assertEquals(expected.Ty(), actual.Ty(), delta, "Matrix4x4.Ty mismatch");
        Assertions.assertEquals(expected.Tz(), actual.Tz(), delta, "Matrix4x4.Tz mismatch");
        Assertions.assertEquals(expected.Tw(), actual.Tw(), delta, "Matrix4x4.Tw mismatch");
    }

    public static void assertEquals(final Matrix4x4.Accessible expected, final Matrix4x4.Accessible actual, final Matrix4x4.Accessible delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta.Xx(), "Matrix4x4.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta.Xy(), "Matrix4x4.Xy mismatch");
        Assertions.assertEquals(expected.Xz(), actual.Xz(), delta.Xz(), "Matrix4x4.Xz mismatch");
        Assertions.assertEquals(expected.Xw(), actual.Xw(), delta.Xw(), "Matrix4x4.Xw mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta.Yx(), "Matrix4x4.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta.Yy(), "Matrix4x4.Yy mismatch");
        Assertions.assertEquals(expected.Yz(), actual.Yz(), delta.Yz(), "Matrix4x4.Yz mismatch");
        Assertions.assertEquals(expected.Yw(), actual.Yw(), delta.Yw(), "Matrix4x4.Yw mismatch");
        Assertions.assertEquals(expected.Zx(), actual.Zx(), delta.Zx(), "Matrix4x4.Zx mismatch");
        Assertions.assertEquals(expected.Zy(), actual.Zy(), delta.Zy(), "Matrix4x4.Zy mismatch");
        Assertions.assertEquals(expected.Zz(), actual.Zz(), delta.Zz(), "Matrix4x4.Zz mismatch");
        Assertions.assertEquals(expected.Zw(), actual.Zw(), delta.Zw(), "Matrix4x4.Zw mismatch");
        Assertions.assertEquals(expected.Tx(), actual.Tx(), delta.Tx(), "Matrix4x4.Tx mismatch");
        Assertions.assertEquals(expected.Ty(), actual.Ty(), delta.Ty(), "Matrix4x4.Ty mismatch");
        Assertions.assertEquals(expected.Tz(), actual.Tz(), delta.Tz(), "Matrix4x4.Tz mismatch");
        Assertions.assertEquals(expected.Tw(), actual.Tw(), delta.Tw(), "Matrix4x4.Tw mismatch");
    }

    private VecMathAssertions() {}

}
