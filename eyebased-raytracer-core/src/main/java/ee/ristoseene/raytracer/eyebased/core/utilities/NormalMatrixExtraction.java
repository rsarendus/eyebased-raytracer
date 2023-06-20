package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;

public final class NormalMatrixExtraction {

    public static Matrix3x3.Accessible getNormalMatrixFromInverse(
            final Matrix3x3.Accessible inverseRotationMatrix
    ) {
        return getNormalMatrixFromInverse(inverseRotationMatrix, Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
    }

    public static <R> R getNormalMatrixFromInverse(
            final Matrix3x3.Accessible inverseRotationMatrix,
            final Matrix3x3.Factory<R> resultFactory
    ) {
        return inverseRotationMatrix.xyzXYZ(resultFactory);
    }

    public static void getNormalMatrixFromInverse(
            final Matrix3x3.Consumer resultConsumer,
            final Matrix3x3.Accessible inverseRotationMatrix
    ) {
        inverseRotationMatrix.xyzXYZto(resultConsumer);
    }

    public static Matrix3x3.Accessible getNormalMatrixFromInverse(
            final Matrix4x4.Accessible inverseTransformationMatrix
    ) {
        return getNormalMatrixFromInverse(inverseTransformationMatrix, Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
    }

    public static <R> R getNormalMatrixFromInverse(
            final Matrix4x4.Accessible inverseTransformationMatrix,
            final Matrix3x3.Factory<R> resultFactory
    ) {
        return inverseTransformationMatrix.xyzXYZ(resultFactory);
    }

    public static void getNormalMatrixFromInverse(
            final Matrix3x3.Consumer resultConsumer,
            final Matrix4x4.Accessible inverseTransformationMatrix
    ) {
        inverseTransformationMatrix.xyzXYZto(resultConsumer);
    }

    private NormalMatrixExtraction() {}

}
