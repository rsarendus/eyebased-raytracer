package ee.ristoseene.raytracer.eyebased.shading.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;

import java.util.Optional;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public abstract class AbstractShadingConfiguration implements ShadingConfiguration, Cloneable {

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return CompilationUtils.getCachedOrCompileAndCache(
                compilationCache,
                this,
                ShadingPipeline.class,
                () -> createCompiledPipeline(compilationCache)
        );
    }

    protected abstract ShadingPipeline createCompiledPipeline(Optional<CompilationCache> compilationCache);

    protected static <T> boolean isConstantValue(final ValueProvider<T> valueProvider, final Predicate<T> test) {
        if (valueProvider instanceof ConstantValueProvider) {
            return test.test(((ConstantValueProvider<T>) valueProvider).getStaticValue());
        } else {
            return false;
        }
    }

    protected static boolean isConstantValue(final DoubleValueProvider valueProvider, final DoublePredicate test) {
        if (valueProvider instanceof ConstantDoubleValueProvider) {
            return test.test(((ConstantDoubleValueProvider) valueProvider).getStaticDoubleValue());
        } else {
            return false;
        }
    }

    @Override
    public AbstractShadingConfiguration clone() {
        try {
            return (AbstractShadingConfiguration) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
