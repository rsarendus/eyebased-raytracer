package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractRayTraceableGeometryTest {

    @Test
    public void rayTraceableGeometryShouldNotAllowMissingTransform() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithTransform(null)
        );

        Assertions.assertEquals("Transform not provided", exception.getMessage());
    }

    @Test
    public void rayTraceableGeometryShouldCompileToItself() {
        AbstractRayTraceableGeometry rayTraceableGeometry = createInstanceWithTransform(CompiledTransform.IDENTITY_TRANSFORM);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        CompiledGeometry compilationResult = rayTraceableGeometry.compile(Optional.of(compilationCache));

        Assertions.assertSame(rayTraceableGeometry, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void getTransformShouldReturnTheAssignedTransform() {
        CompiledTransform compiledTransform = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        AbstractRayTraceableGeometry rayTraceableGeometry = createInstanceWithTransform(compiledTransform);

        Assertions.assertSame(compiledTransform, rayTraceableGeometry.getTransform());
    }

    protected abstract AbstractRayTraceableGeometry createInstanceWithTransform(CompiledTransform transform);

}
