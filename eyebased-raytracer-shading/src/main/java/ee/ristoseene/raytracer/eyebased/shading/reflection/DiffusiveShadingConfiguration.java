package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.common.HemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.CosineWeightedHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.DiffusiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

public class DiffusiveShadingConfiguration extends AbstractShadingConfiguration {

    private CompilableValueProvider<Vector3.Accessible> diffuseColor;
    private HemisphericalSampler diffuseDirectionSampler = CosineWeightedHemisphericalSampler.INSTANCE;
    private double intensityCompensationMultiplier = 2.0;

    public CompilableValueProvider<Vector3.Accessible> getDiffuseColor() {
        return diffuseColor;
    }

    public void setDiffuseColor(final CompilableValueProvider<Vector3.Accessible> diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public DiffusiveShadingConfiguration withDiffuseColor(final CompilableValueProvider<Vector3.Accessible> diffuseColor) {
        setDiffuseColor(diffuseColor);
        return this;
    }

    public HemisphericalSampler getDiffuseDirectionSampler() {
        return diffuseDirectionSampler;
    }

    public void setDiffuseDirectionSampler(final HemisphericalSampler diffuseDirectionSampler) {
        this.diffuseDirectionSampler = diffuseDirectionSampler;
    }

    public DiffusiveShadingConfiguration withDiffuseDirectionSampler(final HemisphericalSampler diffuseDirectionSampler) {
        setDiffuseDirectionSampler(diffuseDirectionSampler);
        return this;
    }

    public double getIntensityCompensationMultiplier() {
        return intensityCompensationMultiplier;
    }

    public void setIntensityCompensationMultiplier(final double intensityCompensationMultiplier) {
        this.intensityCompensationMultiplier = intensityCompensationMultiplier;
    }

    public DiffusiveShadingConfiguration withIntensityCompensationMultiplier(final double intensityCompensationMultiplier) {
        setIntensityCompensationMultiplier(intensityCompensationMultiplier);
        return this;
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        Objects.requireNonNull(diffuseDirectionSampler, "Diffuse direction sampler not provided");
        return super.compile(compilationCache);
    }

    @Override
    protected ShadingPipeline createCompiledPipeline(final Optional<CompilationCache> compilationCache) {
        final ValueProvider<Vector3.Accessible> diffuseColorProvider = (diffuseColor == null)
                ? PROVIDER_VECTOR3_ONE_ONE_ONE
                : diffuseColor.compile(compilationCache);

        if (isConstantValue(diffuseColorProvider, rgb -> VecMathExtended.equal(rgb, Vectors.VECTOR3_ZERO_ZERO_ZERO)) || intensityCompensationMultiplier == 0.0) {
            return ConstantColorShadingPipeline.BLACK_INSTANCE;
        }

        return new DiffusiveShadingPipeline(diffuseColorProvider, diffuseDirectionSampler, intensityCompensationMultiplier);
    }

}
