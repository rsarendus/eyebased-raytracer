package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRayTraceableGeometry implements CompiledGeometry, CompilableGeometry {

    protected final CompiledTransform transform;
    protected final ShadingPipeline shadingPipeline;

    protected AbstractRayTraceableGeometry(final Configuration configuration) {
        Objects.requireNonNull(configuration, "Configuration not provided");

        this.transform = Objects.requireNonNull(
                configuration.getTransform(), "Transform not provided"
        );
        this.shadingPipeline = Objects.requireNonNull(
                configuration.getShadingPipeline(), "Shading pipeline not provided"
        );
    }

    @Override
    public CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    public ShadingPipeline getShadingPipeline() {
        return shadingPipeline;
    }

    public CompiledTransform getTransform() {
        return transform;
    }

    public static class Configuration {

        private CompiledTransform transform;
        private ShadingPipeline shadingPipeline;

        public CompiledTransform getTransform() {
            return transform;
        }

        public void setTransform(final CompiledTransform transform) {
            this.transform = transform;
        }

        public Configuration withTransform(final CompiledTransform transform) {
            setTransform(transform);
            return this;
        }

        public ShadingPipeline getShadingPipeline() {
            return shadingPipeline;
        }

        public void setShadingPipeline(final ShadingPipeline shadingPipeline) {
            this.shadingPipeline = shadingPipeline;
        }

        public Configuration withShadingPipeline(final ShadingPipeline shadingPipeline) {
            setShadingPipeline(shadingPipeline);
            return this;
        }

        public Configuration withConfiguration(final Configuration configuration) {
            setShadingPipeline(configuration.getShadingPipeline());
            setTransform(configuration.getTransform());
            return this;
        }

    }

}
