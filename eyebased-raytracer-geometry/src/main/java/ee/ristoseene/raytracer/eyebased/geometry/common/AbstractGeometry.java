package ee.ristoseene.raytracer.eyebased.geometry.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.Transformable;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.AbstractRayTraceableGeometry;

import java.util.Optional;

public abstract class AbstractGeometry extends Transformable implements CompilableGeometry {

    private ShadingConfiguration shadingConfiguration;

    public ShadingConfiguration getShadingConfiguration() {
        return shadingConfiguration;
    }

    public void setShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        this.shadingConfiguration = shadingConfiguration;
    }

    public AbstractGeometry withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        setShadingConfiguration(shadingConfiguration);
        return this;
    }

    @Override
    public AbstractGeometry withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractGeometry) super.withParentTransform(parentTransform);
    }

    @Override
    public CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        return CompilationUtils.getCachedOrCompileAndCache(
                compilationCache,
                this,
                CompiledGeometry.class,
                () -> createCompiledGeometry(compilationCache)
        );
    }

    protected abstract CompiledGeometry createCompiledGeometry(final Optional<CompilationCache> compilationCache);

    protected AbstractRayTraceableGeometry.Configuration createConfiguration(final Optional<CompilationCache> compilationCache) {
        return new AbstractRayTraceableGeometry.Configuration()
                .withShadingPipeline(getCompiledShadingPipeline(compilationCache))
                .withTransform(getCompiledParentTransform(compilationCache));
    }

    protected ShadingPipeline getCompiledShadingPipeline(final Optional<CompilationCache> compilationCache) {
        if (getShadingConfiguration() != null) {
            return getShadingConfiguration().compile(compilationCache);
        } else {
            return ShadingPipeline.NO_OP_INSTANCE;
        }
    }

}
