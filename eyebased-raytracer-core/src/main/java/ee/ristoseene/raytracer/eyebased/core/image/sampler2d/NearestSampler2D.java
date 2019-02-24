package ee.ristoseene.raytracer.eyebased.core.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.image.Image2D;
import ee.ristoseene.raytracer.eyebased.core.image.SamplingWrapMode;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public class NearestSampler2D extends AbstractSampler2D {

    public NearestSampler2D(Image2D.Readable image, SamplingWrapMode wrapModeX, SamplingWrapMode wrapModeY) {
        super(image, wrapModeX, wrapModeY);
    }

    @Override
    public void sample(Vector4.Mutable destinationRGBA, double x, double y) {
        image.readPixel(destinationRGBA,
                wrapModeX.wrap(mapSampleCoordinateToAbsoluteCoordinate(x, imageWidth), imageWidth),
                wrapModeY.wrap(mapSampleCoordinateToAbsoluteCoordinate(y, imageHeight), imageHeight)
        );
    }

    private static long mapSampleCoordinateToAbsoluteCoordinate(double coordinate, int imageSize) {
        return (long) (coordinate * imageSize + (coordinate < 0.0 ? -1.0 : 0.0));
    }

}
