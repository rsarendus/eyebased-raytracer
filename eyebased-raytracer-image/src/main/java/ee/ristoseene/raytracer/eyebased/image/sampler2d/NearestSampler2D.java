package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.image.Image2D;
import ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode;
import ee.ristoseene.vecmath.Vector4;

public class NearestSampler2D extends AbstractSampler2D {

    public NearestSampler2D(final Image2D.Readable image, final SamplingWrapMode wrapModeX, final SamplingWrapMode wrapModeY) {
        super(image, wrapModeX, wrapModeY);
    }

    @Override
    public void sample(final Vector4.Consumer destinationRGBA, final double x, final double y) {
        image.readPixel(destinationRGBA,
                wrapModeX.wrap(mapSampleCoordinateToAbsoluteCoordinate(x, imageWidth), imageWidth),
                wrapModeY.wrap(mapSampleCoordinateToAbsoluteCoordinate(y, imageHeight), imageHeight)
        );
    }

    private static long mapSampleCoordinateToAbsoluteCoordinate(final double coordinate, final int imageSize) {
        return (long) (coordinate * imageSize + (coordinate < 0.0 ? -1.0 : 0.0));
    }

}
