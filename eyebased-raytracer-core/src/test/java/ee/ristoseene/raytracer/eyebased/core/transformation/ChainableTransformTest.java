package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class ChainableTransformTest extends TransformableTest {

    @Override
    protected abstract ChainableTransform createDefaultInstance();

    @Test
    public void compileShouldReturnNewCompiledTransformIfCompilationCacheNotPresent() {
        ChainableTransform chainableTransform = createDefaultInstance();
        Assertions.assertNotNull(chainableTransform.compile(Optional.empty()));
    }

    @Test
    public void compileShouldReturnNewCompiledTransformIfNotYetCached() {
        ChainableTransform chainableTransform = createDefaultInstance();
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        CompiledTransform compilationResult = chainableTransform.compile(Optional.of(compilationCache));
        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnCachedCompiledTransformIfAlreadyInCache() {
        ChainableTransform chainableTransform = createDefaultInstance();
        CompiledTransform cachedTransform = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        Mockito.doReturn(cachedTransform).when(compilationCache).get(chainableTransform);

        CompiledTransform compilationResult = chainableTransform.compile(Optional.of(compilationCache));
        Assertions.assertSame(cachedTransform, compilationResult);
    }

    @Test
    public void compileShouldReturnIdentityTransformIfNoParentNorAnythingElseSet() {
        ChainableTransform transform = createDefaultInstance();

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        Assertions.assertSame(CompiledTransform.IDENTITY_TRANSFORM, compilationResult);
    }

    @Test
    public void compileShouldReturnParentTransformIfOnlyParentSet() {
        CompiledTransform parentCompilationResult = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        CompilableTransform parentTransform = createMockCompilableTransform(parentCompilationResult);
        ChainableTransform transform = createDefaultInstance()
                .withParentTransform(parentTransform);

        CompiledTransform compilationResult = transform.compile(Optional.empty());

        VecMathAssertions.assertEquals(parentCompilationResult, compilationResult, 0.000001);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T cloneShouldCreateValidCopyOfItself(T original) {
        ChainableTransform originalTransform = (ChainableTransform) original;

        ChainableTransform clonedTransform = super.cloneShouldCreateValidCopyOfItself(originalTransform);

        Assertions.assertNotSame(originalTransform, clonedTransform);

        return (T) clonedTransform;
    }

}
