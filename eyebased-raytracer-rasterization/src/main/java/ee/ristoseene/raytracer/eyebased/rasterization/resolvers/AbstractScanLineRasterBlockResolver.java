package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterSpliterator;

public abstract class AbstractScanLineRasterBlockResolver<T> implements RasterSpliterator.BlockResolver<T> {

    protected final long totalBlockCount;
    protected final int blocksPerScanLine;

    protected AbstractScanLineRasterBlockResolver(final int numberOfScanLines, final int blocksPerScanLine) {
        if (numberOfScanLines < 1) {
            throw new IllegalArgumentException("Invalid number of scan lines: " + numberOfScanLines);
        } else if (blocksPerScanLine < 1) {
            throw new IllegalArgumentException("Invalid number of blocks per scan line: " + blocksPerScanLine);
        }

        this.totalBlockCount = (long) numberOfScanLines * (long) blocksPerScanLine;
        this.blocksPerScanLine = blocksPerScanLine;
    }

    protected abstract T resolveBlockPerScanLine(int scanLineIndex, int scanLineBlockIndex);

    @Override
    public T resolveBlock(final long blockIndex) {
        return resolveBlockPerScanLine(
                (int) (blockIndex / blocksPerScanLine),
                (int) (blockIndex % blocksPerScanLine)
        );
    }

    @Override
    public long blockCount() {
        return totalBlockCount;
    }

}
