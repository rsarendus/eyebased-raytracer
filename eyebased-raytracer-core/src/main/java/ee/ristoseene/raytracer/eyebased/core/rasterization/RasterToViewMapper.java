package ee.ristoseene.raytracer.eyebased.core.rasterization;

@FunctionalInterface
public interface RasterToViewMapper {

    double map(double rasterCoordinate);

}
