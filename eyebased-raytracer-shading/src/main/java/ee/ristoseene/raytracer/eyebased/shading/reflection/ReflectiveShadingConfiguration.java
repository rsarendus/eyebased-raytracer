package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.CosineWeightedAdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.RoughSurfaceReflectiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.SimpleReflectiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class ReflectiveShadingConfiguration extends AbstractShadingConfiguration {

    private CompilableValueProvider<Vector3.Accessible> reflectionColor;
    private CompilableDoubleValueProvider surfaceRoughness;
    private AdjustableHemisphericalSampler roughnessSampler;

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

    public CompilableDoubleValueProvider getSurfaceRoughness() {
        return surfaceRoughness;
    }

    public void setSurfaceRoughness(final CompilableDoubleValueProvider surfaceRoughness) {
        this.surfaceRoughness = surfaceRoughness;
    }

    public ReflectiveShadingConfiguration withSurfaceRoughness(final CompilableDoubleValueProvider surfaceRoughness) {
        setSurfaceRoughness(surfaceRoughness);
        return this;
    }

    public AdjustableHemisphericalSampler getRoughnessSampler() {
        return roughnessSampler;
    }

    public void setRoughnessSampler(final AdjustableHemisphericalSampler roughnessSampler) {
        this.roughnessSampler = roughnessSampler;
    }

    public ReflectiveShadingConfiguration withRoughnessSampler(final AdjustableHemisphericalSampler roughnessSampler) {
        setRoughnessSampler(roughnessSampler);
        return this;
    }

    @Override
    public ReflectiveShadingConfiguration clone() {
        return (ReflectiveShadingConfiguration) super.clone();
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(final Optional<CompilationCache> compilationCache) {
        final ValueProvider<Vector3.Accessible> reflectionColorProvider = (reflectionColor == null)
                ? PROVIDER_VECTOR3_ONE_ONE_ONE
                : reflectionColor.compile(compilationCache);

        if (isConstantValue(reflectionColorProvider, rgb -> VecMathExtended.equal(rgb, Vectors.VECTOR3_ZERO_ZERO_ZERO))) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final DoubleValueProvider surfaceRoughnessProvider = (surfaceRoughness != null)
                ? surfaceRoughness.compile(compilationCache)
                : ConstantDoubleValueProvider.ZERO_INSTANCE;

        if (isConstantValue(surfaceRoughnessProvider, roughness -> roughness == 0.0)) {
            return new SimpleReflectiveShadingPipeline(reflectionColorProvider);
        }

        final AdjustableHemisphericalSampler sampler = (roughnessSampler == null)
                ? CosineWeightedAdjustableHemisphericalSampler.INSTANCE
                : roughnessSampler;

        return new RoughSurfaceReflectiveShadingPipeline(reflectionColorProvider, surfaceRoughnessProvider, sampler);
    }

}
