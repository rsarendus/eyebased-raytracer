package ee.ristoseene.raytracer.eyebased.core.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.image.Image2D;
import ee.ristoseene.raytracer.eyebased.core.image.Sampler2D;
import ee.ristoseene.raytracer.eyebased.core.image.SamplingWrapMode;

public abstract class AbstractSampler2D implements Sampler2D {

    protected final Image2D.Readable image;
    protected final SamplingWrapMode wrapModeX;
    protected final SamplingWrapMode wrapModeY;

    protected final int imageWidth;
    protected final int imageHeight;

    protected AbstractSampler2D(Image2D.Readable image, SamplingWrapMode wrapModeX, SamplingWrapMode wrapModeY) {
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();

        if (imageWidth < 1 || imageHeight < 1) {
            throw new IllegalArgumentException(String.format("Unsupported image size %s x %s", imageWidth, imageHeight));
        } else if (wrapModeX == null || wrapModeY == null) {
            throw new NullPointerException("Wrap mode cannot be null");
        }

        this.image = image;
        this.wrapModeX = wrapModeX;
        this.wrapModeY = wrapModeY;
    }

}
