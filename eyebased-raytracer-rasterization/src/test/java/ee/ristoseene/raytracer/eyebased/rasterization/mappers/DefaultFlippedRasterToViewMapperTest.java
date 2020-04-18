package ee.ristoseene.raytracer.eyebased.rasterization.mappers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultFlippedRasterToViewMapperTest extends AbstractRasterToViewMapperTest {

    @Test
    public void mapperShouldAllowPositiveExtent() {
        Assertions.assertTrue(createMapperWithExtent(1) instanceof DefaultFlippedRasterToViewMapper);
    }

    @Test
    public void mapperShouldMapZeroToOne() {
        Assertions.assertEquals(1.0, createMapperWithExtent(123).map(0.0));
    }

    @Test
    public void mapperShouldMapAverageToZero() {
        Assertions.assertEquals(0.0, createMapperWithExtent(123).map(123.0 / 2), 0.000001);
    }

    @Test
    public void mapperShouldMapMaximumToMinusOne() {
        Assertions.assertEquals(-1.0, createMapperWithExtent(123).map(123.0));
    }

    @Override
    protected RasterToViewMapper createMapperWithExtent(final int extent) {
        return new DefaultFlippedRasterToViewMapper(extent);
    }

}
