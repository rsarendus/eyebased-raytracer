package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

import java.util.Map;

public class ShadingContextImpl implements ShadingContext {

    private final GeometryContext geometryContext;
    private final RayIntersectionContext rayIntersectionContext;
    private final Map<TypedAttribute, Object> configuration;

    public ShadingContextImpl(
            final GeometryContext geometryContext,
            final RayIntersectionContext rayIntersectionContext,
            final Map<TypedAttribute, Object> configuration
    ) {
        this.geometryContext = geometryContext;
        this.rayIntersectionContext = rayIntersectionContext;
        this.configuration = configuration;
    }

    @Override
    public GeometryContext getGeometryContext() {
        return geometryContext;
    }

    @Override
    public RayIntersectionContext getRayIntersectionContext() {
        return rayIntersectionContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttributeValue(final TypedAttribute<T> key) {
        return (T) configuration.getOrDefault(key, key.getDefaultValue());
    }

}
