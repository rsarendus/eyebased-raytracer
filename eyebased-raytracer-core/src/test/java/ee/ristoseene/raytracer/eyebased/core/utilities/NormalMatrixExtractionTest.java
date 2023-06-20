package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.mutable.MutableMatrix3x3;
import org.junit.jupiter.api.Test;

public class NormalMatrixExtractionTest {

    private static Matrix3x3.Accessible ROTATION_MATRIX = new ImmutableMatrix3x3(
            1.9, 2.8, 3.7,
            4.6, 5.5, 6.4,
            7.3, 8.2, 9.1
    );

    private static Matrix4x4.Accessible TRANSFORMATION_MATRIX = new ImmutableMatrix4x4(
            1.9, 2.8, 3.7, 0.0,
            4.6, 5.5, 6.4, 0.0,
            7.3, 8.2, 9.1, 0.0,
            1.3, 2.6, 3.9, 1.0
    );

    private static Matrix3x3.Accessible ROTATION_TRANSPOSE = new ImmutableMatrix3x3(
            1.9, 4.6, 7.3,
            2.8, 5.5, 8.2,
            3.7, 6.4, 9.1
    );

    @Test
    public void getNormalMatrixFromInverseRotationMatrixShouldReturnTransposedRotationMatrix() {
        Matrix3x3.Accessible result = NormalMatrixExtraction.getNormalMatrixFromInverse(ROTATION_MATRIX);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, result, 0.0);
    }

    @Test
    public void getNormalMatrixFromInverseRotationMatrixShouldCreateTransposedRotationMatrix() {
        Matrix3x3.Accessible result = NormalMatrixExtraction.getNormalMatrixFromInverse(ROTATION_MATRIX, Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, result, 0.0);
    }

    @Test
    public void getNormalMatrixFromInverseRotationMatrixShouldWriteTransposedRotationMatrixToConsumer() {
        Matrix3x3.AccessibleAndMutable resultConsumer = new MutableMatrix3x3();
        NormalMatrixExtraction.getNormalMatrixFromInverse(resultConsumer, ROTATION_MATRIX);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, resultConsumer, 0.0);
    }

    @Test
    public void getNormalMatrixFromInverseTransformationMatrixShouldReturnTransposedRotationMatrix() {
        Matrix3x3.Accessible result = NormalMatrixExtraction.getNormalMatrixFromInverse(TRANSFORMATION_MATRIX);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, result, 0.0);
    }

    @Test
    public void getNormalMatrixFromInverseTransformationMatrixShouldCreateTransposedRotationMatrix() {
        Matrix3x3.Accessible result = NormalMatrixExtraction.getNormalMatrixFromInverse(TRANSFORMATION_MATRIX, Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, result, 0.0);
    }

    @Test
    public void getNormalMatrixFromInverseTransformationMatrixShouldWriteTransposedRotationMatrixToConsumer() {
        Matrix3x3.AccessibleAndMutable resultConsumer = new MutableMatrix3x3();
        NormalMatrixExtraction.getNormalMatrixFromInverse(resultConsumer, TRANSFORMATION_MATRIX);
        VecMathAssertions.assertEquals(ROTATION_TRANSPOSE, resultConsumer, 0.0);
    }

}
