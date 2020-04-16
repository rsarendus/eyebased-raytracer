package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompiledTransformTest {

    private static final ImmutableMatrix4x4 MOCK_TRANSFORMATION_MATRIX = new ImmutableMatrix4x4(
            0.1, 0.2, 0.3, 0.0,
            0.4, 0.5, 0.6, 0.0,
            0.7, 0.8, 0.9, 0.0,
            2.0, 4.0, 6.0, 1.0
    );

    @Test
    public void identityTransformShouldBeIdentityMatrix() {
        VecMathAssertions.assertEquals(
                new ImmutableMatrix4x4(1.0, 1.0, 1.0, 1.0),
                CompiledTransform.IDENTITY_TRANSFORM,
                0.0
        );
    }

    @Test
    public void inverseOfIdentityTransformShouldBeInverseMatrix() {
        VecMathAssertions.assertEquals(
                VecMath.inverse(CompiledTransform.IDENTITY_TRANSFORM, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                CompiledTransform.IDENTITY_TRANSFORM.getInverseTransform(),
                0.0
        );
    }

    @Test
    public void inverseOfIdentityTransformShouldBeItself() {
        Assertions.assertSame(
                CompiledTransform.IDENTITY_TRANSFORM,
                CompiledTransform.IDENTITY_TRANSFORM.getInverseTransform()
        );
    }

    @Test
    public void transformFromMatrixShouldBeEqualToTheMatrix() {
        VecMathAssertions.assertEquals(
                MOCK_TRANSFORMATION_MATRIX,
                new CompiledTransform(MOCK_TRANSFORMATION_MATRIX),
                0.0
        );
    }

    @Test
    public void inverseTransformFromMatrixShouldBeItsInverseMatrix() {
        VecMathAssertions.assertEquals(
                VecMath.inverse(MOCK_TRANSFORMATION_MATRIX, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                new CompiledTransform(MOCK_TRANSFORMATION_MATRIX).getInverseTransform(),
                0.0
        );
    }

}
