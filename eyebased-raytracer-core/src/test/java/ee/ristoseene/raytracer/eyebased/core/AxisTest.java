package ee.ristoseene.raytracer.eyebased.core;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AxisTest {

    @Test
    public void axisPositiveXShouldBeImmutableVectorPointingPositiveX() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.POSITIVE_X));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.POSITIVE_X));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(1.0, 0.0, 0.0),
                Axis.POSITIVE_X,
                0.0
        );
    }

    @Test
    public void axisNegativeXShouldBeImmutableVectorPointingNegativeX() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.NEGATIVE_X));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.NEGATIVE_X));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(-1.0, 0.0, 0.0),
                Axis.NEGATIVE_X,
                0.0
        );
    }

    @Test
    public void axisPositiveYShouldBeImmutableVectorPointingPositiveY() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.POSITIVE_Y));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.POSITIVE_Y));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(0.0, 1.0, 0.0),
                Axis.POSITIVE_Y,
                0.0
        );
    }

    @Test
    public void axisNegativeYShouldBeImmutableVectorPointingNegativeY() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.NEGATIVE_Y));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.NEGATIVE_Y));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(0.0, -1.0, 0.0),
                Axis.NEGATIVE_Y,
                0.0
        );
    }

    @Test
    public void axisPositiveZShouldBeImmutableVectorPointingPositiveZ() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.POSITIVE_Z));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.POSITIVE_Z));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(0.0, 0.0, 1.0),
                Axis.POSITIVE_Z,
                0.0
        );
    }

    @Test
    public void axisNegativeZShouldBeImmutableVectorPointingNegativeZ() {
        Assertions.assertTrue(Vector3.Accessible.class.isInstance(Axis.NEGATIVE_Z));
        Assertions.assertFalse(Vector3.Mutable.class.isInstance(Axis.NEGATIVE_Z));
        VecMathAssertions.assertEquals(
                new ImmutableVector3(0.0, 0.0, -1.0),
                Axis.NEGATIVE_Z,
                0.0
        );
    }

}
