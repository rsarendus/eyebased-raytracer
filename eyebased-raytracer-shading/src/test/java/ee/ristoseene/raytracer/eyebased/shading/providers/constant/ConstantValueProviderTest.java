package ee.ristoseene.raytracer.eyebased.shading.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ConstantValueProviderTest {

    @Test
    public void constantValueProviderShouldCompileToItself() {
        ConstantValueProvider<Object> constantValueProvider = new ConstantValueProvider<>(new Object());
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        ValueProvider<Object> compilationResult = constantValueProvider.compile(Optional.of(compilationCache));

        Assertions.assertSame(constantValueProvider, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void getValueShouldReturnTheAssignedValueAndNotInteractWithShadingContext() {
        Object value = Mockito.mock(Object.class);
        ConstantValueProvider<Object> constantValueProvider = new ConstantValueProvider<>(value);
        ShadingContext shadingContext = Mockito.mock(ShadingContext.class);

        Assertions.assertSame(value, constantValueProvider.getValue(shadingContext));
        Mockito.verifyNoInteractions(value, shadingContext);
    }

    @Test
    public void getStaticValueShouldReturnTheAssignedValue() {
        Object value = Mockito.mock(Object.class);
        ConstantValueProvider<Object> constantValueProvider = new ConstantValueProvider<>(value);

        Assertions.assertSame(value, constantValueProvider.getStaticValue());
        Mockito.verifyNoInteractions(value);
    }

}
