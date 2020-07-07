package ee.ristoseene.raytracer.eyebased.demo.gui;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.image.utilities.StandardRGB;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelValue;
import ee.ristoseene.raytracer.eyebased.rasterization.RenderTarget;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.function.Consumer;

public class GuiRenderTarget implements RenderTarget {

    private final BufferedImage backingImage;
    private final Consumer<PixelLocation> renderUpdateListener;

    public GuiRenderTarget(final BufferedImage image, final Consumer<PixelLocation> renderUpdateListener) {
        this.backingImage = Objects.requireNonNull(image);
        this.renderUpdateListener = renderUpdateListener;
    }

    @Override
    public void writePixel(final PixelValue pixelValue) {
        final SampleValue sampleValue = pixelValue.getSampleValue();
        final Vector3.Accessible rgb = sampleValue.getRGB();
        backingImage.setRGB(
                pixelValue.getPixelLocation().getX(),
                pixelValue.getPixelLocation().getY(),
                (0xff << 24)
                        | (toNormalizedUint8(rgb.x()) << 16)
                        | (toNormalizedUint8(rgb.y()) << 8)
                        | (toNormalizedUint8(rgb.z()))
        );
        if (renderUpdateListener != null) {
            renderUpdateListener.accept(pixelValue.getPixelLocation());
        }
    }

    private static int toNormalizedUint8(final double value) {
        return ((int) VecMath.clamp(StandardRGB.linearToSRGB(value) * 255.0, 0.0, 255.0)) & 0xff;
    }

    @Override
    public int getWidth() {
        return backingImage.getWidth();
    }

    @Override
    public int getHeight() {
        return backingImage.getHeight();
    }

}
