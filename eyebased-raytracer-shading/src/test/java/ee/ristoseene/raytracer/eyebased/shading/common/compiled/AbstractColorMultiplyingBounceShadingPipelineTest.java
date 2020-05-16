package ee.ristoseene.raytracer.eyebased.shading.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractColorMultiplyingBounceShadingPipelineTest {

    protected abstract AbstractColorMultiplyingBounceShadingPipeline createInstanceWithColorMultiplier(ValueProvider<Vector3.Accessible> colorMultiplierProvider);

    @Test
    public void shadingPipelineShouldNotAllowMissingColorMultiplierProvider() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithColorMultiplier(null)
        );

        Assertions.assertEquals("Color multiplier not provided", exception.getMessage());
    }

    @Test
    public void colorMultiplyingBounceShadingPipelineShouldCompileToItself() {
        AbstractColorMultiplyingBounceShadingPipeline shadingPipeline = createInstanceWithColorMultiplier(ShadingTestingUtilities.createValueProviderMock());
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        ShadingPipeline compilationResult = shadingPipeline.compile(Optional.of(compilationCache));

        Assertions.assertSame(shadingPipeline, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void shadeShouldReturnOpaqueBlackSampleValueWhenBounceShadingFilterReturnsFalse() {
        Vector3.Accessible colorMultiplier = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> colorMultiplierProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(colorMultiplier).when(colorMultiplierProvider).getValue(Mockito.any(ShadingContext.class));

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(false).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue sampleValue = Mockito.mock(SampleValue.class);
        SampleValueFactory sampleValueFactory = Mockito.mock(SampleValueFactory.class);
        Mockito.doReturn(sampleValue).when(sampleValueFactory).create(Mockito.any(), Mockito.any(Vector3.Accessible.class));

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                SampleValueFactory.KEY, sampleValueFactory
        ));

        BounceContext bounceContext = Mockito.mock(BounceContext.class);
        ShadingPipeline shadingPipeline = createInstanceWithColorMultiplier(colorMultiplierProvider);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(sampleValue, shadingResult);
        Mockito.verify(colorMultiplierProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(bounceContext, colorMultiplier);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueFactory.KEY);
        Mockito.verify(sampleValueFactory, Mockito.times(1)).create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
        Mockito.verifyNoMoreInteractions(colorMultiplierProvider, shadingContext, bounceShadingFilter, sampleValueFactory, sampleValue);
    }

    protected static BounceSampleResolver mockBounceSampleResolver(SampleValue resolvedSampleValue) {
        BounceSampleResolver bounceSampleResolver = Mockito.mock(BounceSampleResolver.class);
        Mockito.doReturn(resolvedSampleValue).when(bounceSampleResolver)
                .resolveBounceSample(Mockito.any(ShadingContext.class), Mockito.any(SampleValue.class), Mockito.any(Vector3.Accessible.class));
        return bounceSampleResolver;
    }

}
