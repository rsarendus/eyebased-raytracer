package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipelineTest;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

public class TransparentShadingPipelineTest extends AbstractColorMultiplyingBounceShadingPipelineTest {

    @Override
    protected TransparentShadingPipeline createInstanceWithColorMultiplier(final ValueProvider<Vector3.Accessible> colorMultiplierProvider) {
        return new TransparentShadingPipeline(colorMultiplierProvider);
    }

    @Test
    public void shadeShouldSampleValueFromBouncingRayProcessorWhenBounceShadingFilterReturnsTrue() {
        Vector3.Accessible colorMultiplier = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> colorMultiplierProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(colorMultiplier).when(colorMultiplierProvider).getValue(Mockito.any(ShadingContext.class));

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(true).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue shadedSampleValue = Mockito.mock(SampleValue.class);
        BouncingRayProcessor bouncingRayProcessor = Mockito.mock(BouncingRayProcessor.class);
        Mockito.doReturn(shadedSampleValue).when(bouncingRayProcessor).processBouncingRay(Mockito.any(Ray.class), Mockito.any(BounceContext.class));

        SampleValue resolvedSampleValue = Mockito.mock(SampleValue.class);
        BounceSampleResolver bounceSampleResolver = mockBounceSampleResolver(resolvedSampleValue);

        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));
        Ray expectedBouncingRay = new SimpleRay(intersectionPoint, intersectingRay.getDirection());

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectionPoint).when(rayIntersectionContext).getRayIntersectionPoint();
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(rayIntersectionContext, Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                BounceSampleResolver.KEY, bounceSampleResolver,
                BouncingRayProcessor.KEY, bouncingRayProcessor
        ));

        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce();

        ShadingPipeline shadingPipeline = createInstanceWithColorMultiplier(colorMultiplierProvider);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(resolvedSampleValue, shadingResult);
        Mockito.verify(colorMultiplierProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(originalBounceContext, colorMultiplier);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BouncingRayProcessor.KEY);
        Mockito.verify(bouncingRayProcessor, Mockito.times(1))
                .processBouncingRay(Mockito.argThat(new RayMatcher(expectedBouncingRay, 0.000001)), Mockito.same(nextBounceContext));
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getRayIntersectionPoint();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSampleResolver.KEY);
        Mockito.verify(bounceSampleResolver, Mockito.times(1)).resolveBounceSample(shadingContext, shadedSampleValue, colorMultiplier);
        Mockito.verifyNoMoreInteractions(colorMultiplierProvider, shadingContext, bounceShadingFilter, bouncingRayProcessor,
                rayIntersectionContext, originalBounceContext, nextBounceContext, bounceSampleResolver);
    }

}
