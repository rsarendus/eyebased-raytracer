package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;

public abstract class AbstractScanLineRasterAreaResolver extends AbstractScanLineRasterBlockResolver<RasterArea> {

    protected AbstractScanLineRasterAreaResolver(final int numberOfScanLines, final int blocksPerScanLine) {
        super(numberOfScanLines, blocksPerScanLine);
    }

    protected static int resolveBlockCount(final int blockSize, final int totalSize) {
        if (blockSize < 1) {
            throw new IllegalArgumentException("Invalid block size: " + blockSize);
        }
        return (totalSize + blockSize - 1) / blockSize;
    }

    @Override
    protected abstract RasterArea resolveBlockPerScanLine(int scanLineIndex, int scanLineBlockIndex);

    @FunctionalInterface
    public interface BlockSizeResolver {
        int resolveBlockSize(int totalSize);
    }

}
