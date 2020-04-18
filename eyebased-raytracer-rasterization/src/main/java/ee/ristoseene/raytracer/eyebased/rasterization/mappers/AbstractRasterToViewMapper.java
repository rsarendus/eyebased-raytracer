package ee.ristoseene.raytracer.eyebased.rasterization.mappers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;

abstract class AbstractRasterToViewMapper implements RasterToViewMapper {

    protected final double ratio;

    protected AbstractRasterToViewMapper(final int extent) {
        if (extent < 1) {
            throw new IllegalArgumentException("Invalid extent: " + extent);
        }

        ratio = 2.0 / extent;
    }

}
