package ee.ristoseene.raytracer.eyebased.core.image;

import ee.ristoseene.raytracer.eyebased.core.image.sampler2d.LinearSampler2D;
import ee.ristoseene.raytracer.eyebased.core.image.sampler2d.NearestSampler2D;
import ee.ristoseene.raytracer.eyebased.core.vecmath.vector4.MutableVector4;

public final class PixelTransferUtils {

    private PixelTransferUtils() {}

    public static void copy(Image2D.Readable source, Image2D.Writable destination) {
        final int imageWidth = destination.getWidth();
        final int imageHeight = destination.getHeight();

        if (source.getWidth() != imageWidth || source.getHeight() != imageHeight) {
            throw new IllegalArgumentException("Source and destination image dimensions do not match");
        }

        final MutableVector4 rgba = new MutableVector4(0.0, 0.0, 0.0, 1.0);

        for (int y = 0; y < imageHeight; ++y) {
            for (int x = 0; x < imageWidth; ++x) {
                source.readPixel(rgba, x, y);
                destination.writePixel(rgba, x, y);
            }
        }
    }

    public static void blit(Image2D.Readable source, Image2D.Writable destination, ResolveMode resolveMode) {
        final int destinationWidth = destination.getWidth();
        final int destinationHeight = destination.getHeight();

        final Sampler2D sourceSampler = resolveMode.createSamplerFor(source);
        final MutableVector4 rgba = new MutableVector4(0.0, 0.0, 0.0, 1.0);

        for (int y = 0; y < destinationHeight; ++y) {
            final double sourceY = (y + 0.5) / destinationHeight;

            for (int x = 0; x < destinationWidth; ++x) {
                final double sourceX = (x + 0.5) / destinationWidth;

                sourceSampler.sample(rgba, sourceX, sourceY);
                destination.writePixel(rgba, x, y);
            }
        }
    }

    public enum ResolveMode implements SamplerFactory2D {

        NEAREST {
            @Override
            public Sampler2D createSamplerFor(Image2D.Readable image) {
                return new NearestSampler2D(image, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
            }
        },

        LINEAR {
            @Override
            public Sampler2D createSamplerFor(Image2D.Readable image) {
                return new LinearSampler2D(image, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
            }
        };

    }

}
