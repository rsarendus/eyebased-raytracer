package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractScalingPositionTransformTest extends AbstractPositionTransformTest {

    @Override
    protected abstract AbstractScalingPositionTransform createDefaultInstance();

    @Test
    public void setScaleShouldSetTheScale() {
        Vector3.Accessible scale = Mockito.mock(Vector3.Accessible.class);
        AbstractScalingPositionTransform transform = createDefaultInstance();

        transform.setScale(scale);

        Assertions.assertSame(scale, transform.getScale());
    }

    @Test
    public void withScaleShouldSetTheScaleAndReturnItself() {
        Vector3.Accessible scale = Mockito.mock(Vector3.Accessible.class);
        AbstractScalingPositionTransform transform = createDefaultInstance();

        Assertions.assertSame(transform, transform.withScale(scale));
        Assertions.assertSame(scale, transform.getScale());
    }

    @Test
    public void compileShouldReturnTransformWithEquivalentScaleComponentIfOnlyScaleSet() {
        Vector3.Accessible scale = new ImmutableVector3(1.3, -2.2, 3.1);
        AbstractScalingPositionTransform transform = createDefaultInstance()
                .withScale(scale);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(
                new ImmutableMatrix4x4(1.3, -2.2, 3.1, 1.0),
                compilationResult, 0.000001
        );
    }

}
