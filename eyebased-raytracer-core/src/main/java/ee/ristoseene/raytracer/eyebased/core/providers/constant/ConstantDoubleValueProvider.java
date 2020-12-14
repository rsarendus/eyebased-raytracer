package ee.ristoseene.raytracer.eyebased.core.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.StaticDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

import java.util.Optional;

public final class ConstantDoubleValueProvider implements CompilableDoubleValueProvider, StaticDoubleValueProvider {

    public static final ConstantDoubleValueProvider NaN_INSTANCE = new ConstantDoubleValueProvider(Double.NaN);
    public static final ConstantDoubleValueProvider ZERO_INSTANCE = new ConstantDoubleValueProvider(0.0);
    public static final ConstantDoubleValueProvider ONE_INSTANCE = new ConstantDoubleValueProvider(1.0);

    private final double value;

    public ConstantDoubleValueProvider(final double value) {
        this.value = value;
    }

    @Override
    public DoubleValueProvider compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    @Override
    public double getDoubleValue(final ShadingContext shadingContext) {
        return value;
    }

    @Override
    public double getStaticDoubleValue() {
        return value;
    }

}
