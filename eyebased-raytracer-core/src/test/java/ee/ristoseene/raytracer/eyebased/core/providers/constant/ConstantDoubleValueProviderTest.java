package ee.ristoseene.raytracer.eyebased.core.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ConstantDoubleValueProviderTest {

    @Test
    public void constantValueProviderShouldCompileToItself() {
        ConstantDoubleValueProvider constantValueProvider = new ConstantDoubleValueProvider(0.0);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        DoubleValueProvider compilationResult = constantValueProvider.compile(Optional.of(compilationCache));

        Assertions.assertSame(constantValueProvider, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void getValueShouldReturnTheAssignedValueAndNotInteractWithShadingContext() {
        ConstantDoubleValueProvider constantValueProvider = new ConstantDoubleValueProvider(3.7);
        ShadingContext shadingContext = Mockito.mock(ShadingContext.class);

        Assertions.assertEquals(3.7, constantValueProvider.getDoubleValue(shadingContext));
        Mockito.verifyNoInteractions(shadingContext);
    }

    @Test
    public void getStaticValueShouldReturnTheAssignedValue() {
        ConstantDoubleValueProvider constantValueProvider = new ConstantDoubleValueProvider(7.3);

        Assertions.assertEquals(7.3, constantValueProvider.getStaticDoubleValue());
    }

}
