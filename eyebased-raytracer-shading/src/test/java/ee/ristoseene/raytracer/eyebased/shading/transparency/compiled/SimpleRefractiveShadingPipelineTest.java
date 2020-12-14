package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.helpers.RayMatcher;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

public class SimpleRefractiveShadingPipelineTest extends AbstractRefractiveShadingPipelineTest {

    @Override
    protected SimpleRefractiveShadingPipeline createInstanceWith(
            ValueProvider<Vector3.Accessible> transparencyColor,
            DoubleValueProvider indexOfRefraction,
            ShadingPipeline totalInternalReflectionPipeline
    ) {
        return new SimpleRefractiveShadingPipeline(transparencyColor, indexOfRefraction, totalInternalReflectionPipeline);
    }

    @Test
    public void shadeShouldSampleValueFromTotalInternalReflectionPipelineWhenRayReflectsInternally() {
        ValueProvider<Vector3.Accessible> transparencyColorProvider = ShadingTestingUtilities.createValueProviderMock();
        DoubleValueProvider indexOfRefractionProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(1.1).when(indexOfRefractionProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValue internallyReflectedSampleValue = Mockito.mock(SampleValue.class);
        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);
        Mockito.doReturn(internallyReflectedSampleValue).when(totalInternalReflectionPipeline).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.0, 0.0, 0.0);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(0.0), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(-0.1, 1.0, 0.0));

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of());

        BounceContext bounceContext = Mockito.mock(BounceContext.class);
        ShadingPipeline shadingPipeline = createInstanceWith(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(internallyReflectedSampleValue, shadingResult);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(indexOfRefractionProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(totalInternalReflectionPipeline, Mockito.times(1)).shade(shadingContext, bounceContext);
        Mockito.verifyNoMoreInteractions(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline, shadingContext,
                geometryContext, rayIntersectionContext, bounceContext, internallyReflectedSampleValue);
    }

    @Test
    public void shadeShouldSampleValueFromBouncingRayProcessorWhenBounceShadingFilterReturnsTrue() {
        Vector3.Accessible transparencyColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> transparencyColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(transparencyColor).when(transparencyColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider indexOfRefractionProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(0.91).when(indexOfRefractionProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(true).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue shadedSampleValue = Mockito.mock(SampleValue.class);
        BouncingRayProcessor bouncingRayProcessor = Mockito.mock(BouncingRayProcessor.class);
        Mockito.doReturn(shadedSampleValue).when(bouncingRayProcessor).processBouncingRay(Mockito.any(Ray.class), Mockito.any(BounceContext.class));

        SampleValue resolvedSampleValue = Mockito.mock(SampleValue.class);
        BounceSampleResolver bounceSampleResolver = Mockito.mock(BounceSampleResolver.class);
        Mockito.doReturn(resolvedSampleValue).when(bounceSampleResolver)
                .resolveBounceSample(Mockito.any(ShadingContext.class), Mockito.any(SampleValue.class), Mockito.any(Vector3.Accessible.class));

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(0.1, 0.3, 0.9);
        Vector3.Accessible intersectionPoint = new ImmutableVector3(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));
        Ray expectedBouncingRay = new SimpleRay(intersectionPoint, VecMath
                .refract(intersectingRay.getDirection(), surfaceNormal, 0.91, Factories.FACTORY_CONST_VECTOR3_xyz));

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectionPoint).when(rayIntersectionContext).getRayIntersectionPoint();
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                BounceSampleResolver.KEY, bounceSampleResolver,
                BouncingRayProcessor.KEY, bouncingRayProcessor
        ));

        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce();

        ShadingPipeline shadingPipeline = createInstanceWith(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(resolvedSampleValue, shadingResult);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(indexOfRefractionProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(transparencyColorProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(originalBounceContext, transparencyColor);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BouncingRayProcessor.KEY);
        Mockito.verify(bouncingRayProcessor, Mockito.times(1))
                .processBouncingRay(Mockito.argThat(new RayMatcher(expectedBouncingRay, 0.000001)), Mockito.same(nextBounceContext));
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getRayIntersectionPoint();
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceSampleResolver.KEY);
        Mockito.verify(bounceSampleResolver, Mockito.times(1)).resolveBounceSample(shadingContext, shadedSampleValue, transparencyColor);
        Mockito.verifyNoMoreInteractions(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline, shadingContext,
                bounceShadingFilter, bouncingRayProcessor, geometryContext, rayIntersectionContext, originalBounceContext, nextBounceContext, bounceSampleResolver);
    }

    @Test
    public void shadeShouldReturnOpaqueBlackSampleValueWhenBounceShadingFilterReturnsFalse() {
        Vector3.Accessible transparencyColor = Mockito.mock(Vector3.Accessible.class);
        ValueProvider<Vector3.Accessible> transparencyColorProvider = ShadingTestingUtilities.createValueProviderMock();
        Mockito.doReturn(transparencyColor).when(transparencyColorProvider).getValue(Mockito.any(ShadingContext.class));

        DoubleValueProvider indexOfRefractionProvider = Mockito.mock(DoubleValueProvider.class);
        Mockito.doReturn(0.91).when(indexOfRefractionProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        ShadingPipeline totalInternalReflectionPipeline = Mockito.mock(ShadingPipeline.class);

        BounceShadingFilter bounceShadingFilter = Mockito.mock(BounceShadingFilter.class);
        Mockito.doReturn(false).when(bounceShadingFilter).test(Mockito.any(BounceContext.class), Mockito.any(Vector3.Accessible.class));

        SampleValue sampleValue = Mockito.mock(SampleValue.class);
        SampleValueFactory sampleValueFactory = Mockito.mock(SampleValueFactory.class);
        Mockito.doReturn(sampleValue).when(sampleValueFactory).create(Mockito.any(), Mockito.any(Vector3.Accessible.class));

        Vector3.Accessible surfaceNormal = Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.1, 2.2, 3.3);
        Ray intersectingRay = new SimpleRay(new ImmutableVector3(4.4, 5.5, 6.6), Factories
                .FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(7.7, 8.8, 9.9));

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(surfaceNormal).when(geometryContext).getSurfaceNormal();

        RayIntersectionContext rayIntersectionContext = Mockito.mock(RayIntersectionContext.class);
        Mockito.doReturn(intersectingRay).when(rayIntersectionContext).getIntersectingRay();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(geometryContext, rayIntersectionContext, Map.of(
                BounceShadingFilter.KEY, bounceShadingFilter,
                SampleValueFactory.KEY, sampleValueFactory
        ));

        BounceContext bounceContext = Mockito.mock(BounceContext.class);

        ShadingPipeline shadingPipeline = createInstanceWith(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline);

        SampleValue shadingResult = shadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(shadingResult, shadingResult);
        Mockito.verify(shadingContext, Mockito.times(1)).getRayIntersectionContext();
        Mockito.verify(rayIntersectionContext, Mockito.times(1)).getIntersectingRay();
        Mockito.verify(shadingContext, Mockito.times(1)).getGeometryContext();
        Mockito.verify(geometryContext, Mockito.times(1)).getSurfaceNormal();
        Mockito.verify(indexOfRefractionProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(transparencyColorProvider, Mockito.times(1)).getValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(BounceShadingFilter.KEY);
        Mockito.verify(bounceShadingFilter, Mockito.times(1)).test(bounceContext, transparencyColor);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueFactory.KEY);
        Mockito.verify(sampleValueFactory, Mockito.times(1)).create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
        Mockito.verifyNoMoreInteractions(transparencyColorProvider, indexOfRefractionProvider, totalInternalReflectionPipeline, shadingContext,
                bounceShadingFilter, sampleValueFactory, geometryContext, rayIntersectionContext, bounceContext);
    }

}
