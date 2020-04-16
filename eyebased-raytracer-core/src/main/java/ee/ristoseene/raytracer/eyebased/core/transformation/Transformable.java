package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

public abstract class Transformable {

    private CompilableTransform parentTransform;

    public CompilableTransform getParentTransform() {
        return parentTransform;
    }

    public void setParentTransform(final CompilableTransform parentTransform) {
        this.parentTransform = parentTransform;
    }

    public Transformable withParentTransform(final CompilableTransform parentTransform) {
        setParentTransform(parentTransform);
        return this;
    }

    protected CompiledTransform getCompiledParentTransform(final Optional<CompilationCache> compilationCache) {
        if (parentTransform != null) {
            return parentTransform.compile(compilationCache);
        } else {
            return CompiledTransform.IDENTITY_TRANSFORM;
        }
    }

}
