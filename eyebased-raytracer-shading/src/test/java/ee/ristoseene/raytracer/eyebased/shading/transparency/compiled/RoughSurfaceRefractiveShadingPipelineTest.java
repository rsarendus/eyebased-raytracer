package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
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
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Map;

public class RoughSurfaceRefractiveShadingPipelineTest extends AbstractRefractiveShadingPipelineTest {

    @Override
    protected RoughSurfaceRefractiveShadingPipeline createInstanceWith(
            ValueProvider<Vector3.Accessible> transparencyColor,
            DoubleValueProvider indexOfRefraction,
            ShadingPipeline totalInternalReflectionPipeline
    ) {
        return new RoughSurfaceRefractiveShadingPipeline(transparencyColor, indexOfRefraction,
                Mockito.mock(DoubleValueProvider.class), Mockito.mock(AdjustableHemisphericalSampler.class),
                totalInternalReflectionPipeline);
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingSurfaceRoughness() {
        ValueProvider<Vector3.Accessible> transparencyColor = ShadingTestingUtilities.createValueProviderMock();
        DoubleValueProvider indexOfRefraction = Mockito.mock(DoubleValueProvider.class);
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RoughSurfaceRefractiveShadingPipeline(
                        transparencyColor, indexOfRefraction, null, roughnessSampler, totalInternalReflectionPipeline
                )
        );

        Assertions.assertEquals("Surface roughness not provided", exception.getMessage());
    }

    @Test
    public void shadingPipelineShouldNotAllowMissingRoughnessSampler() {
        ValueProvider<Vector3.Accessible> transparencyColor = ShadingTestingUtilities.createValueProviderMock();
        DoubleValueProvider indexOfRefraction = Mockito.mock(DoubleValueProvider.class);
        DoubleValueProvider surfaceRoughness = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RoughSurfaceRefractiveShadingPipeline(
                        transparencyColor, indexOfRefraction, surfaceRoughness, null, totalInternalReflectionPipeline
                )
        );

