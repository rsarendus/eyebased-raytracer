package ee.ristoseene.raytracer.eyebased.shading.emission.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Optional;

public class ConstantColorShadingPipelineTest {

    @Test
    public void shadingPipelineShouldNotAllowMissingColor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new ConstantColorShadingPipeline(null)
        );

        Assertions.assertEquals("Color not provided", exception.getMessage());
    }

    @Test
    public void constantColorShadingPipelineShouldCompileToItself() {
        ConstantColorShadingPipeline shadingPipeline = new ConstantColorShadingPipeline(Vectors.VECTOR3_ZERO_ZERO_ZERO);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        ShadingPipeline compilationResult = shadingPipeline.compile(Optional.of(compilationCache));

        Assertions.assertSame(shadingPipeline, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void shadeShouldReturnSampleValueWithTheAssignedColor() {
        SampleValue sampleValue = Mockito.mock(SampleValue.class);
        SampleValueFactory sampleValueFactory = Mockito.mock(SampleValueFactory.class);
        Mockito.doReturn(sampleValue).when(sampleValueFactory).create(Mockito.any(), Mockito.any(Vector3.Accessible.class));

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueFactory.KEY, sampleValueFactory
        ));

        BounceContext bounceContext = Mockito.mock(BounceContext.class);
        Vector3.Accessible color = new ImmutableVector3(1.1, 2.2, 3.3);
        ShadingPipeline shadingPipeline = new ConstantColorShadingPipeline(color);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(sampleValue, shadingResult);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueFactory.KEY);
        Mockito.verify(sampleValueFactory, Mockito.times(1)).create(shadingContext, color);
        Mockito.verifyNoMoreInteractions(shadingContext, sampleValueFactory, sampleValue);
    }

}
