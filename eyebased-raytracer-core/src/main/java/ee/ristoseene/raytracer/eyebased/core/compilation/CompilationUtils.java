package ee.ristoseene.raytracer.eyebased.core.compilation;

import java.util.Optional;
import java.util.function.Supplier;

public final class CompilationUtils {

    private CompilationUtils() {}

    @SuppressWarnings("unchecked")
    public static <T extends CompiledObject> T getCachedOrCompileAndCache(
            final Optional<CompilationCache> compilationCache,
            final Compilable compilableObject,
            final Class<T> compiledObjectType,
            final Supplier<T> compilationResultProducer
    ) {
        if (compilationCache.isPresent()) {
            final CompiledObject compiledObject = compilationCache.get().get(compilableObject);

            if (compiledObjectType.isInstance(compiledObject)) {
                return (T) compiledObject;
            }
        }

        final T compilationResult = compilationResultProducer.get();

        if (compilationCache.isPresent()) {
            compilationCache.get().put(compilableObject, compilationResult);
        }

        return compilationResult;
    }

}
