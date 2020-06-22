package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractUniAggregatingShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    protected final ShadingPipeline shadingPipeline;

    protected AbstractUniAggregatingShadingPipeline(final ShadingPipeline shadingPipeline) {
        this.shadingPipeline = Objects.requireNonNull(shadingPipeline, "Shading pipeline not provided");
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
