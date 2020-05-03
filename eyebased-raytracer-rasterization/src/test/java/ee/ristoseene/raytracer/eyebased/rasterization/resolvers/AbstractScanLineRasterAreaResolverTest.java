package ee.ristoseene.raytracer.eyebased.rasterization.resolvers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;

public abstract class AbstractScanLineRasterAreaResolverTest extends AbstractScanLineRasterBlockResolverTest<RasterArea> {

    @Override
    protected abstract AbstractScanLineRasterAreaResolver createInstanceWith(int numberOfScanLines, int blocksPerScanLine);

}
