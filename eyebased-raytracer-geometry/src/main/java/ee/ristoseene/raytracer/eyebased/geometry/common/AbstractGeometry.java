package ee.ristoseene.raytracer.eyebased.geometry.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.Transformable;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;

import java.util.Optional;

public abstract class AbstractGeometry extends Transformable implements CompilableGeometry {

    @Override
    public AbstractGeometry withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractGeometry) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractGeometry clone() {
        return (AbstractGeometry) super.clone();
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

}
