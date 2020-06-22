package ee.ristoseene.raytracer.eyebased.shading.aggregation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.ConstantMultiplierShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.ConstantRatioMixingShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.DynamicMultiplierShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.DynamicRatioMixingShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;

import java.util.Objects;
import java.util.Optional;

public class MixingShadingConfiguration extends AbstractShadingConfiguration {

    private ShadingConfiguration firstShader;
    private ShadingConfiguration secondShader;
    private CompilableDoubleValueProvider ratio;

    public ShadingConfiguration getFirstShader() {
        return firstShader;
    }

    public void setFirstShader(final ShadingConfiguration firstShader) {
        this.firstShader = firstShader;
    }

    public MixingShadingConfiguration withFirstShader(final ShadingConfiguration firstShader) {
        setFirstShader(firstShader);
        return this;
    }

    public ShadingConfiguration getSecondShader() {
        return secondShader;
    }

    public void setSecondShader(final ShadingConfiguration secondShader) {
        this.secondShader = secondShader;
    }

    public MixingShadingConfiguration withSecondShader(final ShadingConfiguration secondShader) {
        setSecondShader(secondShader);
        return this;
    }

    public CompilableDoubleValueProvider getRatio() {
        return ratio;
    }

    public void setRatio(final CompilableDoubleValueProvider ratio) {
        this.ratio = ratio;
    }

    public MixingShadingConfiguration withRatio(final CompilableDoubleValueProvider ratio) {
        setRatio(ratio);
        return this;
    }

    @Override
    public MixingShadingConfiguration clone() {
        return (MixingShadingConfiguration) super.clone();
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        Objects.requireNonNull(ratio, "Ratio not provided");
        return super.compile(compilationCache);
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(final Optional<CompilationCache> compilationCache) {
        if (firstShader == null && secondShader == null) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final DoubleValueProvider compiledRatioProvider = ratio.compile(compilationCache);

        if (compiledRatioProvider instanceof ConstantDoubleValueProvider) {
            final double ratio = ((ConstantDoubleValueProvider) compiledRatioProvider).getStaticDoubleValue();

            if (ratio == 0.0) {
                if (firstShader != null) return firstShader.compile(compilationCache);
                else return ConstantColorShadingPipeline.BLACK_INSTANCE;
            } else if (ratio == 1.0) {
                if (secondShader != null) return secondShader.compile(compilationCache);
                else return ConstantColorShadingPipeline.BLACK_INSTANCE;
            } else if (firstShader == null) {
                return new ConstantMultiplierShadingPipeline(
                        secondShader.compile(compilationCache),
                        ratio
                );
            } else if (secondShader == null) {
                return new ConstantMultiplierShadingPipeline(
                        firstShader.compile(compilationCache),
                        1.0 - ratio
                );
            }

            return new ConstantRatioMixingShadingPipeline(
                    firstShader.compile(compilationCache),
                    secondShader.compile(compilationCache),
                    ratio
            );
        } else if (firstShader == null) {
            return new DynamicMultiplierShadingPipeline(
                    secondShader.compile(compilationCache),
                    compiledRatioProvider
            );
        } else if (secondShader == null) {
            return new DynamicMultiplierShadingPipeline(
                    firstShader.compile(compilationCache),
                    shadingContext -> 1.0 - compiledRatioProvider.getDoubleValue(shadingContext)
            );
        } else {
            return new DynamicRatioMixingShadingPipeline(
                    firstShader.compile(compilationCache),
                    secondShader.compile(compilationCache),
                    compiledRatioProvider
            );
        }
    }

}
