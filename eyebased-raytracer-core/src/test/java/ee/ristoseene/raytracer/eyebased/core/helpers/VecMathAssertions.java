package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Matrix2x2;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import org.junit.jupiter.api.Assertions;

import java.util.function.DoublePredicate;

public interface VecMathAssertions {

    static void assertEquals(final Vector2.Accessible expected, final Vector2.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector2.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector2.y mismatch");
    }

    static void assertEquals(final Vector2.Accessible expected, final Vector2.Accessible actual, final Vector2.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector2.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector2.y mismatch");
    }

    static void assertMatches(final DoublePredicate matcher, final Vector2.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.x()), "Vector2.x " + reason);
        Assertions.assertTrue(matcher.test(actual.y()), "Vector2.y " + reason);
    }

    static void assertEquals(final Vector3.Accessible expected, final Vector3.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector3.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector3.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta, "Vector3.z mismatch");
    }

    static void assertEquals(final Vector3.Accessible expected, final Vector3.Accessible actual, final Vector3.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector3.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector3.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta.z(), "Vector3.z mismatch");
    }

    static void assertMatches(final DoublePredicate matcher, final Vector3.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.x()), "Vector3.x " + reason);
        Assertions.assertTrue(matcher.test(actual.y()), "Vector3.y " + reason);
        Assertions.assertTrue(matcher.test(actual.z()), "Vector3.z " + reason);
    }

    static void assertEquals(final Vector4.Accessible expected, final Vector4.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta, "Vector4.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta, "Vector4.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta, "Vector4.z mismatch");
        Assertions.assertEquals(expected.w(), actual.w(), delta, "Vector4.w mismatch");
    }

    static void assertEquals(final Vector4.Accessible expected, final Vector4.Accessible actual, final Vector4.Accessible delta) {
        Assertions.assertEquals(expected.x(), actual.x(), delta.x(), "Vector4.x mismatch");
        Assertions.assertEquals(expected.y(), actual.y(), delta.y(), "Vector4.y mismatch");
        Assertions.assertEquals(expected.z(), actual.z(), delta.z(), "Vector4.z mismatch");
        Assertions.assertEquals(expected.w(), actual.w(), delta.w(), "Vector4.w mismatch");
    }

    static void assertMatches(final DoublePredicate matcher, final Vector4.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.x()), "Vector4.x " + reason);
        Assertions.assertTrue(matcher.test(actual.y()), "Vector4.y " + reason);
        Assertions.assertTrue(matcher.test(actual.z()), "Vector4.z " + reason);
        Assertions.assertTrue(matcher.test(actual.w()), "Vector4.w " + reason);
    }

    static void assertEquals(final Matrix2x2.Accessible expected, final Matrix2x2.Accessible actual, final double delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta, "Matrix2x2.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta, "Matrix2x2.Xy mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta, "Matrix2x2.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta, "Matrix2x2.Yy mismatch");
    }

    static void assertEquals(final Matrix2x2.Accessible expected, final Matrix2x2.Accessible actual, final Matrix2x2.Accessible delta) {
        Assertions.assertEquals(expected.Xx(), actual.Xx(), delta.Xx(), "Matrix2x2.Xx mismatch");
        Assertions.assertEquals(expected.Xy(), actual.Xy(), delta.Xy(), "Matrix2x2.Xy mismatch");
        Assertions.assertEquals(expected.Yx(), actual.Yx(), delta.Yx(), "Matrix2x2.Yx mismatch");
        Assertions.assertEquals(expected.Yy(), actual.Yy(), delta.Yy(), "Matrix2x2.Yy mismatch");
    }

    static void assertMatches(final DoublePredicate matcher, final Matrix2x2.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.Xx()), "Matrix2x2.Xx " + reason);
        Assertions.assertTrue(matcher.test(actual.Xy()), "Matrix2x2.Xy " + reason);
        Assertions.assertTrue(matcher.test(actual.Yx()), "Matrix2x2.Yx " + reason);
        Assertions.assertTrue(matcher.test(actual.Yy()), "Matrix2x2.Yy " + reason);
    }

    static void assertEquals(final Matrix3x3.Accessible expected, final Matrix3x3.Accessible actual, final double delta) {
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

    static void assertEquals(final Matrix3x3.Accessible expected, final Matrix3x3.Accessible actual, final Matrix3x3.Accessible delta) {
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

    static void assertMatches(final DoublePredicate matcher, final Matrix3x3.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.Xx()), "Matrix3x3.Xx " + reason);
        Assertions.assertTrue(matcher.test(actual.Xy()), "Matrix3x3.Xy " + reason);
        Assertions.assertTrue(matcher.test(actual.Xz()), "Matrix3x3.Xz " + reason);
        Assertions.assertTrue(matcher.test(actual.Yx()), "Matrix3x3.Yx " + reason);
        Assertions.assertTrue(matcher.test(actual.Yy()), "Matrix3x3.Yy " + reason);
        Assertions.assertTrue(matcher.test(actual.Yz()), "Matrix3x3.Yz " + reason);
        Assertions.assertTrue(matcher.test(actual.Zx()), "Matrix3x3.Zx " + reason);
        Assertions.assertTrue(matcher.test(actual.Zy()), "Matrix3x3.Zy " + reason);
        Assertions.assertTrue(matcher.test(actual.Zz()), "Matrix3x3.Zz " + reason);
    }

    static void assertEquals(final Matrix4x4.Accessible expected, final Matrix4x4.Accessible actual, final double delta) {
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

    static void assertEquals(final Matrix4x4.Accessible expected, final Matrix4x4.Accessible actual, final Matrix4x4.Accessible delta) {
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

    static void assertMatches(final DoublePredicate matcher, final Matrix4x4.Accessible actual, final String reason) {
        Assertions.assertTrue(matcher.test(actual.Xx()), "Matrix4x4.Xx " + reason);
        Assertions.assertTrue(matcher.test(actual.Xy()), "Matrix4x4.Xy " + reason);
        Assertions.assertTrue(matcher.test(actual.Xz()), "Matrix4x4.Xz " + reason);
        Assertions.assertTrue(matcher.test(actual.Xw()), "Matrix4x4.Xw " + reason);
        Assertions.assertTrue(matcher.test(actual.Yx()), "Matrix4x4.Yx " + reason);
        Assertions.assertTrue(matcher.test(actual.Yy()), "Matrix4x4.Yy " + reason);
        Assertions.assertTrue(matcher.test(actual.Yz()), "Matrix4x4.Yz " + reason);
        Assertions.assertTrue(matcher.test(actual.Yw()), "Matrix4x4.Yw " + reason);
        Assertions.assertTrue(matcher.test(actual.Zx()), "Matrix4x4.Zx " + reason);
        Assertions.assertTrue(matcher.test(actual.Zy()), "Matrix4x4.Zy " + reason);
        Assertions.assertTrue(matcher.test(actual.Zz()), "Matrix4x4.Zz " + reason);
        Assertions.assertTrue(matcher.test(actual.Zw()), "Matrix4x4.Zw " + reason);
        Assertions.assertTrue(matcher.test(actual.Tx()), "Matrix4x4.Tx " + reason);
        Assertions.assertTrue(matcher.test(actual.Ty()), "Matrix4x4.Ty " + reason);
        Assertions.assertTrue(matcher.test(actual.Tz()), "Matrix4x4.Tz " + reason);
        Assertions.assertTrue(matcher.test(actual.Tw()), "Matrix4x4.Tw " + reason);
    }

}
