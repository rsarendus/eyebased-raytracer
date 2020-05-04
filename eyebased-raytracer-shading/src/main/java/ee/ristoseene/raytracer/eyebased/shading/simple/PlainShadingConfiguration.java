package ee.ristoseene.raytracer.eyebased.shading.simple;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.VectorProviders;
import ee.ristoseene.raytracer.eyebased.shading.simple.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.simple.compiled.DynamicColorShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class PlainShadingConfiguration extends AbstractShadingConfiguration {
    
    private CompilableValueProvider<Vector3.Accessible> color;
    private CompilableDoubleValueProvider alpha;

    public CompilableValueProvider<Vector3.Accessible> getColor() {
        return color;
    }

    public void setColor(final CompilableValueProvider<Vector3.Accessible> color) {
        this.color = color;
    }

    public PlainShadingConfiguration withColor(final CompilableValueProvider<Vector3.Accessible> color) {
        setColor(color);
        return this;
    }

    public CompilableDoubleValueProvider getAlpha() {
        return alpha;
    }

    public void setAlpha(final CompilableDoubleValueProvider alpha) {
        this.alpha = alpha;
    }

    public PlainShadingConfiguration withAlpha(final CompilableDoubleValueProvider alpha) {
        setAlpha(alpha);
        return this;
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(Optional<CompilationCache> compilationCache) {
        final ValueProvider<Vector3.Accessible> colorProvider = (color == null)
                ? VectorProviders.PROVIDER_VECTOR3_ZERO_ZERO_ZERO
                : color.compile(compilationCache);
        
        final DoubleValueProvider alphaProvider = (alpha == null)
                ? ConstantDoubleValueProvider.ONE_INSTANCE
                : alpha.compile(compilationCache);
        
        if (colorProvider instanceof ConstantValueProvider && alphaProvider instanceof ConstantDoubleValueProvider) {
            final Vector3.Accessible colorValue = ((ConstantValueProvider<Vector3.Accessible>) colorProvider).getStaticValue();
            final double alphaValue = ((ConstantDoubleValueProvider) alphaProvider).getStaticDoubleValue();

            if (VecMathExtended.equal(colorValue, Vectors.VECTOR3_ZERO_ZERO_ZERO) && alphaValue == 1.0) {
                return ConstantColorShadingPipeline.OPAQUE_BLACK_INSTANCE;
            } else {
                return new ConstantColorShadingPipeline(colorValue, alphaValue);
            }
        } else {
            return new DynamicColorShadingPipeline(colorProvider, alphaProvider);
        }
    }
    
}
