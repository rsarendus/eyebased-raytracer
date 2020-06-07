package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRefractiveShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    protected final ValueProvider<Vector3.Accessible> transparencyColorProvider;
    protected final DoubleValueProvider indexOfRefractionProvider;
    protected final ShadingPipeline totalInternalReflectionPipeline;

    protected AbstractRefractiveShadingPipeline(
            final ValueProvider<Vector3.Accessible> transparencyColor,
            final DoubleValueProvider indexOfRefraction,
            final ShadingPipeline totalInternalReflectionPipeline
    ) {
        this.transparencyColorProvider = Objects.requireNonNull(transparencyColor, "Transparency color not provided");
        this.indexOfRefractionProvider = Objects.requireNonNull(indexOfRefraction, "Index of refraction not provided");
        this.totalInternalReflectionPipeline = Objects.requireNonNull(totalInternalReflectionPipeline, "Total internal reflection pipeline not provided");
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
