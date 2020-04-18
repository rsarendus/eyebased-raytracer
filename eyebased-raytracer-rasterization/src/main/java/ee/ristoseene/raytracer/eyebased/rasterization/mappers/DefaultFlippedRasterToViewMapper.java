package ee.ristoseene.raytracer.eyebased.rasterization.mappers;

public final class DefaultFlippedRasterToViewMapper extends AbstractRasterToViewMapper {

    public DefaultFlippedRasterToViewMapper(final int extent) {
        super(extent);
    }

    @Override
    public double map(final double rasterCoordinate) {
        return 1.0 - rasterCoordinate * ratio;
    }

}
