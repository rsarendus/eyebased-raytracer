package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.raytracer.eyebased.projection.direction.UniformlyAngledRayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.helpers.AxialCombinationsTest;
import ee.ristoseene.vecmath.VecOps;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class UniformlyPositionedRayOriginProducerTest implements AxialCombinationsTest {

    @Test
    public void originProducerShouldNotAllowMissingOrientation() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new UniformlyAngledRayDirectionProducer(null, 0.0, 0.0)
        );

        Assertions.assertEquals("Orientation not provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.8, -0.5, -0.2, -0.1, 0.0, 0.1, 0.2, 0.5, 0.8, 1.0})
    public void produceOriginShouldPositionOriginUniformlyOnHorizontalAxis(final double viewX) {
        RayOriginProducer originProducer = new UniformlyPositionedRayOriginProducer(
                Orientation.builder().withForwardAxis(Axis.POSITIVE_X).withRightAxis(Axis.POSITIVE_Y).withUpAxis(Axis.POSITIVE_Z).build(),
                2.0, 0.0
        );

        Vector3.Accessible result = originProducer.produceOrigin(viewX, 0.0);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, viewX, 0.0), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.8, -0.5, -0.2, -0.1, 0.0, 0.1, 0.2, 0.5, 0.8, 1.0})
    public void produceOriginShouldPositionOriginUniformlyOnVerticalAxis(final double viewY) {
        RayOriginProducer originProducer = new UniformlyPositionedRayOriginProducer(
                Orientation.builder().withForwardAxis(Axis.POSITIVE_X).withRightAxis(Axis.POSITIVE_Y).withUpAxis(Axis.POSITIVE_Z).build(),
                0.0, 2.0
        );

        Vector3.Accessible result = originProducer.produceOrigin(0.0, viewY);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, 0.0, viewY), result, 0.000001);
    }

    @ParameterizedTest
    @MethodSource("validCombinationsOfAxesForOrientation")
    public void produceOriginShouldReturnCorrectlyPositionedOrigin(final List<Axis> axes) {
        Orientation orientation = Orientation.builder().withForwardAxis(axes.get(0)).withRightAxis(axes.get(1)).withUpAxis(axes.get(2)).build();
        RayOriginProducer originProducer = new UniformlyPositionedRayOriginProducer(orientation, 3.5, 2.5);

        Vector3.Accessible result = originProducer.produceOrigin(0.55, -0.45);

        VecMathAssertions.assertEquals(
                VecOps.apply(
                        (r, u) -> r * 3.5 / 2 * 0.55 - u * 2.5 / 2 * 0.45,
                        orientation.getRightAxis(), orientation.getUpAxis(),
                        Factories.FACTORY_CONST_VECTOR3_xyz
                ),
                result, 0.000001
        );
    }

}
