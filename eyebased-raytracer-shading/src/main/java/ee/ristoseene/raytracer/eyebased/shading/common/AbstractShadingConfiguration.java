package ee.ristoseene.raytracer.eyebased.shading.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public abstract class AbstractShadingConfiguration implements ShadingConfiguration, Cloneable {

    protected static final ConstantValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_ONE_ONE_ONE = new ConstantValueProvider<>(Vectors.VECTOR3_ONE_ONE_ONE);

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
