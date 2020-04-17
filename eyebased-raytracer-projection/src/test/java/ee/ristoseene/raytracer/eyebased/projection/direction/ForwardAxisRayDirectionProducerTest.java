package ee.ristoseene.raytracer.eyebased.projection.direction;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ForwardAxisRayDirectionProducerTest {

    @Test
    public void directionProducerShouldNotAllowMissingForwardAxis() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new ForwardAxisRayDirectionProducer(null)
        );

        Assertions.assertEquals("Forward axis not provided", exception.getMessage());
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void produceDirectionShouldAlwaysReturnTheSpecifiedForwardAxis(final Axis axis) {
        RayDirectionProducer directionProducer = new ForwardAxisRayDirectionProducer(axis);

        Vector3.Accessible result = directionProducer.produceDirection(3.7, 7.3);

        VecMathAssertions.assertEquals(axis, result, 0.0);
    }

}
