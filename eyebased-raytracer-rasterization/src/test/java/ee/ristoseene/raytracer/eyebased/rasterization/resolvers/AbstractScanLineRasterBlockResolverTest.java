package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public abstract class AbstractScanLineRasterBlockResolverTest<T> {

    protected abstract AbstractScanLineRasterBlockResolver<T> createInstanceWith(int numberOfScanLines, int blocksPerScanLine);

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 10})
    public void blockCountShouldDeriveFromNumberOfScanLinesAndBlocksPerScanLine(final int size) {
        AbstractScanLineRasterBlockResolver<T> rasterBlockResolver = createInstanceWith(size, size);

        Assertions.assertEquals(size * size, rasterBlockResolver.blockCount());
    }

    @Test
    public void blockCountShouldDeriveFromNumberOfScanLinesAndBlocksPerScanLine() {
        AbstractScanLineRasterBlockResolver<T> rasterBlockResolver = createInstanceWith(3, 7);

        Assertions.assertEquals(21, rasterBlockResolver.blockCount());
    }

}
