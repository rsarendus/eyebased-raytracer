package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.CosineWeightedAdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.VectorProviders;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.BlurryReflectiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.SharpReflectiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class ReflectiveShadingConfiguration extends AbstractShadingConfiguration {

    private CompilableValueProvider<Vector3.Accessible> reflectionColor;
    private CompilableDoubleValueProvider reflectionBlurriness;
    private AdjustableHemisphericalSampler blurSampler;

    public CompilableValueProvider<Vector3.Accessible> getReflectionColor() {
        return reflectionColor;
    }

    public void setReflectionColor(final CompilableValueProvider<Vector3.Accessible> reflectionColor) {
        this.reflectionColor = reflectionColor;
    }

    public ReflectiveShadingConfiguration withReflectionColor(final CompilableValueProvider<Vector3.Accessible> reflectionColor) {
        setReflectionColor(reflectionColor);
        return this;
    }

    public CompilableDoubleValueProvider getReflectionBlurriness() {
        return reflectionBlurriness;
    }

    public void setReflectionBlurriness(final CompilableDoubleValueProvider reflectionBlurriness) {
        this.reflectionBlurriness = reflectionBlurriness;
    }

    public ReflectiveShadingConfiguration withReflectionBlurriness(final CompilableDoubleValueProvider reflectionBlurriness) {
        setReflectionBlurriness(reflectionBlurriness);
        return this;
    }

    public AdjustableHemisphericalSampler getBlurSampler() {
        return blurSampler;
    }

    public void setBlurSampler(final AdjustableHemisphericalSampler blurSampler) {
        this.blurSampler = blurSampler;
    }

    public ReflectiveShadingConfiguration withBlurSampler(final AdjustableHemisphericalSampler blurSampler) {
        setBlurSampler(blurSampler);
        return this;
    }

    @Override
    public ReflectiveShadingConfiguration clone() {
        return (ReflectiveShadingConfiguration) super.clone();
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(final Optional<CompilationCache> compilationCache) {
        final ValueProvider<Vector3.Accessible> reflectionColorProvider = (reflectionColor == null)
                ? VectorProviders.PROVIDER_VECTOR3_ONE_ONE_ONE
                : reflectionColor.compile(compilationCache);

        if (isConstantValue(reflectionColorProvider, rgb -> VecMathExtended.equal(rgb, Vectors.VECTOR3_ZERO_ZERO_ZERO))) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final DoubleValueProvider reflectionBlurrinessProvider = (reflectionBlurriness != null)
                ? reflectionBlurriness.compile(compilationCache)
                : ConstantDoubleValueProvider.ZERO_INSTANCE;

        if (isConstantValue(reflectionBlurrinessProvider, blurriness -> blurriness == 0.0)) {
            return new SharpReflectiveShadingPipeline(reflectionColorProvider);
        }

        final AdjustableHemisphericalSampler hemisphericalSampler = (blurSampler == null)
                ? new CosineWeightedAdjustableHemisphericalSampler()
                : blurSampler;

        return new BlurryReflectiveShadingPipeline(reflectionColorProvider, reflectionBlurrinessProvider, hemisphericalSampler);
    }

}
