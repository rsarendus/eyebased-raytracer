package ee.ristoseene.raytracer.eyebased.shading.emission;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.DynamicColorShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class EmissiveShadingConfiguration extends AbstractShadingConfiguration {
    
    private CompilableValueProvider<Vector3.Accessible> color;

    public CompilableValueProvider<Vector3.Accessible> getColor() {
        return color;
    }

    public void setColor(final CompilableValueProvider<Vector3.Accessible> color) {
        this.color = color;
    }

    public EmissiveShadingConfiguration withColor(final CompilableValueProvider<Vector3.Accessible> color) {
        setColor(color);
        return this;
    }

    @Override
    public EmissiveShadingConfiguration clone() {
        return (EmissiveShadingConfiguration) super.clone();
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(Optional<CompilationCache> compilationCache) {
        if (color == null) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final ValueProvider<Vector3.Accessible> colorProvider = color.compile(compilationCache);
        
        if (colorProvider instanceof ConstantValueProvider) {
            final Vector3.Accessible colorValue = ((ConstantValueProvider<Vector3.Accessible>) colorProvider).getStaticValue();
            return new ConstantColorShadingPipeline(colorValue);
        } else {
            return new DynamicColorShadingPipeline(colorProvider);
        }
    }
    
}
