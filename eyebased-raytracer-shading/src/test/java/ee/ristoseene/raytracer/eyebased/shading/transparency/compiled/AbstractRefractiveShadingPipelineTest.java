package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractRefractiveShadingPipelineTest {

    protected abstract AbstractRefractiveShadingPipeline createInstanceWith(
            ValueProvider<Vector3.Accessible> transparencyColor,
            DoubleValueProvider indexOfRefraction,
            ShadingPipeline totalInternalReflectionPipeline
    );

    @Test
    public void shadingPipelineShouldNotAllowMissingTransparencyColor() {
        DoubleValueProvider indexOfRefraction = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWith(null, indexOfRefraction, totalInternalReflectionPipeline)
        );

        Assertions.assertEquals("Transparency color not provided", exception.getMessage());
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingIndexOfRefraction() {
        ValueProvider<Vector3.Accessible> transparencyColor = ShadingTestingUtilities.createValueProviderMock();
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWith(transparencyColor, null, totalInternalReflectionPipeline)
        );

        Assertions.assertEquals("Index of refraction not provided", exception.getMessage());
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingTotalInternalReflectionPipeline() {
        ValueProvider<Vector3.Accessible> transparencyColor = ShadingTestingUtilities.createValueProviderMock();
        DoubleValueProvider indexOfRefraction = Mockito.mock(DoubleValueProvider.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWith(transparencyColor, indexOfRefraction, null)
        );

        Assertions.assertEquals("Total internal reflection pipeline not provided", exception.getMessage());
    }

    @Test
    public void refractiveShadingPipelineShouldCompileToItself() {
        AbstractRefractiveShadingPipeline shadingPipeline = createInstanceWith(
                ShadingTestingUtilities.createValueProviderMock(),
                Mockito.mock(DoubleValueProvider.class),
                Mockito.mock(ShadingPipeline.class)
        );
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        ShadingPipeline compilationResult = shadingPipeline.compile(Optional.of(compilationCache));

        Assertions.assertSame(shadingPipeline, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

}
