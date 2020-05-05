package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.transformation.ChainableTransformTest;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractPositionTransformTest extends ChainableTransformTest {

    @Override
    protected abstract AbstractPositionTransform createDefaultInstance();

    @Test
    public void setPositionShouldSetThePosition() {
        Vector3.Accessible position = Mockito.mock(Vector3.Accessible.class);
        AbstractPositionTransform transform = createDefaultInstance();

        transform.setPosition(position);

        Assertions.assertSame(position, transform.getPosition());
    }

    @Test
    public void withPositionShouldSetThePositionAndReturnItself() {
        Vector3.Accessible position = Mockito.mock(Vector3.Accessible.class);
        AbstractPositionTransform transform = createDefaultInstance();

        Assertions.assertSame(transform, transform.withPosition(position));
        Assertions.assertSame(position, transform.getPosition());
    }

    @Test
    public void compileShouldReturnTransformWithEquivalentPositionComponentIfOnlyPositionSet() {
        Vector3.Accessible position = new ImmutableVector3(1.3, -2.2, 3.1);
        AbstractPositionTransform transform = createDefaultInstance()
                .withPosition(position);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(new ImmutableMatrix4x4(
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                1.3, -2.2, 3.1, 1.0
        ), compilationResult, 0.000001);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T cloneShouldCreateValidCopyOfItself(T original) {
        AbstractPositionTransform originalTransform = (AbstractPositionTransform) original;
        Vector3.Accessible position = Mockito.mock(Vector3.Accessible.class);
        originalTransform.setPosition(position);

        AbstractPositionTransform clonedTransform = super.cloneShouldCreateValidCopyOfItself(originalTransform);

        Assertions.assertNotSame(originalTransform, clonedTransform);
        Assertions.assertSame(position, clonedTransform.getPosition());

        return (T) clonedTransform;
    }

}
