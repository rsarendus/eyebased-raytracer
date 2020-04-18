package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;

import java.util.Optional;

@FunctionalInterface
public interface ShadingConfiguration extends Compilable {

    ShadingPipeline compile(Optional<CompilationCache> compilationCache);

}
