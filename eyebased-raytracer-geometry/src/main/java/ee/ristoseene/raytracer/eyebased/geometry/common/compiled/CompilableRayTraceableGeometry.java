package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;

import java.util.Optional;

public interface CompilableRayTraceableGeometry extends CompiledGeometry, CompilableGeometry {

    @Override
    default CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
