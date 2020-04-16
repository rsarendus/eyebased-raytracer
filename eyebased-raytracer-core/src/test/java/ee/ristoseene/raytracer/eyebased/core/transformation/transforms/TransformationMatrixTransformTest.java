package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.transformation.ChainableTransformTest;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class TransformationMatrixTransformTest extends ChainableTransformTest {

    @Override
    protected TransformationMatrixTransform createDefaultInstance() {
        return new TransformationMatrixTransform();
    }

    @Test
    public void setTransformationMatrixShouldSetTheTransformationMatrix() {
        Matrix4x4.Accessible transformationMatrix = Mockito.mock(Matrix4x4.Accessible.class);
        TransformationMatrixTransform transform = createDefaultInstance();

        transform.setTransformationMatrix(transformationMatrix);

        Assertions.assertSame(transformationMatrix, transform.getTransformationMatrix());
    }

    @Test
    public void withTransformationMatrixShouldSetTheTransformationMatrixAndReturnItself() {
        Matrix4x4.Accessible transformationMatrix = Mockito.mock(Matrix4x4.Accessible.class);
        TransformationMatrixTransform transform = createDefaultInstance();

        Assertions.assertSame(transform, transform.withTransformationMatrix(transformationMatrix));
        Assertions.assertSame(transformationMatrix, transform.getTransformationMatrix());
    }

    @Test
    public void compileShouldReturnTransformEqualToTransformationMatrixIfNoParentSet() {
        Matrix4x4.Accessible transformationMatrix = new ImmutableMatrix4x4(3.7);
        TransformationMatrixTransform transform = createDefaultInstance()
                .withTransformationMatrix(transformationMatrix);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(transformationMatrix, compilationResult, 0.000001);
    }

    @Test
    public void compileShouldReturnMultiplicationResultOfParentAndTransformationMatrix() {
        Matrix4x4.Accessible transformationMatrix = new ImmutableMatrix4x4(3.7);
        CompiledTransform parentCompilationResult = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        CompilableTransform parentTransform = createMockCompilableTransform(parentCompilationResult);
        TransformationMatrixTransform transform = createDefaultInstance()
                .withTransformationMatrix(transformationMatrix)
                .withParentTransform(parentTransform);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(
                VecMath.multiply(parentCompilationResult, transformationMatrix, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                compilationResult,
                0.000001
        );
    }

}
