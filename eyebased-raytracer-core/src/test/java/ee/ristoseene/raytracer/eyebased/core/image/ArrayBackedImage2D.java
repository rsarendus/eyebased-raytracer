package ee.ristoseene.raytracer.eyebased.core.image;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public class ArrayBackedImage2D implements Image2D.Readable, Image2D.Writable {

    private final double[] backingArray;

    private final int width;
    private final int height;
    private final int channels;

    public ArrayBackedImage2D(int width, int height) {
        this(width, height, 4);
    }

    public ArrayBackedImage2D(int width, int height, int channels) {
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
    public void readPixel(Vector4.Mutable destinationRGBA, int x, int y) {
        final int baseIndex = channels * (width * y + x);
        if (channels > 0) destinationRGBA.x(backingArray[baseIndex]);
        if (channels > 1) destinationRGBA.y(backingArray[baseIndex + 1]);
        if (channels > 2) destinationRGBA.z(backingArray[baseIndex + 2]);
        if (channels > 3) destinationRGBA.w(backingArray[baseIndex + 3]);
    }

    @Override
    public void writePixel(Vector4.Accessible sourceRGBA, int x, int y) {
        final int baseIndex = channels * (width * y + x);
        if (channels > 0) backingArray[baseIndex] = sourceRGBA.x();
        if (channels > 1) backingArray[baseIndex + 1] = sourceRGBA.y();
        if (channels > 2) backingArray[baseIndex + 2] = sourceRGBA.z();
        if (channels > 3) backingArray[baseIndex + 3] = sourceRGBA.w();
    }

}