        Assertions.assertEquals("Roughness sampler not provided", exception.getMessage());
    }

    @Test
    public void shadeShouldSampleValueFromBounceSamplingProcessor() {
        Vector3.Accessible transparencyColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> transparencyColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(transparencyColor).when(transparencyColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider indexOfRefractionProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(3.7).when(indexOfRefractionProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider surfaceRoughnessProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(7.3).when(surfaceRoughnessProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        SampleValue shadedSampleValue = Mockito.mock(SampleValue.class);
        BounceSamplingProcessor bounceSamplingProcessor = Mockito.mock(BounceSamplingProcessor.class);
        Mockito.doReturn(shadedSampleValue).when(bounceSamplingProcessor).processBounceSampling(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyDouble());

        SampleValue resolvedSampleValue = Mockito.mock(SampleValue.class);
        BounceSampleResolver bounceSampleResolver = Mockito.mock(BounceSampleResolver.class);
        Mockito.doReturn(resolvedSampleValue).when(bounceSampleResolver).resolveBounceSample(Mockito.any(ShadingContext.class), Mockito.any(SampleValue.class));

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                BounceSamplingProcessor.KEY, bounceSamplingProcessor,
                BounceSampleResolver.KEY, bounceSampleResolver
        ));

        BounceContext bounceContext = Mockito.mock(BounceContext.class);
        ShadingPipeline shadingPipeline = new RoughSurfaceRefractiveShadingPipeline(
                transparencyColorProvider, indexOfRefractionProvider, surfaceRoughnessProvider, roughnessSampler, totalInternalReflectionPipeline
        );

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(resolvedSampleValue, shadingResult);
        Mockito.verify(transparencyColorProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(surfaceRoughnessProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(indexOfRefractionProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSamplingProcessor.KEY);
        Mockito.verify(bounceSamplingProcessor, Mockito.times(1)).processBounceSampling(Mockito.any(BounceSamplingProcessor.BounceSampler.class),
                Mockito.same(shadingContext), Mockito.same(bounceContext), AdditionalMatchers.eq(7.3, 0.000001));
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSampleResolver.KEY);
        Mockito.verify(bounceSampleResolver, Mockito.times(1)).resolveBounceSample(shadingContext, shadedSampleValue);
        Mockito.verifyNoMoreInteractions(transparencyColorProvider, surfaceRoughnessProvider, indexOfRefractionProvider, roughnessSampler,
                totalInternalReflectionPipeline, shadingContext, bounceSamplingProcessor, bounceSampleResolver, bounceContext);
    }

    @Test
    public void sampleBounceOfBounceSamplerShouldSampleValueFromTotalInternalReflectionPipelineWhenRayReflectsInternally() {
        SampleValue internallyReflectedSampleValue = Mockito.mock(SampleValue.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);
        Mockito.doReturn(internallyReflectedSampleValue).when(totalInternalReflectionPipeline).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.0, 0.0, 0.0);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(0.0), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.0, 0.0, 1.0));
        Vector3.Accessible roughSampledNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(-0.1, 1.0, 0.0);

        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        Mockito.doReturn(roughSampledNormal).when(roughnessSampler).sample(Mockito.any(Vector3.Accessible.class), Mockito.anyDouble());
        BounceSamplingProcessor.BounceSampler bounceSampler = getPipelineBounceSampler(Mockito.mock(Vector3.Accessible.class),
                1.1, 3.75, roughnessSampler, totalInternalReflectionPipeline);

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of());
        BounceContext bounceContext = Mockito.mock(BounceContext.class);

        SampleValue sampledValue = bounceSampler.sampleBounce(shadingContext, bounceContext);

        Assertions.assertSame(internallyReflectedSampleValue, sampledValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(roughnessSampler, Mockito.times(1)).sample(surfaceNormal, 3.75);
        Mockito.verify(totalInternalReflectionPipeline, Mockito.times(1)).shade(shadingContext, bounceContext);
        Mockito.verifyNoMoreInteractions(totalInternalReflectionPipeline, roughnessSampler, shadingContext,
                geometryContext, rayIntersectionContext, bounceContext, internallyReflectedSampleValue);
    }

    @Test
    public void sampleBounceOfBounceSamplerShouldSampleValueFromBouncingRayProcessorWhenBounceShadingFilterReturnsTrue() {
        Vector3.Accessible transparencyColor = Mockito.mock(Vector3.Accessible.class);

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.1, 0.3, 0.9);
        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));
        Vector3.Accessible roughSampledNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(10.1, 11.2, 12.3);
        Ray expectedBouncingRay = new SimpleRay(intersectionPoint, VecMath
                .refract(intersectingRay.getDirection(), roughSampledNormal, 0.91, Factories.FACTORY_CONST_VECTOR3_xyz));

        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        Mockito.doReturn(roughSampledNormal).when(roughnessSampler).sample(Mockito.any(Vector3.Accessible.class), Mockito.anyDouble());
        BounceSamplingProcessor.BounceSampler bounceSampler = getPipelineBounceSampler(transparencyColor,
                0.91, 3.75, roughnessSampler, Mockito.mock(ShadingPipeline.class));

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(true).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue shadedSampleValue = Mockito.mock(SampleValue.class);
        SampleValue multipliedSampleValue = Mockito.mock(SampleValue.class);
        BouncingRayProcessor bouncingRayProcessor = Mockito.mock(BouncingRayProcessor.class);
        Mockito.doReturn(shadedSampleValue).when(bouncingRayProcessor).processBouncingRay(Mockito.any(Ray.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(multipliedSampleValue).when(shadedSampleValue).multiplied(Mockito.any(Vector3.Accessible.class));

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectionPoint).when(rayIntersectionContext).getRayIntersectionPoint();
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                BouncingRayProcessor.KEY, bouncingRayProcessor
        ));

        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce();

        SampleValue sampledValue = bounceSampler.sampleBounce(shadingContext, originalBounceContext);

        Assertions.assertSame(multipliedSampleValue, sampledValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(roughnessSampler, Mockito.times(1)).sample(surfaceNormal, 3.75);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(originalBounceContext, transparencyColor);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BouncingRayProcessor.KEY);
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getRayIntersectionPoint();
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce();
        Mockito.verify(bouncingRayProcessor, Mockito.times(1))
                .processBouncingRay(Mockito.argThat(new RayMatcher(expectedBouncingRay, 0.000001)), Mockito.same(nextBounceContext));
        Mockito.verify(shadedSampleValue, Mockito.times(1)).multiplied(transparencyColor);
        Mockito.verifyNoMoreInteractions(transparencyColor, roughnessSampler, bounceShadingFilter, bouncingRayProcessor, shadingContext,
                geometryContext, rayIntersectionContext, originalBounceContext, nextBounceContext);
    }

    @Test
    public void sampleBounceOfBounceSamplerShouldReturnOpaqueBlackSampleValueWhenBounceShadingFilterReturnsFalse() {
        Vector3.Accessible transparencyColor = Mockito.mock(Vector3.Accessible.class);

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.1, 0.3, 0.9);
        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));
        Vector3.Accessible roughSampledNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(10.1, 11.2, 12.3);

        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        Mockito.doReturn(roughSampledNormal).when(roughnessSampler).sample(Mockito.any(Vector3.Accessible.class), Mockito.anyDouble());
        BounceSamplingProcessor.BounceSampler bounceSampler = getPipelineBounceSampler(transparencyColor,
                0.91, 3.75, roughnessSampler, Mockito.mock(ShadingPipeline.class));

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(false).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue sampleValue = Mockito.mock(SampleValue.class);
        SampleValueFactory sampleValueFactory = Mockito.mock(SampleValueFactory.class);
        Mockito.doReturn(sampleValue).when(sampleValueFactory).create(Mockito.any(), Mockito.any(Vector3.Accessible.class));

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                SampleValueFactory.KEY, sampleValueFactory
        ));

        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce();

        SampleValue sampledValue = bounceSampler.sampleBounce(shadingContext, originalBounceContext);

        Assertions.assertSame(sampleValue, sampledValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(roughnessSampler, Mockito.times(1)).sample(surfaceNormal, 3.75);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(originalBounceContext, transparencyColor);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueFactory.KEY);
        Mockito.verify(sampleValueFactory, Mockito.times(1)).create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
        Mockito.verifyNoMoreInteractions(transparencyColor, roughnessSampler, bounceShadingFilter, sampleValueFactory, shadingContext,
                geometryContext, rayIntersectionContext, originalBounceContext, nextBounceContext);
    }

    private static BounceSamplingProcessor.BounceSampler getPipelineBounceSampler(
            Vector3.Accessible transparencyColor, double indexOfRefraction,
            double surfaceRoughness, AdjustableHemisphericalSampler roughnessSampler,
            ShadingPipeline totalInternalReflectionPipeline
    ) {
        ValueProvider<Vector3.Accessible> transparencyColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(transparencyColor).when(transparencyColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider indexOfRefractionProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(indexOfRefraction).when(indexOfRefractionProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider surfaceRoughnessProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(surfaceRoughness).when(surfaceRoughnessProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        BounceSamplingProcessor bounceSamplingProcessor = Mockito.mock(BounceSamplingProcessor.class);
        Mockito.doReturn(Mockito.mock(SampleValue.class)).when(bounceSamplingProcessor).processBounceSampling(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.anyDouble());
        BounceSampleResolver bounceSampleResolver = Mockito.mock(BounceSampleResolver.class);

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                BounceSamplingProcessor.KEY, bounceSamplingProcessor,
                BounceSampleResolver.KEY, bounceSampleResolver
        ));

        ShadingPipeline shadingPipeline = new RoughSurfaceRefractiveShadingPipeline(
                transparencyColorProvider, indexOfRefractionProvider, surfaceRoughnessProvider, roughnessSampler, totalInternalReflectionPipeline
        );
        shadingPipeline.shade(shadingContext, Mockito.mock(BounceContext.class));

        ArgumentCaptor<BounceSamplingProcessor.BounceSampler> bounceSamplerCaptor = ArgumentCaptor.forClass(BounceSamplingProcessor.BounceSampler.class);
        Mockito.verify(bounceSamplingProcessor).processBounceSampling(bounceSamplerCaptor.capture(), Mockito.any(), Mockito.any(), Mockito.anyDouble());
        return bounceSamplerCaptor.getValue();
    }

}
