package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.image.Image2D;
import ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.mutable.MutableVector4;

public class LinearSampler2D extends AbstractSampler2D {

    public LinearSampler2D(final Image2D.Readable image, final SamplingWrapMode wrapModeX, final SamplingWrapMode wrapModeY) {
        super(image, wrapModeX, wrapModeY);
    }

    @Override
    public void sample(final Vector4.Consumer destinationRGBA, final double x, final double y) {
        final double absoluteX = mapSampleCoordinateToAbsoluteCoordinate(x, imageWidth);
        final double absoluteY = mapSampleCoordinateToAbsoluteCoordinate(y, imageHeight);

        final long truncatedX = truncateTowardsLower(absoluteX);
        final long truncatedY = truncateTowardsLower(absoluteY);

        final double ratioX2 = absoluteX - truncatedX;
        final double ratioY2 = absoluteY - truncatedY;
        final double ratioX1 = 1.0 - ratioX2;
        final double ratioY1 = 1.0 - ratioY2;

        final int wrappedX1 = wrapModeX.wrap(truncatedX, imageWidth);
        final int wrappedY1 = wrapModeY.wrap(truncatedY, imageHeight);
        final int wrappedX2 = wrapModeX.wrap(truncatedX + 1L, imageWidth);
        final int wrappedY2 = wrapModeY.wrap(truncatedY + 1L, imageHeight);

        final MutableVector4 fetcher = new MutableVector4();
        final MutableVector4 accumulator = new MutableVector4(0.0);

        image.readPixel(fetcher, wrappedX1, wrappedY1);
        VecMath.multiplyAdd(accumulator, fetcher, ratioX1 * ratioY1, accumulator);

        image.readPixel(fetcher, wrappedX2, wrappedY1);
        VecMath.multiplyAdd(accumulator, fetcher, ratioX2 * ratioY1, accumulator);

        image.readPixel(fetcher, wrappedX1, wrappedY2);
        VecMath.multiplyAdd(accumulator, fetcher, ratioX1 * ratioY2, accumulator);

        image.readPixel(fetcher, wrappedX2, wrappedY2);
        VecMath.multiplyAdd(accumulator, fetcher, ratioX2 * ratioY2, accumulator);

        accumulator.xyzwTo(destinationRGBA);
    }

    private static double mapSampleCoordinateToAbsoluteCoordinate(final double coordinate, final int imageSize) {
        return coordinate * imageSize - 0.5;
    }

    private static long truncateTowardsLower(final double value) {
        return (long) (value + (value < 0.0 ? -1.0 : 0.0));
    }

}
