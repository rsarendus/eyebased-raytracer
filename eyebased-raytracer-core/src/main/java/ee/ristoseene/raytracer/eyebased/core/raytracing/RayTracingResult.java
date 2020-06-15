package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface RayTracingResult {

    RayTraceable getSourceRayTraceable();

    default <T> T getAttributeValue(final TypedAttribute<T> key) {
        return key.getDefaultValue();
    }

}
