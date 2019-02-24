package ee.ristoseene.raytracer.eyebased.core.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.image.Image2D;
import ee.ristoseene.raytracer.eyebased.core.image.SamplingWrapMode;
import ee.ristoseene.raytracer.eyebased.core.vecmath.VecMath;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;
import ee.ristoseene.raytracer.eyebased.core.vecmath.vector4.MutableVector4;

public class LinearSampler2D extends AbstractSampler2D {

    public LinearSampler2D(Image2D.Readable image, SamplingWrapMode wrapModeX, SamplingWrapMode wrapModeY) {
        super(image, wrapModeX, wrapModeY);
    }

    @Override
    public void sample(Vector4.Mutable destinationRGBA, double x, double y) {
        double absoluteX = mapSampleCoordinateToAbsoluteCoordinate(x, imageWidth);
        double absoluteY = mapSampleCoordinateToAbsoluteCoordinate(y, imageHeight);

        long truncatedX = truncateTowardsLower(absoluteX);
        long truncatedY = truncateTowardsLower(absoluteY);

        double ratioX2 = absoluteX - truncatedX;
        double ratioY2 = absoluteY - truncatedY;
        double ratioX1 = 1.0 - ratioX2;
        double ratioY1 = 1.0 - ratioY2;

        int wrappedX1 = wrapModeX.wrap(truncatedX, imageWidth);
        int wrappedY1 = wrapModeY.wrap(truncatedY, imageHeight);
        int wrappedX2 = wrapModeX.wrap(truncatedX + 1L, imageWidth);
        int wrappedY2 = wrapModeY.wrap(truncatedY + 1L, imageHeight);

        MutableVector4 fetcher = new MutableVector4(0.0, 0.0, 0.0, 1.0);
        MutableVector4 accumulator = new MutableVector4(0.0);

        image.readPixel(fetcher, wrappedX1, wrappedY1);
        VecMath.madd(fetcher, ratioX1 * ratioY1, accumulator, accumulator);

        image.readPixel(fetcher, wrappedX2, wrappedY1);
        VecMath.madd(fetcher, ratioX2 * ratioY1, accumulator, accumulator);

        image.readPixel(fetcher, wrappedX1, wrappedY2);
        VecMath.madd(fetcher, ratioX1 * ratioY2, accumulator, accumulator);

        image.readPixel(fetcher, wrappedX2, wrappedY2);
        VecMath.madd(fetcher, ratioX2 * ratioY2, accumulator, accumulator);

        destinationRGBA.xyzw(accumulator);
    }

    private static double mapSampleCoordinateToAbsoluteCoordinate(double coordinate, int imageSize) {
        return coordinate * imageSize - 0.5;
    }

    private static long truncateTowardsLower(double value) {
        return (long) (value + (value < 0.0 ? -1.0 : 0.0));
    }

}
