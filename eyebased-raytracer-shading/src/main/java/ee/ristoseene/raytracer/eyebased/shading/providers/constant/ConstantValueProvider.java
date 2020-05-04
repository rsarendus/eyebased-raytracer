package ee.ristoseene.raytracer.eyebased.shading.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.StaticValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;

import java.util.Optional;

public final class ConstantValueProvider<T> implements StaticValueProvider<T>, CompilableValueProvider<T> {

    private final T value;

    public ConstantValueProvider(final T value) {
        this.value = value;
    }

    @Override
    public ValueProvider<T> compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    @Override
    public T getValue(ShadingContext shadingContext) {
        return value;
    }

    @Override
    public T getStaticValue() {
        return value;
    }

}
