package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface CompilableTransform extends Compilable {

    @Override
    CompiledTransform compile(Optional<CompilationCache> compilationCache);

}
