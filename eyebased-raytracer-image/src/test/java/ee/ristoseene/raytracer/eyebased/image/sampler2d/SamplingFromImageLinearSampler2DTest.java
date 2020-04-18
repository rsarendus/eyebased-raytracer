package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.mutable.MutableVector4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SamplingFromImageLinearSampler2DTest extends SamplingFromImageTest {

    private MutableVector4 rgba;

    @BeforeEach
    public void setUpFetchingVector() {
        rgba = new MutableVector4(0.0, 0.0, 0.0, 1.0);
    }


    @Test
    public void sampleFromCenterOfPixelForClampShouldReturnValueOfThatPixel() {
        createClampingSampler().sample(rgba, 0.5, 0.5);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_1_1, rgba, DELTA);
    }

    @Test
    public void sampleWithinLimitsForClampShouldReturnModulatedValueOfFourNearestPixels() {
        createClampingSampler().sample(rgba, 0.45, 0.55);
        VecMathAssertions.assertEquals(lerp(
                lerp(DEFAULT_SOURCE_PIXEL_0_1, DEFAULT_SOURCE_PIXEL_1_1, 1.0 - 0.05 * 3),
                lerp(DEFAULT_SOURCE_PIXEL_0_2, DEFAULT_SOURCE_PIXEL_1_2, 1.0 - 0.05 * 3),
                0.05 * 3
        ), rgba, DELTA);
    }

    @Test
    public void sampleZeroOrBelowZeroForClampShouldReturnValueOfZeroCoordinates() {
        createClampingSampler().sample(rgba, 0.0, -0.9);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_0_0, rgba, DELTA);
    }

    @Test
    public void sampleMaximumOrAboveMaximumForClampShouldReturnValueOfMaximumCoordinates() {
        createClampingSampler().sample(rgba, 1.0, 1.9);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_2_2, rgba, DELTA);
    }


    @Test
    public void sampleFromCenterOfPixelForRepeatShouldReturnValueOfThatPixel() {
        createRepeatingSampler().sample(rgba, 0.5, 0.5);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_1_1, rgba, DELTA);
    }

    @Test
    public void sampleWithinLimitsForRepeatShouldReturnModulatedValueOfFourNearestPixels() {
        createRepeatingSampler().sample(rgba, 0.45, 0.55);
        VecMathAssertions.assertEquals(lerp(
                lerp(DEFAULT_SOURCE_PIXEL_0_1, DEFAULT_SOURCE_PIXEL_1_1, 1.0 - 0.05 * 3),
                lerp(DEFAULT_SOURCE_PIXEL_0_2, DEFAULT_SOURCE_PIXEL_1_2, 1.0 - 0.05 * 3),
                0.05 * 3
        ), rgba, DELTA);
    }

    @Test
    public void sampleAroundZeroForRepeatShouldReturnModulatedValueOfZeroAndWrappedAroundMaximumCoordinates() {
        createRepeatingSampler().sample(rgba, 0.05, -0.05);
        VecMathAssertions.assertEquals(lerp(
                lerp(DEFAULT_SOURCE_PIXEL_2_2, DEFAULT_SOURCE_PIXEL_0_2, 0.5 + 0.05 * 3),
                lerp(DEFAULT_SOURCE_PIXEL_2_0, DEFAULT_SOURCE_PIXEL_0_0, 0.5 + 0.05 * 3),
                0.5 - 0.05 * 3
        ), rgba, DELTA);
    }

    @Test
    public void sampleAroundOneForRepeatShouldReturnModulatedValueOfMaximumAndWrappedAroundZeroCoordinates() {
        createRepeatingSampler().sample(rgba, 0.95, 1.05);
        VecMathAssertions.assertEquals(lerp(
                lerp(DEFAULT_SOURCE_PIXEL_2_2, DEFAULT_SOURCE_PIXEL_0_2, 0.5 - 0.05 * 3),
                lerp(DEFAULT_SOURCE_PIXEL_2_0, DEFAULT_SOURCE_PIXEL_0_0, 0.5 - 0.05 * 3),
                0.5 + 0.05 * 3
        ), rgba, DELTA);
    }


    private LinearSampler2D createClampingSampler() {
        return new LinearSampler2D(sourceImage3x3, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
    }

    private LinearSampler2D createRepeatingSampler() {
        return new LinearSampler2D(sourceImage3x3, SamplingWrapMode.REPEAT, SamplingWrapMode.REPEAT);
    }

    private static Vector4.Accessible lerp(Vector4.Accessible vector1, Vector4.Accessible vector2, double ratio) {
        return VecMath.lerp(vector1, vector2, ratio, Factories.FACTORY_CONST_VECTOR4_xyzw);
    }

}
