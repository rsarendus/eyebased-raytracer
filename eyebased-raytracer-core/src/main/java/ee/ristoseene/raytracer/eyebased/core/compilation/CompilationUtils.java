package ee.ristoseene.raytracer.eyebased.core.compilation;

import java.util.function.Supplier;

public final class CompilationUtils {

    private CompilationUtils() {}

    @SuppressWarnings("unchecked")
    public static <T extends CompiledObject> T getCachedOrCompileAndCache(
            CompilationCache compilationCache,
            Compilable compilableObject,
            Class<T> compiledObjectType,
            Supplier<T> compilationProducer
    ) {
        CompiledObject compiledObject = compilationCache.get(compilableObject);

        if (compilableObject != null && compiledObjectType.isInstance(compiledObject)) {
            return (T) compiledObject;
        }

        T compilationResult = compilationProducer.get();
        compilationCache.put(compilableObject, compilationResult);

        return compilationResult;
    }

}
