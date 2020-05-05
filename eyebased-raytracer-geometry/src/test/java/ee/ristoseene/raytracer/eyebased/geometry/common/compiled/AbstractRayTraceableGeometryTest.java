package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractRayTraceableGeometryTest {

    @Mock
    protected ShadingPipeline shadingPipeline;

    @Test
    public void rayTraceableGeometryShouldNotAllowMissingConfiguration() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(null)
        );

        Assertions.assertEquals("Configuration not provided", exception.getMessage());
    }

    @Test
    public void rayTraceableGeometryShouldNotAllowMissingTransform() {
        AbstractRayTraceableGeometry.Configuration configuration = createDefaultConfiguration()
                .withTransform(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(configuration)
        );

        Assertions.assertEquals("Transform not provided", exception.getMessage());
    }

    @Test
    public void rayTraceableGeometryShouldNotAllowMissingShadingPipeline() {
        AbstractRayTraceableGeometry.Configuration configuration = createDefaultConfiguration()
                .withShadingPipeline(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(configuration)
        );

        Assertions.assertEquals("Shading pipeline not provided", exception.getMessage());
    }

    @Test
    public void rayTraceableGeometryShouldCompileToItself() {
        AbstractRayTraceableGeometry rayTraceableGeometry = createInstanceWithConfiguration(createDefaultConfiguration());
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        CompiledGeometry compilationResult = rayTraceableGeometry.compile(Optional.of(compilationCache));

        Assertions.assertSame(rayTraceableGeometry, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void getTransformShouldReturnTheAssignedTransform() {
        CompiledTransform compiledTransform = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        AbstractRayTraceableGeometry rayTraceableGeometry = createInstanceWithConfiguration(createDefaultConfiguration()
                .withTransform(compiledTransform));

        Assertions.assertSame(compiledTransform, rayTraceableGeometry.getTransform());
    }

    @Test
    public void getShadingPipelineShouldReturnTheAssignedShadingPipeline() {
        AbstractRayTraceableGeometry rayTraceableGeometry = createInstanceWithConfiguration(createDefaultConfiguration());

        Assertions.assertSame(shadingPipeline, rayTraceableGeometry.getShadingPipeline());
    }

    protected abstract AbstractRayTraceableGeometry createInstanceWithConfiguration(AbstractRayTraceableGeometry.Configuration configuration);

    protected AbstractRayTraceableGeometry.Configuration createDefaultConfiguration() {
        return new AbstractRayTraceableGeometry.Configuration()
                .withTransform(CompiledTransform.IDENTITY_TRANSFORM)
                .withShadingPipeline(shadingPipeline);
    }

}
