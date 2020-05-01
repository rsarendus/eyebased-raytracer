package ee.ristoseene.raytracer.eyebased.geometry;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface CompilableGeometry extends Compilable {

    @Override
    CompiledGeometry compile(Optional<CompilationCache> compilationCache);

}
