package ee.ristoseene.raytracer.eyebased.core.compilation;

public interface CompilationCache {

    void put(Compilable compilable, CompiledObject compilationResult);

    CompiledObject get(Compilable compilable);

}
