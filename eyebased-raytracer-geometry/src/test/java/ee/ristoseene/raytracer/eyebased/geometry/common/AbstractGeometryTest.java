package ee.ristoseene.raytracer.eyebased.geometry.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.transformation.TransformableTest;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractGeometryTest extends TransformableTest {

    @Override
    protected abstract AbstractGeometry createDefaultInstance();

    @Test
    public void compileShouldReturnNewGeometryIfCompilationCacheNotPresent() {
        AbstractGeometry geometry = createDefaultInstance();

        Assertions.assertNotNull(geometry.compile(Optional.empty()));
    }

    @Test
    public void compileShouldReturnNewGeometryIfNotYetCached() {
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        AbstractGeometry geometry = createDefaultInstance();

        CompiledGeometry compilationResult = geometry.compile(Optional.of(compilationCache));
        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnCachedGeometryIfAlreadyInCache() {
        CompiledGeometry cachedGeometry = Mockito.mock(CompiledGeometry.class);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        AbstractGeometry geometry = createDefaultInstance();
        Mockito.doReturn(cachedGeometry).when(compilationCache).get(geometry);

        CompiledGeometry compilationResult = geometry.compile(Optional.of(compilationCache));
        Assertions.assertSame(cachedGeometry, compilationResult);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T cloneShouldCreateValidCopyOfItself(T original) {
        AbstractGeometry originalGeometry = (AbstractGeometry) original;

        AbstractGeometry clonedGeometry = super.cloneShouldCreateValidCopyOfItself(originalGeometry);

        Assertions.assertNotSame(originalGeometry, clonedGeometry);

        return (T) clonedGeometry;
    }

}
