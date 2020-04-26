package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class QuaternionAndScaleAndPositionTransformTest extends AbstractScalingPositionTransformTest {

    @Override
    protected QuaternionAndScaleAndPositionTransform createDefaultInstance() {
        return new QuaternionAndScaleAndPositionTransform();
    }

    @Test
    public void setQuaternionShouldSetTheQuaternion() {
        Quaternion.Accessible quaternion = Mockito.mock(Quaternion.Accessible.class);
        QuaternionAndScaleAndPositionTransform transform = createDefaultInstance();

        transform.setQuaternion(quaternion);

        Assertions.assertSame(quaternion, transform.getQuaternion());
    }

    @Test
    public void withQuaternionShouldSetTheQuaternionAndReturnItself() {
        Quaternion.Accessible quaternion = Mockito.mock(Quaternion.Accessible.class);
        QuaternionAndScaleAndPositionTransform transform = createDefaultInstance();

        Assertions.assertSame(transform, transform.withQuaternion(quaternion));
        Assertions.assertSame(quaternion, transform.getQuaternion());
    }

    @Test
    public void compileShouldReturnTransformWithEquivalentRotationComponentIfOnlyQuaternionSet() {
        Quaternion.Accessible quaternion = VecMath.toRotation(
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.0, 2.0, 3.0),
                0.73, Factories.FACTORY_CONST_QUATERNION_xyzw
        );
        QuaternionAndScaleAndPositionTransform transform = createDefaultInstance()
                .withQuaternion(quaternion);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(
                VecMath.toRotation(quaternion, Factories.FACTORY_CONST_MATRIX4x4_XYZxyz_IDENTITY),
                compilationResult, 0.000001
        );
    }

    @Test
    public void compileShouldReturnMultiplicationResultOfParentAndTransformationMatrixWithCorrectQuaternionAndScaleAndPositionComponents() {
        Quaternion.Accessible quaternion = VecMath.toRotation(
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.0, 2.0, 3.0),
                0.73, Factories.FACTORY_CONST_QUATERNION_xyzw
        );
        Vector3.Accessible scale = new ImmutableVector3(1.3, -2.2, 3.1);
        Vector3.Accessible position = new ImmutableVector3(-3.1, 2.2, -1.3);
        Matrix3x3.Accessible rotation = VecMath.toRotation(quaternion, Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
        CompiledTransform parentCompilationResult = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        CompilableTransform parentTransform = createMockCompilableTransform(parentCompilationResult);
        QuaternionAndScaleAndPositionTransform transform = createDefaultInstance()
                .withParentTransform(parentTransform)
                .withQuaternion(quaternion)
                .withScale(scale)
                .withPosition(position);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(
                VecMath.multiply(parentCompilationResult, new ImmutableMatrix4x4(
                        1.3 * rotation.Xx(), -2.2 * rotation.Xy(), 3.1 * rotation.Xz(), 0.0,
                        1.3 * rotation.Yx(), -2.2 * rotation.Yy(), 3.1 * rotation.Yz(), 0.0,
                        1.3 * rotation.Zx(), -2.2 * rotation.Zy(), 3.1 * rotation.Zz(), 0.0,
                        -3.1, 2.2, -1.3, 1.0
                ), Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                compilationResult,
                0.000001
        );
    }

}
