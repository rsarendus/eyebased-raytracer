package ee.ristoseene.raytracer.eyebased.core.compilation.cache;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class MapBasedCompilationCacheTest {

    @Mock
    private Map<Compilable, CompiledObject> backingMap;

    @Mock
    private Compilable mockCompilableObject;

    @Mock
    private CompiledObject mockCompiledObject;


    @Test
    public void putShouldDelegateMethodCallToBackingMap() {
        new MapBasedCompilationCache(backingMap).put(mockCompilableObject, mockCompiledObject);

        Mockito.verifyZeroInteractions(mockCompilableObject, mockCompiledObject);
        Mockito.verify(backingMap, Mockito.times(1)).put(mockCompilableObject, mockCompiledObject);
        Mockito.verifyNoMoreInteractions(backingMap);
    }

    @Test
    public void getShouldDelegateMethodCallToBackingMap() {
        Mockito.doReturn(mockCompiledObject).when(backingMap).get(mockCompilableObject);

        CompiledObject returnedObject = new MapBasedCompilationCache(backingMap).get(mockCompilableObject);

        Assertions.assertSame(mockCompiledObject, returnedObject);

        Mockito.verifyZeroInteractions(mockCompilableObject, mockCompiledObject);
        Mockito.verify(backingMap, Mockito.times(1)).get(mockCompilableObject);
        Mockito.verifyNoMoreInteractions(backingMap);
    }

    @Test
    public void constructorShouldFailWhenBackingMapIsNull() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new MapBasedCompilationCache(null)
        );

        Assertions.assertEquals(
                "Map cannot be null",
                exception.getMessage()
        );
    }

}