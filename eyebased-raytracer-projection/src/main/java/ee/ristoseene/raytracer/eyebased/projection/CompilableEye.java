package ee.ristoseene.raytracer.eyebased.projection;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface CompilableEye extends Compilable {

    @Override
    CompiledRayProducer compile(Optional<CompilationCache> compilationCache);

}
