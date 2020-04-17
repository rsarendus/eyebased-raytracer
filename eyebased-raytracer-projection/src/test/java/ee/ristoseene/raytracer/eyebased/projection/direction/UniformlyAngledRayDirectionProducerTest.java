package ee.ristoseene.raytracer.eyebased.projection.direction;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.helpers.AxialCombinationsTest;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class UniformlyAngledRayDirectionProducerTest implements AxialCombinationsTest {

    @Test
    public void directionProducerShouldNotAllowMissingOrientation() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new UniformlyAngledRayDirectionProducer(null, 1.0, 1.0)
        );

        Assertions.assertEquals("Orientation not provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.8, -0.5, -0.2, -0.1, 0.0, 0.1, 0.2, 0.5, 0.8, 1.0})
    public void produceDirectionShouldAngleVectorDirectionUniformlyAroundVerticalAxis(final double viewX) {
        RayDirectionProducer directionProducer = new UniformlyAngledRayDirectionProducer(
                Orientation.builder().withForwardAxis(Axis.POSITIVE_X).withRightAxis(Axis.POSITIVE_Y).withUpAxis(Axis.POSITIVE_Z).build(),
                2.0, 0.0
        );

        Vector3.Accessible result = directionProducer.produceDirection(viewX, 0.0);

        Assertions.assertEquals(Math.abs(viewX), Math.acos(VecMath.dot(Axis.POSITIVE_X, result)), 0.000001);
        Assertions.assertEquals(viewX, Math.asin(VecMath.dot(Axis.POSITIVE_Y, result)), 0.000001);
        Assertions.assertEquals(0.0, VecMath.dot(Axis.POSITIVE_Z, result), 0.0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.8, -0.5, -0.2, -0.1, 0.0, 0.1, 0.2, 0.5, 0.8, 1.0})
    public void produceDirectionShouldAngleVectorDirectionUniformlyAroundHorizontalAxis(final double viewY) {
        RayDirectionProducer directionProducer = new UniformlyAngledRayDirectionProducer(
                Orientation.builder().withForwardAxis(Axis.POSITIVE_X).withRightAxis(Axis.POSITIVE_Y).withUpAxis(Axis.POSITIVE_Z).build(),
                0.0, 2.0
        );

        Vector3.Accessible result = directionProducer.produceDirection(0.0, viewY);

        Assertions.assertEquals(Math.abs(viewY), Math.acos(VecMath.dot(Axis.POSITIVE_X, result)), 0.000001);
        Assertions.assertEquals(0.0, VecMath.dot(Axis.POSITIVE_Y, result), 0.0);
        Assertions.assertEquals(viewY, Math.asin(VecMath.dot(Axis.POSITIVE_Z, result)), 0.000001);
    }

    @ParameterizedTest
    @MethodSource("validCombinationsOfAxesForOrientation")
    public void produceDirectionShouldReturnCorrectlyOrientedUnitVector(final List<Axis> axes) {
        Orientation orientation = Orientation.builder().withForwardAxis(axes.get(0)).withRightAxis(axes.get(1)).withUpAxis(axes.get(2)).build();
        RayDirectionProducer directionProducer = new UniformlyAngledRayDirectionProducer(orientation, 3.5, 2.5);

        Vector3.Accessible result = directionProducer.produceDirection(0.0, 0.0);

        VecMathAssertions.assertEquals(new ImmutableVector3(
                VecMath.dot(orientation.getForwardAxis(), Axis.POSITIVE_X),
                VecMath.dot(orientation.getForwardAxis(), Axis.POSITIVE_Y),
                VecMath.dot(orientation.getForwardAxis(), Axis.POSITIVE_Z)
        ), result, 0.000001);
    }

}
