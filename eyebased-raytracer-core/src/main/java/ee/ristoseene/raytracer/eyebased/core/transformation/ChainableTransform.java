package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;

import java.util.Optional;

public abstract class ChainableTransform extends Transformable implements CompilableTransform {

    @Override
    public ChainableTransform withParentTransform(final CompilableTransform parentTransform) {
        return (ChainableTransform) super.withParentTransform(parentTransform);
    }

    @Override
    public ChainableTransform clone() {
        return (ChainableTransform) super.clone();
    }

    @Override
    public CompiledTransform compile(final Optional<CompilationCache> compilationCache) {
        return CompilationUtils.getCachedOrCompileAndCache(
                compilationCache,
                this,
                CompiledTransform.class,
                () -> createCompiledTransform(getCompiledParentTransform(compilationCache))
        );
    }

    protected abstract CompiledTransform createCompiledTransform(CompiledTransform parent);

}
