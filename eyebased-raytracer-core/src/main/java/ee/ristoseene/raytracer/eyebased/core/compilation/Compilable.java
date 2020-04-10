package ee.ristoseene.raytracer.eyebased.core.compilation;

import java.util.Optional;

public interface Compilable {

    CompiledObject compile(Optional<CompilationCache> compilationCache);

}
