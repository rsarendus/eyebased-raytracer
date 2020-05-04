package ee.ristoseene.raytracer.eyebased.shading.providers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

@FunctionalInterface
public interface StaticValueProvider<T> extends ValueProvider<T> {

    @Override
    default T getValue(final ShadingContext shadingContext) {
        return getStaticValue();
    }

    T getStaticValue();

}
