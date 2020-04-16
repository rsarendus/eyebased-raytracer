package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RotationMatrixAndPositionTransformTest extends AbstractPositionTransformTest {

    @Override
    protected RotationMatrixAndPositionTransform createDefaultInstance() {
        return new RotationMatrixAndPositionTransform();
    }

    @Test
    public void setRotationMatrixShouldSetTheRotationMatrix() {
        Matrix3x3.Accessible rotationMatrix = Mockito.mock(Matrix3x3.Accessible.class);
        RotationMatrixAndPositionTransform transform = createDefaultInstance();

        transform.setRotationMatrix(rotationMatrix);

        Assertions.assertSame(rotationMatrix, transform.getRotationMatrix());
    }

    @Test
    public void withRotationMatrixShouldSetTheRotationMatrixAndReturnItself() {
        Matrix3x3.Accessible rotationMatrix = Mockito.mock(Matrix3x3.Accessible.class);
        RotationMatrixAndPositionTransform transform = createDefaultInstance();

        Assertions.assertSame(transform, transform.withRotationMatrix(rotationMatrix));
        Assertions.assertSame(rotationMatrix, transform.getRotationMatrix());
    }

    @Test
    public void compileShouldReturnTransformWithEquivalentRotationComponentIfOnlyRotationMatrixSet() {
        Matrix3x3.Accessible rotationMatrix = new ImmutableMatrix3x3(3.7);
        RotationMatrixAndPositionTransform transform = createDefaultInstance()
                .withRotationMatrix(rotationMatrix);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(new ImmutableMatrix4x4(
                3.7, 3.7, 3.7, 0.0,
                3.7, 3.7, 3.7, 0.0,
                3.7, 3.7, 3.7, 0.0,
                0.0, 0.0, 0.0, 1.0
        ), compilationResult, 0.000001);
    }

    @Test
    public void compileShouldReturnMultiplicationResultOfParentAndTransformationMatrixWithCorrectRotationAndPositionComponents() {
        Matrix3x3.Accessible rotationMatrix = new ImmutableMatrix3x3(3.7);
        Vector3.Accessible position = new ImmutableVector3(1.1, 2.2, 3.3);
        CompiledTransform parentCompilationResult = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        CompilableTransform parentTransform = createMockCompilableTransform(parentCompilationResult);
        RotationMatrixAndPositionTransform transform = createDefaultInstance()
                .withParentTransform(parentTransform)
                .withRotationMatrix(rotationMatrix)
                .withPosition(position);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(
                VecMath.multiply(parentCompilationResult, new ImmutableMatrix4x4(
                        3.7, 3.7, 3.7, 0.0,
                        3.7, 3.7, 3.7, 0.0,
                        3.7, 3.7, 3.7, 0.0,
                        1.1, 2.2, 3.3, 1.0
                ), Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                compilationResult,
                0.000001
        );
    }

}
