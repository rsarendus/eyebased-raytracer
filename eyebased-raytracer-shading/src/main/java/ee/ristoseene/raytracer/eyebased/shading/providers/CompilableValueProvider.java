package ee.ristoseene.raytracer.eyebased.shading.providers;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface CompilableValueProvider<T> extends Compilable {

    ValueProvider<T> compile(Optional<CompilationCache> compilationCache);

}
