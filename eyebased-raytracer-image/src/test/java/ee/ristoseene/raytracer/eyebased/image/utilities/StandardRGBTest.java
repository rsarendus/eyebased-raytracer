package ee.ristoseene.raytracer.eyebased.image.utilities;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.helpers.VerboseVecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;
import ee.ristoseene.vecmath.mutable.MutableVector3;
import ee.ristoseene.vecmath.mutable.MutableVector4;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ee.ristoseene.raytracer.eyebased.image.utilities.StandardRGB.linearToSRGB;
import static ee.ristoseene.raytracer.eyebased.image.utilities.StandardRGB.sRGBToLinear;

public class StandardRGBTest {

    private static final double DELTA = 0.0000001;

    @ParameterizedTest
    @MethodSource("vectors3")
    public void linearToSRGBShouldCreateCorrectVector3(final Vector3.Accessible linearRGB) {
        Vector3.Accessible expectedRGB = new ImmutableVector3(
                linearToSRGB(linearRGB.x()),
                linearToSRGB(linearRGB.y()),
                linearToSRGB(linearRGB.z())
        );

        VecMathAssertions.assertEquals(expectedRGB, linearToSRGB(linearRGB.x(), linearRGB.y(), linearRGB.z(), Factories.FACTORY_CONST_VECTOR3_xyz), DELTA);
        VecMathAssertions.assertEquals(expectedRGB, linearToSRGB(linearRGB, Factories.FACTORY_CONST_VECTOR3_xyz), DELTA);

        Vector3.AccessibleAndMutable consumer = new MutableVector3(Double.NaN);
        linearToSRGB(consumer, linearRGB.x(), linearRGB.y(), linearRGB.z());
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);

        consumer.xyz(Double.NaN);
        linearToSRGB(consumer, linearRGB);
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);
    }

    @ParameterizedTest
    @MethodSource("vectors3")
    public void sRGBToLinearShouldCreateCorrectVector3(final Vector3.Accessible sRGB) {
        Vector3.Accessible expectedRGB = new ImmutableVector3(
                sRGBToLinear(sRGB.x()),
                sRGBToLinear(sRGB.y()),
                sRGBToLinear(sRGB.z())
        );

        VecMathAssertions.assertEquals(expectedRGB, sRGBToLinear(sRGB.x(), sRGB.y(), sRGB.z(), Factories.FACTORY_CONST_VECTOR3_xyz), DELTA);
        VecMathAssertions.assertEquals(expectedRGB, sRGBToLinear(sRGB, Factories.FACTORY_CONST_VECTOR3_xyz), DELTA);

        Vector3.AccessibleAndMutable consumer = new MutableVector3(Double.NaN);
        sRGBToLinear(consumer, sRGB.x(), sRGB.y(), sRGB.z());
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);

        consumer.xyz(Double.NaN);
        sRGBToLinear(consumer, sRGB);
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);
    }

    private static Stream<Vector3.Accessible> vectors3() {
        return Stream.of(
                new ImmutableVector3(0.0),
                new ImmutableVector3(0.0, 0.000001, 0.000002),
                new ImmutableVector3(0.000002, 0.0, 0.000001),
                new ImmutableVector3(0.000001, 0.000002, 0.0),
                new ImmutableVector3(0.003130),
                new ImmutableVector3(0.003131),
                new ImmutableVector3(0.040448),
                new ImmutableVector3(0.040449),
                new ImmutableVector3(0.5, 0.6, 0.7),
                new ImmutableVector3(0.7, 0.5, 0.6),
                new ImmutableVector3(0.6, 0.7, 0.5),
                new ImmutableVector3(1.0, 0.999999, 0.999998),
                new ImmutableVector3(0.999998, 1.0, 0.999999),
                new ImmutableVector3(0.999999, 0.999998, 1.0),
                new ImmutableVector3(1.0)
        ).map(VerboseVecMath::verbose);
    }

    @ParameterizedTest
    @MethodSource("vectors4")
    public void linearToSRGBShouldCreateCorrectVector4(final Vector4.Accessible linearRGB) {
        Vector4.Accessible expectedRGB = new ImmutableVector4(
                linearToSRGB(linearRGB.x()),
                linearToSRGB(linearRGB.y()),
                linearToSRGB(linearRGB.z()),
                linearRGB.w()
        );

        VecMathAssertions.assertEquals(expectedRGB, linearToSRGB(linearRGB.x(), linearRGB.y(), linearRGB.z(), linearRGB.w(), Factories.FACTORY_CONST_VECTOR4_xyzw), DELTA);
        VecMathAssertions.assertEquals(expectedRGB, linearToSRGB(linearRGB, Factories.FACTORY_CONST_VECTOR4_xyzw), DELTA);

        Vector4.AccessibleAndMutable consumer = new MutableVector4(Double.NaN);
        linearToSRGB(consumer, linearRGB.x(), linearRGB.y(), linearRGB.z(), linearRGB.w());
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);

        consumer.xyzw(Double.NaN);
        linearToSRGB(consumer, linearRGB);
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);
    }

    @ParameterizedTest
    @MethodSource("vectors4")
    public void sRGBToLinearShouldCreateCorrectVector4(final Vector4.Accessible sRGB) {
        Vector4.Accessible expectedRGB = new ImmutableVector4(
                sRGBToLinear(sRGB.x()),
                sRGBToLinear(sRGB.y()),
                sRGBToLinear(sRGB.z()),
                sRGB.w()
        );

        VecMathAssertions.assertEquals(expectedRGB, sRGBToLinear(sRGB.x(), sRGB.y(), sRGB.z(), sRGB.w(), Factories.FACTORY_CONST_VECTOR4_xyzw), DELTA);
        VecMathAssertions.assertEquals(expectedRGB, sRGBToLinear(sRGB, Factories.FACTORY_CONST_VECTOR4_xyzw), DELTA);

        Vector4.AccessibleAndMutable consumer = new MutableVector4(Double.NaN);
        sRGBToLinear(consumer, sRGB.x(), sRGB.y(), sRGB.z(), sRGB.w());
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);

        consumer.xyzw(Double.NaN);
        sRGBToLinear(consumer, sRGB);
        VecMathAssertions.assertEquals(expectedRGB, consumer, DELTA);
    }

    private static Stream<Vector4.Accessible> vectors4() {
        return Stream.of(
                new ImmutableVector4(0.0),
                new ImmutableVector4(0.0, 0.000001, 0.000002, 0.000003),
                new ImmutableVector4(0.000002, 0.0, 0.000003, 0.000001),
                new ImmutableVector4(0.000001, 0.000003, 0.0, 0.000002),
                new ImmutableVector4(0.000003, 0.000002, 0.000001, 0.0),
                new ImmutableVector4(0.003130),
                new ImmutableVector4(0.003131),
                new ImmutableVector4(0.040448),
                new ImmutableVector4(0.040449),
                new ImmutableVector4(0.5, 0.6, 0.7, 0.8),
                new ImmutableVector4(0.7, 0.5, 0.8, 0.6),
                new ImmutableVector4(0.6, 0.8, 0.5, 0.7),
                new ImmutableVector4(0.8, 0.7, 0.6, 0.5),
                new ImmutableVector4(1.0, 0.999999, 0.999998, 0.999997),
                new ImmutableVector4(0.999998, 1.0, 0.999997, 0.999999),
                new ImmutableVector4(0.999999, 0.999997, 1.0, 0.999998),
                new ImmutableVector4(0.999997, 0.999998, 0.999999, 1.0),
                new ImmutableVector4(1.0)
        ).map(VerboseVecMath::verbose);
    }

}
