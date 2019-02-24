package ee.ristoseene.raytracer.eyebased.core.compilation;

public interface Compilable {

    CompiledObject compile(CompilationCache compilationCache);

    CompiledObject compile();

}
