package ee.ristoseene.raytracer.eyebased.core.providers;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface CompilableDoubleValueProvider extends Compilable {

    DoubleValueProvider compile(Optional<CompilationCache> compilationCache);

}
