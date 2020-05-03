package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.PixelLocation;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;

import java.util.Objects;

public final class DefaultHorizontalScanLinePixelLocationResolver extends AbstractScanLineRasterBlockResolver<PixelLocation> {

    private final int x, y;

    public DefaultHorizontalScanLinePixelLocationResolver(final RasterArea rasterArea) {
        super(
                Objects.requireNonNull(rasterArea, "Raster area not provided").getHeight(),
                Objects.requireNonNull(rasterArea, "Raster area not provided").getWidth()
        );
        this.x = rasterArea.getX();
        this.y = rasterArea.getY();
    }

    @Override
    protected PixelLocation resolveBlockPerScanLine(final int scanLineIndex, final int scanLineBlockIndex) {
        return new PixelLocation(
                x + scanLineBlockIndex,
                y + scanLineIndex
        );
    }

}
