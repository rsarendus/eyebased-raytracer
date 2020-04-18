package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode;
import ee.ristoseene.vecmath.mutable.MutableVector4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SamplingFromImageNearestSampler2DTest extends SamplingFromImageTest {

    private MutableVector4 rgba;

    @BeforeEach
    public void setUpFetchingVector() {
        rgba = new MutableVector4(0.0, 0.0, 0.0, 1.0);
    }


    @Test
    public void sampleWithinLimitsForClampShouldReturnValueOfNearestPixel() {
        createClampingSampler().sample(rgba, 0.499, 0.501);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_1_1, rgba, DELTA);
    }

    @Test
    public void sampleZeroOrBelowForClampShouldReturnValueOfZeroCoordinates() {
        createClampingSampler().sample(rgba, 0.0, -0.9);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_0_0, rgba, DELTA);
    }

    @Test
    public void sampleMaximumOrAboveForClampShouldReturnValueOfMaximumCoordinates() {
        createClampingSampler().sample(rgba, 1.0, 1.9);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_2_2, rgba, DELTA);
    }


    @Test
    public void sampleWithinLimitsForRepeatShouldReturnValueOfNearestPixel() {
        createRepeatingSampler().sample(rgba, 0.499, 0.501);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_1_1, rgba, DELTA);
    }

    @Test
    public void sampleNearZeroForRepeatShouldReturnValueOfZeroCoordinates() {
        createRepeatingSampler().sample(rgba, 0.002, 0.001);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_0_0, rgba, DELTA);
    }

    @Test
    public void sampleBelowZeroForRepeatShouldReturnValueOfWrappedAroundCoordinates() {
        createRepeatingSampler().sample(rgba, -0.334, -0.001);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_1_2, rgba, DELTA);
    }

    @Test
    public void sampleNearOneForRepeatShouldReturnValueOfMaximumCoordinates() {
        createRepeatingSampler().sample(rgba, 0.998, 0.999);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_2_2, rgba, DELTA);
    }

    @Test
    public void sampleAboveOneForRepeatShouldReturnValueOfWrappedAroundCoordinates() {
        createRepeatingSampler().sample(rgba, 1.001, 1.334);
        VecMathAssertions.assertEquals(DEFAULT_SOURCE_PIXEL_0_1, rgba, DELTA);
    }


    private NearestSampler2D createClampingSampler() {
        return new NearestSampler2D(sourceImage3x3, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
    }

    private NearestSampler2D createRepeatingSampler() {
        return new NearestSampler2D(sourceImage3x3, SamplingWrapMode.REPEAT, SamplingWrapMode.REPEAT);
    }

}
