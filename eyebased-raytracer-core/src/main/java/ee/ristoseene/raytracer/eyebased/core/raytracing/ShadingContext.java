package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface ShadingContext {

    GeometryContext getGeometryContext();
    RayIntersectionContext getRayIntersectionContext();

    default <T> T getAttributeValue(final Attribute<T> attribute) {
        return attribute.getDefaultValue();
    }

    interface Attribute<T> {

        Class<T> getValueType();
        T getDefaultValue();

    }

}
