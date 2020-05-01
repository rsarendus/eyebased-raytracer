package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface ShadingContext {

    GeometryContext getGeometryContext();
    RayIntersectionContext getRayIntersectionContext();

    default <T> T getAttributeValue(final TypedAttribute<T> key) {
        return key.getDefaultValue();
    }

}
