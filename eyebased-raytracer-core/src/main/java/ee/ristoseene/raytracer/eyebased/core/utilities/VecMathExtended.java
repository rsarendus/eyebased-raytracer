package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.vecmath.Matrix2x2;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public final class VecMathExtended {

    public static <R> R addProducts(
            final Vector2.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector2.Accessible rightMultiplicand, final double rightMultiplier,
            final Vector2.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier
        );
    }

    public static void addProducts(
            final Vector2.Consumer resultConsumer,
            final Vector2.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector2.Accessible rightMultiplicand, final double rightMultiplier
    ) {
        resultConsumer.xy(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier
        );
    }

    public static <R> R addProducts(
            final Vector2.Accessible leftMultiplicand, final Vector2.Accessible leftMultiplier,
            final Vector2.Accessible rightMultiplicand, final Vector2.Accessible rightMultiplier,
            final Vector2.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y()
        );
    }

    public static void addProducts(
            final Vector2.Consumer resultConsumer,
            final Vector2.Accessible leftMultiplicand, final Vector2.Accessible leftMultiplier,
            final Vector2.Accessible rightMultiplicand, final Vector2.Accessible rightMultiplier
    ) {
        resultConsumer.xy(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y()
        );
    }

    public static <R> R addProducts(
            final Vector3.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector3.Accessible rightMultiplicand, final double rightMultiplier,
            final Vector3.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier,
                leftMultiplicand.z() * leftMultiplier + rightMultiplicand.z() * rightMultiplier
        );
    }

    public static void addProducts(
            final Vector3.Consumer resultConsumer,
            final Vector3.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector3.Accessible rightMultiplicand, final double rightMultiplier
    ) {
        resultConsumer.xyz(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier,
                leftMultiplicand.z() * leftMultiplier + rightMultiplicand.z() * rightMultiplier
        );
    }

    public static <R> R addProducts(
            final Vector3.Accessible leftMultiplicand, final Vector3.Accessible leftMultiplier,
            final Vector3.Accessible rightMultiplicand, final Vector3.Accessible rightMultiplier,
            final Vector3.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y(),
                leftMultiplicand.z() * leftMultiplier.z() + rightMultiplicand.z() * rightMultiplier.z()
        );
    }

    public static void addProducts(
            final Vector3.Consumer resultConsumer,
            final Vector3.Accessible leftMultiplicand, final Vector3.Accessible leftMultiplier,
            final Vector3.Accessible rightMultiplicand, final Vector3.Accessible rightMultiplier
    ) {
        resultConsumer.xyz(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y(),
                leftMultiplicand.z() * leftMultiplier.z() + rightMultiplicand.z() * rightMultiplier.z()
        );
    }

    public static <R> R addProducts(
            final Vector4.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector4.Accessible rightMultiplicand, final double rightMultiplier,
            final Vector4.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier,
                leftMultiplicand.z() * leftMultiplier + rightMultiplicand.z() * rightMultiplier,
                leftMultiplicand.w() * leftMultiplier + rightMultiplicand.w() * rightMultiplier
        );
    }

    public static void addProducts(
            final Vector4.Consumer resultConsumer,
            final Vector4.Accessible leftMultiplicand, final double leftMultiplier,
            final Vector4.Accessible rightMultiplicand, final double rightMultiplier
    ) {
        resultConsumer.xyzw(
                leftMultiplicand.x() * leftMultiplier + rightMultiplicand.x() * rightMultiplier,
                leftMultiplicand.y() * leftMultiplier + rightMultiplicand.y() * rightMultiplier,
                leftMultiplicand.z() * leftMultiplier + rightMultiplicand.z() * rightMultiplier,
                leftMultiplicand.w() * leftMultiplier + rightMultiplicand.w() * rightMultiplier
        );
    }

    public static <R> R addProducts(
            final Vector4.Accessible leftMultiplicand, final Vector4.Accessible leftMultiplier,
            final Vector4.Accessible rightMultiplicand, final Vector4.Accessible rightMultiplier,
            final Vector4.Factory<R> resultFactory
    ) {
        return resultFactory.create(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y(),
                leftMultiplicand.z() * leftMultiplier.z() + rightMultiplicand.z() * rightMultiplier.z(),
                leftMultiplicand.w() * leftMultiplier.w() + rightMultiplicand.w() * rightMultiplier.w()
        );
    }

    public static void addProducts(
            final Vector4.Consumer resultConsumer,
            final Vector4.Accessible leftMultiplicand, final Vector4.Accessible leftMultiplier,
            final Vector4.Accessible rightMultiplicand, final Vector4.Accessible rightMultiplier
    ) {
        resultConsumer.xyzw(
                leftMultiplicand.x() * leftMultiplier.x() + rightMultiplicand.x() * rightMultiplier.x(),
                leftMultiplicand.y() * leftMultiplier.y() + rightMultiplicand.y() * rightMultiplier.y(),
                leftMultiplicand.z() * leftMultiplier.z() + rightMultiplicand.z() * rightMultiplier.z(),
                leftMultiplicand.w() * leftMultiplier.w() + rightMultiplicand.w() * rightMultiplier.w()
        );
    }

    public static <R> R normalize(final double x, final double y, final Vector2.Factory<R> resultFactory) {
        double length = Math.sqrt(x * x + y * y);
        double inverseLength = 1.0 / (length > 0.0 ? length : 1.0);
        return resultFactory.create(
                x * inverseLength,
                y * inverseLength
        );
    }

    public static <R> R normalize(final double x, final double y, final double z, final Vector3.Factory<R> resultFactory) {
        double length = Math.sqrt(x * x + y * y + z * z);
        double inverseLength = 1.0 / (length > 0.0 ? length : 1.0);
        return resultFactory.create(
                x * inverseLength,
                y * inverseLength,
                z * inverseLength
        );
    }

    public static <R> R normalize(final double x, final double y, final double z, final double w, final Vector4.Factory<R> resultFactory) {
        double length = Math.sqrt(x * x + y * y + z * z + w * w);
        double inverseLength = 1.0 / (length > 0.0 ? length : 1.0);
        return resultFactory.create(
                x * inverseLength,
                y * inverseLength,
                z * inverseLength,
                w * inverseLength
        );
    }

    public static boolean equal(final Vector2.Accessible vector1, final Vector2.Accessible vector2) {
        if (vector1.x() != vector2.x()) return false;
        if (vector1.y() != vector2.y()) return false;
        return true;
    }

    public static boolean equal(final Vector3.Accessible vector1, final Vector3.Accessible vector2) {
        if (vector1.x() != vector2.x()) return false;
        if (vector1.y() != vector2.y()) return false;
        if (vector1.z() != vector2.z()) return false;
        return true;
    }

    public static boolean equal(final Vector4.Accessible vector1, final Vector4.Accessible vector2) {
        if (vector1.x() != vector2.x()) return false;
        if (vector1.y() != vector2.y()) return false;
        if (vector1.z() != vector2.z()) return false;
        if (vector1.w() != vector2.w()) return false;
        return true;
    }

    public static boolean equal(final Matrix2x2.Accessible matrix1, final Matrix2x2.Accessible matrix2) {
        if (matrix1.Xx() != matrix2.Xx()) return false;
        if (matrix1.Xy() != matrix2.Xy()) return false;
        if (matrix1.Yx() != matrix2.Yx()) return false;
        if (matrix1.Yy() != matrix2.Yy()) return false;
        return true;
    }

    public static boolean equal(final Matrix3x3.Accessible matrix1, final Matrix3x3.Accessible matrix2) {
        if (matrix1.Xx() != matrix2.Xx()) return false;
        if (matrix1.Xy() != matrix2.Xy()) return false;
        if (matrix1.Xz() != matrix2.Xz()) return false;
        if (matrix1.Yx() != matrix2.Yx()) return false;
        if (matrix1.Yy() != matrix2.Yy()) return false;
        if (matrix1.Yz() != matrix2.Yz()) return false;
        if (matrix1.Zx() != matrix2.Zx()) return false;
        if (matrix1.Zy() != matrix2.Zy()) return false;
        if (matrix1.Zz() != matrix2.Zz()) return false;
        return true;
    }

    public static boolean equal(final Matrix4x4.Accessible matrix1, final Matrix4x4.Accessible matrix2) {
        if (matrix1.Xx() != matrix2.Xx()) return false;
        if (matrix1.Xy() != matrix2.Xy()) return false;
        if (matrix1.Xz() != matrix2.Xz()) return false;
        if (matrix1.Xw() != matrix2.Xw()) return false;
        if (matrix1.Yx() != matrix2.Yx()) return false;
        if (matrix1.Yy() != matrix2.Yy()) return false;
        if (matrix1.Yz() != matrix2.Yz()) return false;
        if (matrix1.Yw() != matrix2.Yw()) return false;
        if (matrix1.Zx() != matrix2.Zx()) return false;
        if (matrix1.Zy() != matrix2.Zy()) return false;
        if (matrix1.Zz() != matrix2.Zz()) return false;
        if (matrix1.Zw() != matrix2.Zw()) return false;
        if (matrix1.Tx() != matrix2.Tx()) return false;
        if (matrix1.Ty() != matrix2.Ty()) return false;
        if (matrix1.Tz() != matrix2.Tz()) return false;
        if (matrix1.Tw() != matrix2.Tw()) return false;
        return true;
    }

    private VecMathExtended() {}

}
