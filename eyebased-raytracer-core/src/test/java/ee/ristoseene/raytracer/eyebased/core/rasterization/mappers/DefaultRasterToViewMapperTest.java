package ee.ristoseene.raytracer.eyebased.core.rasterization.mappers;

import ee.ristoseene.raytracer.eyebased.core.rasterization.RasterToViewMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultRasterToViewMapperTest extends AbstractRasterToViewMapperTest {

    @Test
    public void mapperShouldAllowPositiveExtent() {
        Assertions.assertTrue(createMapperWithExtent(1) instanceof DefaultRasterToViewMapper);
    }

    @Test
    public void mapperShouldMapZeroToMinusOne() {
        Assertions.assertEquals(-1.0, createMapperWithExtent(123).map(0.0));
    }

    @Test
    public void mapperShouldMapAverageToZero() {
        Assertions.assertEquals(0.0, createMapperWithExtent(123).map(123.0 / 2), 0.000001);
    }

    @Test
    public void mapperShouldMapMaximumToOne() {
        Assertions.assertEquals(1.0, createMapperWithExtent(123).map(123.0));
    }

    @Override
    protected RasterToViewMapper createMapperWithExtent(final int extent) {
        return new DefaultRasterToViewMapper(extent);
    }

}