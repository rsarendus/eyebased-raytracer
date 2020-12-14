package ee.ristoseene.raytracer.eyebased.shading.transparency;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.CosineWeightedAdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.transparency.compiled.RoughSurfaceRefractiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.transparency.compiled.SimpleRefractiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class TransparentShadingConfiguration extends AbstractShadingConfiguration {

    private CompilableValueProvider<Vector3.Accessible> transparencyColor;
    private CompilableDoubleValueProvider indexOfRefraction;
    private ShadingConfiguration totalInternalReflectionShader;
    private CompilableDoubleValueProvider surfaceRoughness;
    private AdjustableHemisphericalSampler roughnessSampler;

    public CompilableValueProvider<Vector3.Accessible> getTransparencyColor() {
        return transparencyColor;
    }

    public void setTransparencyColor(final CompilableValueProvider<Vector3.Accessible> transparencyColor) {
        this.transparencyColor = transparencyColor;
    }

    public TransparentShadingConfiguration withTransparencyColor(final CompilableValueProvider<Vector3.Accessible> transparencyColor) {
        setTransparencyColor(transparencyColor);
        return this;
    }

    public CompilableDoubleValueProvider getIndexOfRefraction() {
        return indexOfRefraction;
    }

    public void setIndexOfRefraction(final CompilableDoubleValueProvider indexOfRefraction) {
        this.indexOfRefraction = indexOfRefraction;
    }

    public TransparentShadingConfiguration withIndexOfRefraction(final CompilableDoubleValueProvider indexOfRefraction) {
        setIndexOfRefraction(indexOfRefraction);
        return this;
    }

    public ShadingConfiguration getTotalInternalReflectionShader() {
        return totalInternalReflectionShader;
    }

    public void setTotalInternalReflectionShader(final ShadingConfiguration totalInternalReflectionShader) {
        this.totalInternalReflectionShader = totalInternalReflectionShader;
    }

    public TransparentShadingConfiguration withTotalInternalReflectionShader(final ShadingConfiguration totalInternalReflectionShader) {
        setTotalInternalReflectionShader(totalInternalReflectionShader);
        return this;
    }

    public CompilableDoubleValueProvider getSurfaceRoughness() {
        return surfaceRoughness;
    }

    public void setSurfaceRoughness(final CompilableDoubleValueProvider surfaceRoughness) {
        this.surfaceRoughness = surfaceRoughness;
    }

    public TransparentShadingConfiguration withSurfaceRoughness(final CompilableDoubleValueProvider surfaceRoughness) {
        setSurfaceRoughness(surfaceRoughness);
        return this;
    }

    public AdjustableHemisphericalSampler getRoughnessSampler() {
        return roughnessSampler;
    }

    public void setRoughnessSampler(final AdjustableHemisphericalSampler roughnessSampler) {
        this.roughnessSampler = roughnessSampler;
    }

    public TransparentShadingConfiguration withRoughnessSampler(final AdjustableHemisphericalSampler roughnessSampler) {
        setRoughnessSampler(roughnessSampler);
        return this;
    }

    @Override
    public TransparentShadingConfiguration clone() {
        return (TransparentShadingConfiguration) super.clone();
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(Optional<CompilationCache> compilationCache) {
        final ValueProvider<Vector3.Accessible> transparencyColorProvider = (transparencyColor == null)
                ? PROVIDER_VECTOR3_ONE_ONE_ONE
                : transparencyColor.compile(compilationCache);

        final boolean isConstantBlack = isConstantValue(transparencyColorProvider, rgb -> VecMathExtended.equal(rgb, Vectors.VECTOR3_ZERO_ZERO_ZERO));

        if (isConstantBlack && indexOfRefraction == null && surfaceRoughness == null) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final DoubleValueProvider indexOfRefractionProvider = (indexOfRefraction != null)
                ? indexOfRefraction.compile(compilationCache)
                : ConstantDoubleValueProvider.ONE_INSTANCE;

        final DoubleValueProvider surfaceRoughnessProvider = (surfaceRoughness != null)
                ? surfaceRoughness.compile(compilationCache)
                : ConstantDoubleValueProvider.ZERO_INSTANCE;

        final boolean isSharp = isConstantValue(surfaceRoughnessProvider, roughness -> roughness == 0.0);
        final boolean isNonRefractive = isSharp && isConstantValue(indexOfRefractionProvider, ior -> ior == 1.0);

        if (isConstantBlack && (isNonRefractive || totalInternalReflectionShader == null)) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        final ShadingPipeline totalInternalReflectionPipeline = (totalInternalReflectionShader != null)
                ? totalInternalReflectionShader.compile(compilationCache)
                : ConstantColorShadingPipeline.BLACK_INSTANCE;

        if (isSharp) {
            return new SimpleRefractiveShadingPipeline(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline);
        }

        final AdjustableHemisphericalSampler sampler = (roughnessSampler == null)
                ? CosineWeightedAdjustableHemisphericalSampler.INSTANCE
                : roughnessSampler;

        return new RoughSurfaceRefractiveShadingPipeline(
                transparencyColorProvider, indexOfRefractionProvider, surfaceRoughnessProvider, sampler, totalInternalReflectionPipeline
        );
    }

}
