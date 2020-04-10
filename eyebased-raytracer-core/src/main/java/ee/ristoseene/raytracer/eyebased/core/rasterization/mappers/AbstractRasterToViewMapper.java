package ee.ristoseene.raytracer.eyebased.core.rasterization.mappers;

import ee.ristoseene.raytracer.eyebased.core.rasterization.RasterToViewMapper;

abstract class AbstractRasterToViewMapper implements RasterToViewMapper {

    protected final double ratio;

    protected AbstractRasterToViewMapper(final int extent) {
        if (extent < 1) {
            throw new IllegalArgumentException("Invalid extent: " + extent);
        }

        ratio = 2.0 / extent;
    }

}
