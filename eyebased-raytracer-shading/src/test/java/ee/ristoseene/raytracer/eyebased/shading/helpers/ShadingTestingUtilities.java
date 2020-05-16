package ee.ristoseene.raytracer.eyebased.shading.helpers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import org.mockito.Mockito;

import java.util.Map;

public interface ShadingTestingUtilities {

    @SuppressWarnings("unchecked")
    static <T> ValueProvider<T> createValueProviderMock() {
        return (ValueProvider<T>) Mockito.mock(ValueProvider.class);
    }

    @SuppressWarnings("unchecked")
    static <T> CompilableValueProvider<T> createCompilableValueProviderMock() {
        return (CompilableValueProvider<T>) Mockito.mock(CompilableValueProvider.class);
    }

    @SuppressWarnings("unchecked")
    static <T> CompilableValueProvider<T> createCompilableValueProviderMock(ValueProvider<T> valueProvider) {
        CompilableValueProvider<T> compilableValueProvider = createCompilableValueProviderMock();
        Mockito.doReturn(valueProvider).when(compilableValueProvider).compile(Mockito.any());
        return compilableValueProvider;
    }

    static CompilableDoubleValueProvider createCompilableDoubleValueProviderMock(DoubleValueProvider doubleValueProvider) {
        CompilableDoubleValueProvider compilableDoubleValueProvider = Mockito.mock(CompilableDoubleValueProvider.class);
        Mockito.doReturn(doubleValueProvider).when(compilableDoubleValueProvider).compile(Mockito.any());
        return compilableDoubleValueProvider;
    }

    static ShadingContext createShadingContextMock() {
        return createShadingContextMock(null, null, false);
    }

    static ShadingContext createShadingContextMock(boolean mockDefaultAttributes) {
        return createShadingContextMock(null, null, mockDefaultAttributes);
    }

    static ShadingContext createShadingContextMock(GeometryContext geometryContext, boolean mockDefaultAttributes) {
        return createShadingContextMock(geometryContext, null, mockDefaultAttributes);
    }

    static ShadingContext createShadingContextMock(RayIntersectionContext rayIntersectionContext, boolean mockDefaultAttributes) {
        return createShadingContextMock(null, rayIntersectionContext, mockDefaultAttributes);
    }

    @SuppressWarnings("unchecked")
    static ShadingContext createShadingContextMock(GeometryContext geometryContext, RayIntersectionContext rayIntersectionContext, boolean mockDefaultAttributes) {
        ShadingContext shadingContext = Mockito.mock(ShadingContext.class);

        if (geometryContext != null) {
            Mockito.doReturn(geometryContext).when(shadingContext).getGeometryContext();
        }
        if (rayIntersectionContext != null) {
            Mockito.doReturn(rayIntersectionContext).when(shadingContext).getRayIntersectionContext();
        }
        if (mockDefaultAttributes) {
            Mockito.doAnswer(invocationOnMock -> {
                TypedAttribute typedAttribute = invocationOnMock.getArgument(0);
                return typedAttribute.getDefaultValue();
            }).when(shadingContext).getAttributeValue(Mockito.any(TypedAttribute.class));
        }

        return shadingContext;
    }

    @SuppressWarnings("unchecked")
    static ShadingContext createShadingContextMock(GeometryContext geometryContext, RayIntersectionContext rayIntersectionContext, Map<TypedAttribute, Object> attributesMap) {
        ShadingContext shadingContext = createShadingContextMock(geometryContext, rayIntersectionContext, false);
        mockShadingContextAttributes(shadingContext, attributesMap);
        return shadingContext;
    }

    @SuppressWarnings("unchecked")
    static ShadingContext createShadingContextMock(Map<TypedAttribute, Object> attributesMap) {
        ShadingContext shadingContext = createShadingContextMock(null, null, false);
        mockShadingContextAttributes(shadingContext, attributesMap);
        return shadingContext;
    }

    @SuppressWarnings("unchecked")
    static ShadingContext createShadingContextMock(TypedAttribute key, Object valueToReturn) {
        ShadingContext shadingContext = createShadingContextMock(null, null, false);
        mockShadingContextAttribute(shadingContext, key, valueToReturn);
        return shadingContext;
    }

    @SuppressWarnings("unchecked")
    static void mockShadingContextAttributes(ShadingContext shadingContext, Map<TypedAttribute, Object> attributesMap) {
        attributesMap.forEach((key, value) -> mockShadingContextAttribute(shadingContext, key, value));
    }

    @SuppressWarnings("unchecked")
    static void mockShadingContextAttribute(ShadingContext shadingContext, TypedAttribute key, Object valueToReturn) {
        Mockito.doReturn(valueToReturn).when(shadingContext).getAttributeValue(key);
    }

}
