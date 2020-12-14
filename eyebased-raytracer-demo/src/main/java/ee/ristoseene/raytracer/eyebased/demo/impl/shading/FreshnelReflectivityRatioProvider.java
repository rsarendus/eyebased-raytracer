package ee.ristoseene.raytracer.eyebased.demo.impl.shading;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.vecmath.VecMath;

import java.util.Objects;
import java.util.Optional;

public class FreshnelReflectivityRatioProvider implements CompilableDoubleValueProvider, DoubleValueProvider {

    private final FreshnelValueProvider freshnelValueProvider;
    private final DoubleValueProvider baseReflectivityProvider;

    public FreshnelReflectivityRatioProvider(
            final FreshnelValueProvider freshnelValueProvider,
            final double baseReflectivity
    ) {
        this(freshnelValueProvider, new ConstantDoubleValueProvider(baseReflectivity));
    }

    public FreshnelReflectivityRatioProvider(
            final FreshnelValueProvider freshnelValueProvider,
            final DoubleValueProvider baseReflectivityProvider
    ) {
        this.freshnelValueProvider = Objects.requireNonNull(freshnelValueProvider);
        this.baseReflectivityProvider = Objects.requireNonNull(baseReflectivityProvider);
    }

    @Override
    public DoubleValueProvider compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    @Override
    public double getDoubleValue(final ShadingContext shadingContext) {
        return VecMath.lerp(
                1.0,
                baseReflectivityProvider.getDoubleValue(shadingContext),
                freshnelValueProvider.getDoubleValue(shadingContext)
        );
    }

}
