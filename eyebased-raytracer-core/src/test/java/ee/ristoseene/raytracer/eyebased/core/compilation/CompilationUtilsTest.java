package ee.ristoseene.raytracer.eyebased.core.compilation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.function.Supplier;

@ExtendWith(MockitoExtension.class)
public class CompilationUtilsTest {

    @Mock
    private CompilationCache compilationCache;

    @Mock
    private Compilable mockCompilableObject;


    @Test
    public void getCachedOrCompileAndCacheShouldReturnCachedObjectWhenExactTypeExistsInCache() {
        CompiledObject cachedCompiledObject = mockCompiledObject(CompiledObject.class);
        Supplier<CompiledObject> compilationProducer = mockCompilationProducer(null);
        Mockito.doReturn(cachedCompiledObject).when(compilationCache).get(mockCompilableObject);

        CompiledObject returnedObject = CompilationUtils.getCachedOrCompileAndCache(
                Optional.of(compilationCache), mockCompilableObject, CompiledObject.class, compilationProducer
        );

        Assertions.assertSame(cachedCompiledObject, returnedObject);

        Mockito.verify(compilationCache, Mockito.times(1)).get(mockCompilableObject);
        Mockito.verifyNoMoreInteractions(compilationCache);

        Mockito.verifyNoInteractions(mockCompilableObject, cachedCompiledObject, compilationProducer);
    }

    @Test
    public void getCachedOrCompileAndCacheShouldReturnCachedObjectWhenSubTypeExistsInCache() {
        SubclassOfCompiledObject cachedCompiledObject = mockCompiledObject(SubclassOfCompiledObject.class);
        Supplier<CompiledObject> compilationProducer = mockCompilationProducer(null);
        Mockito.doReturn(cachedCompiledObject).when(compilationCache).get(mockCompilableObject);

        CompiledObject returnedObject = CompilationUtils.getCachedOrCompileAndCache(
                Optional.of(compilationCache), mockCompilableObject, CompiledObject.class, compilationProducer
        );

        Assertions.assertSame(cachedCompiledObject, returnedObject);

        Mockito.verify(compilationCache, Mockito.times(1)).get(mockCompilableObject);
        Mockito.verifyNoMoreInteractions(compilationCache);

        Mockito.verifyNoInteractions(mockCompilableObject, cachedCompiledObject, compilationProducer);
    }

    @Test
    public void getCachedOrCompileAndCacheShouldFetchNewObjectFromProducerAndCacheItWhenNoneFoundInCache() {
        CompiledObject producedCompiledObject = mockCompiledObject(CompiledObject.class);
        Supplier<CompiledObject> compilationProducer = mockCompilationProducer(producedCompiledObject);
        Mockito.doReturn(null).when(compilationCache).get(mockCompilableObject);

        CompiledObject returnedObject = CompilationUtils.getCachedOrCompileAndCache(
                Optional.of(compilationCache), mockCompilableObject, CompiledObject.class, compilationProducer
        );

        Assertions.assertSame(producedCompiledObject, returnedObject);

        Mockito.verify(compilationCache, Mockito.times(1)).get(mockCompilableObject);
        Mockito.verify(compilationCache, Mockito.times(1)).put(mockCompilableObject, producedCompiledObject);
        Mockito.verifyNoMoreInteractions(compilationCache);

        Mockito.verify(compilationProducer, Mockito.times(1)).get();
        Mockito.verifyNoMoreInteractions(compilationProducer);

        Mockito.verifyNoInteractions(mockCompilableObject, producedCompiledObject);
    }

    @Test
    public void getCachedOrCompileAndCacheShouldFetchNewObjectFromProducerAndCacheItWhenIncompatibleTypeFoundInCache() {
        CompiledObject cachedCompiledObject = mockCompiledObject(CompiledObject.class);
        Mockito.doReturn(cachedCompiledObject).when(compilationCache).get(mockCompilableObject);

        SubclassOfCompiledObject producedCompiledObject = mockCompiledObject(SubclassOfCompiledObject.class);
        Supplier<SubclassOfCompiledObject> compilationProducer = mockCompilationProducer(producedCompiledObject);

        SubclassOfCompiledObject returnedObject = CompilationUtils.getCachedOrCompileAndCache(
                Optional.of(compilationCache), mockCompilableObject, SubclassOfCompiledObject.class, compilationProducer
        );

        Assertions.assertSame(producedCompiledObject, returnedObject);

        Mockito.verify(compilationCache, Mockito.times(1)).get(mockCompilableObject);
        Mockito.verify(compilationCache, Mockito.times(1)).put(mockCompilableObject, producedCompiledObject);
        Mockito.verifyNoMoreInteractions(compilationCache);

        Mockito.verify(compilationProducer, Mockito.times(1)).get();
        Mockito.verifyNoMoreInteractions(compilationProducer);

        Mockito.verifyNoInteractions(mockCompilableObject, cachedCompiledObject, producedCompiledObject);
    }

    @Test
    public void getCachedOrCompileAndCacheShouldFetchNewObjectFromProducerWhenNoCacheProvided() {
        CompiledObject producedCompiledObject = mockCompiledObject(CompiledObject.class);
        Supplier<CompiledObject> compilationProducer = mockCompilationProducer(producedCompiledObject);

        CompiledObject returnedObject = CompilationUtils.getCachedOrCompileAndCache(
                Optional.empty(), mockCompilableObject, CompiledObject.class, compilationProducer
        );

        Assertions.assertSame(producedCompiledObject, returnedObject);

        Mockito.verify(compilationProducer, Mockito.times(1)).get();
        Mockito.verifyNoMoreInteractions(compilationProducer);

        Mockito.verifyNoInteractions(compilationCache, mockCompilableObject, producedCompiledObject);
    }


    @SuppressWarnings("unchecked")
    private <T extends CompiledObject> Supplier<T> mockCompilationProducer(T returnableObject) {
        Supplier<T> compilationProducer = Mockito.mock(Supplier.class);
        if (returnableObject != null) {
            Mockito.doReturn(returnableObject).when(compilationProducer).get();
        }
        return compilationProducer;
    }

    private <T extends CompiledObject> T mockCompiledObject(Class<T> type) {
        return Mockito.mock(type);
    }


    interface SubclassOfCompiledObject extends CompiledObject {
    }

}