package ee.ristoseene.raytracer.eyebased.shading.reflection.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipelineTest;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Map;

public class RoughSurfaceReflectiveShadingPipelineTest extends AbstractColorMultiplyingBounceShadingPipelineTest {

    @Override
    protected RoughSurfaceReflectiveShadingPipeline createInstanceWithColorMultiplier(final ValueProvider<Vector3.Accessible> colorMultiplierProvider) {
        return new RoughSurfaceReflectiveShadingPipeline(colorMultiplierProvider, Mockito.mock(DoubleValueProvider.class), Mockito.mock(AdjustableHemisphericalSampler.class));
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingSurfaceRoughness() {
        ValueProvider<Vector3.Accessible> reflectionColor = ShadingTestingUtilities.createValueProviderMock();
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RoughSurfaceReflectiveShadingPipeline(reflectionColor, null, roughnessSampler)
        );

        Assertions.assertEquals("Surface roughness not provided", exception.getMessage());
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingRoughnessSampler() {
        ValueProvider<Vector3.Accessible> reflectionColor = ShadingTestingUtilities.createValueProviderMock();
        DoubleValueProvider surfaceRoughness = Mockito.mock(DoubleValueProvider.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RoughSurfaceReflectiveShadingPipeline(reflectionColor, surfaceRoughness, null)
        );

        Assertions.assertEquals("Roughness sampler not provided", exception.getMessage());
    }

    @Test
    public void shadeShouldSampleValueFromBounceSamplingProcessorWhenBounceShadingFilterReturnsTrue() {
        Vector3.Accessible reflectionColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> reflectionColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(reflectionColor).when(reflectionColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider surfaceRoughnessProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(7.35).when(surfaceRoughnessProvider).getDoubleValue(Mockito.any(ShadingContext.class));
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(true).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue shadedSampleValue = Mockito.mock(SampleValue.class);
        BounceSamplingProcessor bounceSamplingProcessor = Mockito.mock(BounceSamplingProcessor.class);
        Mockito.doReturn(shadedSampleValue).when(bounceSamplingProcessor).processBounceSampling(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyDouble());

        SampleValue resolvedSampleValue = Mockito.mock(SampleValue.class);
        BounceSampleResolver bounceSampleResolver = mockBounceSampleResolver(resolvedSampleValue);

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                BounceSampleResolver.KEY, bounceSampleResolver,
                BounceSamplingProcessor.KEY, bounceSamplingProcessor
        ));

        BounceContext bounceContext = Mockito.mock(BounceContext.class);
        ShadingPipeline shadingPipeline = new RoughSurfaceReflectiveShadingPipeline(reflectionColorProvider, surfaceRoughnessProvider, roughnessSampler);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(resolvedSampleValue, shadingResult);
        Mockito.verify(reflectionColorProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(bounceContext, reflectionColor);
        Mockito.verify(surfaceRoughnessProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSamplingProcessor.KEY);
        Mockito.verify(bounceSamplingProcessor, Mockito.times(1)).processBounceSampling(Mockito
                .any(BounceSamplingProcessor.BounceSampler.class), Mockito.same(shadingContext), Mockito.same(bounceContext), Mockito.eq(7.35));
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSampleResolver.KEY);
        Mockito.verify(bounceSampleResolver, Mockito.times(1)).resolveBounceSample(shadingContext, shadedSampleValue, reflectionColor);
        Mockito.verifyNoMoreInteractions(reflectionColorProvider, surfaceRoughnessProvider, roughnessSampler, shadingContext,
                bounceShadingFilter, bounceSamplingProcessor, bounceSampleResolver, bounceContext);
    }

    @Test
    public void sampleBounceOfBounceSamplerShouldSampleValueFromBouncingRayProcessor() {
        SampleValue bouncingRayProcessingResult = Mockito.mock(SampleValue.class);
        BouncingRayProcessor bouncingRayProcessor = Mockito.mock(BouncingRayProcessor.class);
        Mockito.doReturn(bouncingRayProcessingResult).when(bouncingRayProcessor).processBouncingRay(Mockito.any(Ray.class), Mockito.any(BounceContext.class));

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.1, 0.3, 0.9);
        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));
        Vector3.Accessible roughSampledNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(9.3, 6.2, 3.1);
        Ray expectedBouncingRay = new SimpleRay(intersectionPoint, VecMath
                .reflect(intersectingRay.getDirection(), roughSampledNormal, Factories.FACTORY_CONST_VECTOR3_xyz));

        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        Mockito.doReturn(roughSampledNormal).when(roughnessSampler).sample(Mockito.any(Vector3.Accessible.class), Mockito.anyDouble());
        BounceSamplingProcessor.BounceSampler bounceSampler = getPipelineBounceSampler(3.75, roughnessSampler);

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectionPoint).when(rayIntersectionContext).getRayIntersectionPoint();
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BouncingRayProcessor.KEY, bouncingRayProcessor
        ));
        BounceContext bounceContext = Mockito.mock(BounceContext.class);

        SampleValue sampledValue = bounceSampler.sampleBounce(shadingContext, bounceContext);

        Assertions.assertSame(bouncingRayProcessingResult, sampledValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BouncingRayProcessor.KEY);
        Mockito.verify(bouncingRayProcessor, Mockito.times(1))
                .processBouncingRay(Mockito.argThat(new RayMatcher(expectedBouncingRay, 0.000001)), Mockito.same(bounceContext));
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getRayIntersectionPoint();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(roughnessSampler, Mockito.times(1)).sample(surfaceNormal, 3.75);
        Mockito.verifyNoMoreInteractions(shadingContext, bouncingRayProcessor, rayIntersectionContext, geometryContext, roughnessSampler);
    }

    private static BounceSamplingProcessor.BounceSampler getPipelineBounceSampler(double surfaceRoughness, AdjustableHemisphericalSampler roughnessSampler) {
        Vector3.Accessible reflectionColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> reflectionColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(reflectionColor).when(reflectionColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider surfaceRoughnessProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(surfaceRoughness).when(surfaceRoughnessProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(true).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));
        BounceSamplingProcessor bounceSamplingProcessor = Mockito.mock(BounceSamplingProcessor.class);
        Mockito.doReturn(Mockito.mock(SampleValue.class)).when(bounceSamplingProcessor).processBounceSampling(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyDouble());
        BounceSampleResolver bounceSampleResolver = mockBounceSampleResolver(Mockito.mock(SampleValue.class));

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                BounceSampleResolver.KEY, bounceSampleResolver,
                BounceSamplingProcessor.KEY, bounceSamplingProcessor
        ));

        ShadingPipeline shadingPipeline = new RoughSurfaceReflectiveShadingPipeline(reflectionColorProvider, surfaceRoughnessProvider, roughnessSampler);
        shadingPipeline.shade(shadingContext, Mockito.mock(BounceContext.class));

        ArgumentCaptor<BounceSamplingProcessor.BounceSampler> bounceSamplerCaptor = ArgumentCaptor.forClass(BounceSamplingProcessor.BounceSampler.class);
        Mockito.verify(bounceSamplingProcessor).processBounceSampling(bounceSamplerCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.anyDouble());
        return bounceSamplerCaptor.getValue();
    }

}
