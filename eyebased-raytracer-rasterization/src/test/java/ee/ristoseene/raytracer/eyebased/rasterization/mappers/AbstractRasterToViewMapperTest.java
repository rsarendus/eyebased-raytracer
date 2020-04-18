package ee.ristoseene.raytracer.eyebased.rasterization.mappers;

import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public abstract class AbstractRasterToViewMapperTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void mapperShouldNotAllowExtentLessThanOne(final int extent) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createMapperWithExtent(extent)
        );
        Assertions.assertEquals("Invalid extent: " + extent, exception.getMessage());
    }

    protected abstract RasterToViewMapper createMapperWithExtent(int extent);

}
