package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.image.helpers.ArrayBackedImage2D;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.immutable.ImmutableVector4;
import org.junit.jupiter.api.BeforeEach;

abstract class SamplingFromImageTest {

    protected static final double DELTA = 0.000001;

    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_0_0 = new ImmutableVector4(0.1, 0.2, 0.3, 0.1);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_0_1 = new ImmutableVector4(0.2, 0.4, 0.8, 0.2);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_0_2 = new ImmutableVector4(0.3, 0.6, 0.9, 0.3);

    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_1_0 = new ImmutableVector4(0.2, 0.3, 0.1, 0.4);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_1_1 = new ImmutableVector4(0.4, 0.8, 0.2, 0.5);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_1_2 = new ImmutableVector4(0.6, 0.9, 0.3, 0.6);

    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_2_0 = new ImmutableVector4(0.3, 0.1, 0.2, 0.7);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_2_1 = new ImmutableVector4(0.8, 0.2, 0.4, 0.8);
    protected static final Vector4.Accessible DEFAULT_SOURCE_PIXEL_2_2 = new ImmutableVector4(0.9, 0.3, 0.6, 0.9);

    protected ArrayBackedImage2D sourceImage3x3;

    @BeforeEach
    public void setUpSourceImage() {
        sourceImage3x3 = new ArrayBackedImage2D(3, 3, 4);

        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_0_0, 0, 0);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_0_1, 0, 1);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_0_2, 0, 2);

        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_1_0, 1, 0);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_1_1, 1, 1);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_1_2, 1, 2);

        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_2_0, 2, 0);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_2_1, 2, 1);
        sourceImage3x3.writePixel(DEFAULT_SOURCE_PIXEL_2_2, 2, 2);
    }

}
