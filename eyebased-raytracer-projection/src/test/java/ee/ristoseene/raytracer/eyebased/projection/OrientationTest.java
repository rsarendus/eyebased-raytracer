package ee.ristoseene.raytracer.eyebased.projection;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.helpers.AxialCombinationsGenerator;
import ee.ristoseene.raytracer.eyebased.projection.helpers.AxialCombinationsTest;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.mutable.MutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class OrientationTest implements AxialCombinationsTest {

    @Test
    public void orientationShouldNotAllowMissingUpAxis() {
        Orientation.Builder builder = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> builder.build()
        );

        Assertions.assertEquals("Up axis not provided", exception.getMessage());
    }

    @Test
    public void orientationShouldNotAllowMissingRightAxis() {
        Orientation.Builder builder = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withUpAxis(Axis.POSITIVE_Z);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> builder.build()
        );

        Assertions.assertEquals("Right axis not provided", exception.getMessage());
    }

    @Test
    public void orientationShouldNotAllowMissingForwardAxis() {
        Orientation.Builder builder = Orientation.builder()
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> builder.build()
        );

        Assertions.assertEquals("Forward axis not provided", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("validCombinationsOfAxesForOrientation")
    public void orientationShouldAllowAllCombinationsOfPerpendicularAxes(final List<Axis> axes) {
        Orientation.Builder builder = Orientation.builder()
                .withForwardAxis(axes.get(0))
                .withRightAxis(axes.get(1))
                .withUpAxis(axes.get(2));

        Orientation orientation = builder.build();

        Assertions.assertEquals(axes.get(0), orientation.getForwardAxis());
        Assertions.assertEquals(axes.get(1), orientation.getRightAxis());
        Assertions.assertEquals(axes.get(2), orientation.getUpAxis());
    }

    @ParameterizedTest
    @MethodSource("invalidCombinationsOfAxes")
    public void orientationShouldNotAllowAnyCombinationOfParallelAxes(final List<Axis> axes) {
        Orientation.Builder builder = Orientation.builder()
                .withForwardAxis(axes.get(0))
                .withRightAxis(axes.get(1))
                .withUpAxis(axes.get(2));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> builder.build()
        );

        Assertions.assertTrue(exception.getMessage().startsWith("Illegal combination of perpendicular axes"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithForwardValueShouldCreateValidPositionVector(final double value) {
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        Vector3.Accessible result = orientation.multiply(value, 0.0, 0.0, Factories.FACTORY_CONST_VECTOR3_xyz);

        VecMathAssertions.assertEquals(new ImmutableVector3(value, 0.0, 0.0), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithForwardValueShouldResultInValidPositionVector(final double value) {
        Vector3.AccessibleAndMutable result = new MutableVector3();
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        orientation.multiply(result, value, 0.0, 0.0);

        VecMathAssertions.assertEquals(new ImmutableVector3(value, 0.0, 0.0), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithRightValueShouldCreateValidPositionVector(final double value) {
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        Vector3.Accessible result = orientation.multiply(0.0, value, 0.0, Factories.FACTORY_CONST_VECTOR3_xyz);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, value, 0.0), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithRightValueShouldResultInValidPositionVector(final double value) {
        Vector3.AccessibleAndMutable result = new MutableVector3();
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        orientation.multiply(result, 0.0, value, 0.0);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, value, 0.0), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithUpValueShouldCreateValidPositionVector(final double value) {
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        Vector3.Accessible result = orientation.multiply(0.0, 0.0, value, Factories.FACTORY_CONST_VECTOR3_xyz);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, 0.0, value), result, 0.000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, -0.1, 0.0, 0.1, 1.0, 10.0})
    public void multiplyWithUpValueShouldResultInValidPositionVector(final double value) {
        Vector3.AccessibleAndMutable result = new MutableVector3();
        Orientation orientation = Orientation.builder()
                .withForwardAxis(Axis.POSITIVE_X)
                .withRightAxis(Axis.POSITIVE_Y)
                .withUpAxis(Axis.POSITIVE_Z)
                .build();

        orientation.multiply(result, 0.0, 0.0, value);

        VecMathAssertions.assertEquals(new ImmutableVector3(0.0, 0.0, value), result, 0.000001);
    }

    private static Stream<List<Axis>> invalidCombinationsOfAxes() {
        return new AxialCombinationsGenerator().generateCombinationsOfSize(3)
                .filter(new AxialCombinationsGenerator.AnyPairPredicate((a1, a2) -> VecMath.dot(a1, a2) != 0.0));
    }

}
