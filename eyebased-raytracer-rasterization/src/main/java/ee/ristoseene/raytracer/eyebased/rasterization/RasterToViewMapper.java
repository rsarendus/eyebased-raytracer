package ee.ristoseene.raytracer.eyebased.rasterization;

@FunctionalInterface
public interface RasterToViewMapper {

    double map(double rasterCoordinate);

}
