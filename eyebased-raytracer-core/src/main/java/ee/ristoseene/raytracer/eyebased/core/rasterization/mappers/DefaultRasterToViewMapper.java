package ee.ristoseene.raytracer.eyebased.core.rasterization.mappers;

public final class DefaultRasterToViewMapper extends AbstractRasterToViewMapper {

    public DefaultRasterToViewMapper(final int extent) {
        super(extent);
    }

    @Override
    public double map(final double rasterCoordinate) {
        return rasterCoordinate * ratio - 1.0;
    }

}
