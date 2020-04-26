package ee.ristoseene.raytracer.eyebased.image.helpers;

import ee.ristoseene.raytracer.eyebased.image.Image2D;
import ee.ristoseene.vecmath.Vector4;

public class ArrayBackedImage2D implements Image2D.Readable, Image2D.Writable {

    private final double[] backingArray;

    private final int width;
    private final int height;
    private final int channels;

    public ArrayBackedImage2D(final int width, final int height) {
        this(width, height, 4);
    }

    public ArrayBackedImage2D(final int width, final int height, final int channels) {
        if (channels < 0 || channels > 4) {
            throw new IllegalStateException(String.format("Invalid number of color channels: %d", channels));
        } else if (width < 0 || height < 0) {
            throw new IllegalStateException(String.format("Invalid image size: %d x %d", width, height));
        }

        this.width = width;
        this.height = height;
        this.channels = channels;
        this.backingArray = new double[width * height * channels];
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public <R> R readPixel(final int x, final int y, final Vector4.Factory<R> resultFactory) {
        final int baseIndex = channels * (width * y + x);
        return resultFactory.create(
                (channels > 0) ? backingArray[baseIndex] : 0.0,
                (channels > 1) ? backingArray[baseIndex + 1] : 0.0,
                (channels > 2) ? backingArray[baseIndex + 2] : 0.0,
                (channels > 3) ? backingArray[baseIndex + 3] : 1.0
        );
    }

    @Override
    public void readPixel(final Vector4.Consumer resultConsumer, final int x, final int y) {
        final int baseIndex = channels * (width * y + x);
        resultConsumer.xyzw(
                (channels > 0) ? backingArray[baseIndex] : 0.0,
                (channels > 1) ? backingArray[baseIndex + 1] : 0.0,
                (channels > 2) ? backingArray[baseIndex + 2] : 0.0,
                (channels > 3) ? backingArray[baseIndex + 3] : 1.0
        );
    }

    @Override
    public void writePixel(final int x, final int y, final double r, final double g, final double b, final double a) {
        final int baseIndex = channels * (width * y + x);
        if (channels > 0) backingArray[baseIndex + 0] = r;
        if (channels > 1) backingArray[baseIndex + 1] = g;
        if (channels > 2) backingArray[baseIndex + 2] = b;
        if (channels > 3) backingArray[baseIndex + 3] = a;
    }

}
