package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractRayTraceableGeometry implements CompiledGeometry, CompilableGeometry {

    protected final CompiledTransform transform;

    protected AbstractRayTraceableGeometry(final CompiledTransform transform) {
        this.transform = Objects.requireNonNull(transform, "Transform not provided");
    }

    @Override
    public CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    public CompiledTransform getTransform() {
        return transform;
    }

}
