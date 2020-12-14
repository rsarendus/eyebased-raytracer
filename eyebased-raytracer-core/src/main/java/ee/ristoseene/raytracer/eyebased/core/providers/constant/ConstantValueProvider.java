package ee.ristoseene.raytracer.eyebased.core.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.StaticValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

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
