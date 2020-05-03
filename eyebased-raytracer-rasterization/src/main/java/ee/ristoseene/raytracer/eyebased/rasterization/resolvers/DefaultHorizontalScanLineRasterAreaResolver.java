package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;

import java.util.Objects;

public final class DefaultHorizontalScanLineRasterAreaResolver extends AbstractScanLineRasterAreaResolver {

    private final RasterArea rasterArea;
    private final int horizontalBlockSize;
    private final int verticalBlockSize;

    public DefaultHorizontalScanLineRasterAreaResolver(
            final RasterArea rasterArea, final BlockSizeResolver horizontalBlockSizeResolver, final BlockSizeResolver verticalBlockSizeResolver
    ) {
        this(
                Objects.requireNonNull(rasterArea, "Raster area not provided"),
                Objects.requireNonNull(horizontalBlockSizeResolver, "Horizontal block size resolver not provided").resolveBlockSize(rasterArea.getWidth()),
                Objects.requireNonNull(verticalBlockSizeResolver, "Vertical block size resolver not provided").resolveBlockSize(rasterArea.getHeight())
        );
    }

    private DefaultHorizontalScanLineRasterAreaResolver(final RasterArea rasterArea, final int horizontalBlockSize, final int verticalBlockSize) {
        super(resolveBlockCount(verticalBlockSize, rasterArea.getHeight()), resolveBlockCount(horizontalBlockSize, rasterArea.getWidth()));

        this.rasterArea = rasterArea;
        this.horizontalBlockSize = horizontalBlockSize;
        this.verticalBlockSize = verticalBlockSize;
    }

    @Override
    protected RasterArea resolveBlockPerScanLine(final int scanLineIndex, final int scanLineBlockIndex) {
        final int offsetX = scanLineBlockIndex * horizontalBlockSize;
        final int offsetY = scanLineIndex * verticalBlockSize;

        return new RasterArea(
                rasterArea.getX() + offsetX,
                rasterArea.getY() + offsetY,
                Math.min(horizontalBlockSize, rasterArea.getWidth() - offsetX),
                Math.min(verticalBlockSize, rasterArea.getHeight() - offsetY)
        );
    }

}
