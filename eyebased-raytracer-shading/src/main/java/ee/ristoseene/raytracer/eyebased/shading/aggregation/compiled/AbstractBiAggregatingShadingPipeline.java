package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractBiAggregatingShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    protected final ShadingPipeline shadingPipeline1;
    protected final ShadingPipeline shadingPipeline2;

    protected AbstractBiAggregatingShadingPipeline(
            final ShadingPipeline shadingPipeline1,
            final ShadingPipeline shadingPipeline2
    ) {
        this.shadingPipeline1 = Objects.requireNonNull(shadingPipeline1, "First shading pipeline not provided");
        this.shadingPipeline2 = Objects.requireNonNull(shadingPipeline2, "Second shading pipeline not provided");
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
