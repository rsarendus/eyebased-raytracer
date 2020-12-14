package ee.ristoseene.raytracer.eyebased.shading.reflection.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.HemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipelineTest;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Map;

public class DiffusiveShadingPipelineTest extends AbstractColorMultiplyingBounceShadingPipelineTest {

    @Override
    protected DiffusiveShadingPipeline createInstanceWithColorMultiplier(final ValueProvider<Vector3.Accessible> colorMultiplierProvider) {
        return new DiffusiveShadingPipeline(colorMultiplierProvider, Mockito.mock(HemisphericalSampler.class), 1.0);
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingDiffuseDirectionSampler() {
        ValueProvider<Vector3.Accessible> diffuseColor = ShadingTestingUtilities.createValueProviderMock();

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DiffusiveShadingPipeline(diffuseColor, null, 1.0)
        );

        Assertions.assertEquals("Diffuse direction sampler not provided", exception.getMessage());
    }

    @Test
    public void shadeShouldSampleValueFromBounceSamplingProcessorWhenBounceShadingFilterReturnsTrue() {
        Vector3.Accessible diffuseColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> diffuseColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(diffuseColor).when(diffuseColorProvider).getValue(Mockito.any(ShadingContext.class));
        HemisphericalSampler diffuseDirectionSampler = Mockito.mock(HemisphericalSampler.class);

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
        ShadingPipeline shadingPipeline = new DiffusiveShadingPipeline(diffuseColorProvider, diffuseDirectionSampler, 7.35);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(resolvedSampleValue, shadingResult);
        Mockito.verify(diffuseColorProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(bounceContext, diffuseColor);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSamplingProcessor.KEY);
        Mockito.verify(bounceSamplingProcessor, Mockito.times(1)).processBounceSampling(Mockito
                .any(BounceSamplingProcessor.BounceSampler.class), Mockito.same(shadingContext), Mockito.same(bounceContext), Mockito.eq(1.0));
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSampleResolver.KEY);
        Mockito.verify(bounceSampleResolver, Mockito.times(1)).resolveBounceSample(shadingContext, shadedSampleValue, diffuseColor);
        Mockito.verifyNoMoreInteractions(diffuseColorProvider, diffuseDirectionSampler, shadingContext,
                bounceShadingFilter, bounceSamplingProcessor, bounceSampleResolver, bounceContext);
    }

    @Test
    public void sampleBounceOfBounceSamplerShouldSampleValueFromBouncingRayProcessor() {
        SampleValue bouncingRayProcessingResult = Mockito.mock(SampleValue.class);
        BouncingRayProcessor bouncingRayProcessor = Mockito.mock(BouncingRayProcessor.class);
        Mockito.doReturn(bouncingRayProcessingResult).when(bouncingRayProcessor).processBouncingRay(Mockito.any(Ray.class), Mockito.any(BounceContext.class));

        SampleValue compensatedSampleValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(compensatedSampleValue).when(bouncingRayProcessingResult).multiplied(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble());

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.1, 0.3, 0.9);
        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Vector3.Accessible directionSampledNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(9.3, 6.2, 3.1);
        Ray expectedBouncingRay = new SimpleRay(intersectionPoint, directionSampledNormal);
        double expectedWeight = VecMath.dot(surfaceNormal, directionSampledNormal);
        double expectedCompensation = expectedWeight * 5.82;

        HemisphericalSampler diffuseDirectionSampler = Mockito.mock(HemisphericalSampler.class);
        Mockito.doReturn(directionSampledNormal).when(diffuseDirectionSampler).sample(Mockito.any(Vector3.Accessible.class));
        BounceSamplingProcessor.BounceSampler bounceSampler = getPipelineBounceSampler(diffuseDirectionSampler, 5.82);

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectionPoint).when(rayIntersectionContext).getRayIntersectionPoint();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BouncingRayProcessor.KEY, bouncingRayProcessor
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue sampledValue = bounceSampler.sampleBounce(shadingContext, originalBounceContext);

        Assertions.assertSame(compensatedSampleValue, sampledValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(diffuseDirectionSampler, Mockito.times(1)).sample(surfaceNormal);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BouncingRayProcessor.KEY);
        Mockito.verify(bouncingRayProcessor, Mockito.times(1))
                .processBouncingRay(Mockito.argThat(new RayMatcher(expectedBouncingRay, 0.000001)), Mockito.same(weightedBounceContext));
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getRayIntersectionPoint();
        Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(AdditionalMatchers.eq(expectedWeight, 0.000001));
        Mockito.verify(bouncingRayProcessingResult, Mockito.times(1)).multiplied(AdditionalMatchers.eq(expectedCompensation, 0.000001),
                AdditionalMatchers.eq(expectedCompensation, 0.000001), AdditionalMatchers.eq(expectedCompensation, 0.000001));
        Mockito.verifyNoMoreInteractions(shadingContext, bouncingRayProcessor, rayIntersectionContext, geometryContext, diffuseDirectionSampler,
                originalBounceContext, weightedBounceContext, bouncingRayProcessingResult);
    }

    private static BounceSamplingProcessor.BounceSampler getPipelineBounceSampler(HemisphericalSampler diffuseDirectionSampler, double intensityCompensationMultiplier) {
        Vector3.Accessible diffuseColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> diffuseColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(diffuseColor).when(diffuseColorProvider).getValue(Mockito.any(ShadingContext.class));

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

        ShadingPipeline shadingPipeline = new DiffusiveShadingPipeline(diffuseColorProvider, diffuseDirectionSampler, intensityCompensationMultiplier);
        shadingPipeline.shade(shadingContext, Mockito.mock(BounceContext.class));

        ArgumentCaptor<BounceSamplingProcessor.BounceSampler> bounceSamplerCaptor = ArgumentCaptor.forClass(BounceSamplingProcessor.BounceSampler.class);
        Mockito.verify(bounceSamplingProcessor).processBounceSampling(bounceSamplerCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.anyDouble());
        return bounceSamplerCaptor.getValue();
    }

}
