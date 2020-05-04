package ee.ristoseene.raytracer.eyebased.shading.simple;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.simple.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.simple.compiled.DynamicColorShadingPipeline;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.*;

public class PlainShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected PlainShadingConfiguration createDefaultInstance() {
        return new PlainShadingConfiguration();
    }

    @Test
    public void setColorShouldSetTheColor() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setColor(color);

        Assertions.assertSame(color, shadingConfiguration.getColor());
    }

    @Test
    public void withColorShouldSetTheColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withColor(color));
        Assertions.assertSame(color, shadingConfiguration.getColor());
    }

    @Test
    public void setAlphaShouldSetTheAlpha() {
        CompilableDoubleValueProvider alpha = Mockito.mock(CompilableDoubleValueProvider.class);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setAlpha(alpha);

        Assertions.assertSame(alpha, shadingConfiguration.getAlpha());
    }

    @Test
    public void withAlphaShouldSetTheAlphaAndReturnItself() {
        CompilableDoubleValueProvider alpha = Mockito.mock(CompilableDoubleValueProvider.class);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withAlpha(alpha));
        Assertions.assertSame(alpha, shadingConfiguration.getAlpha());
    }

    @Test
    public void compileForUnsetColorAndUnsetAlphaShouldReturnConstantColorPipeline() {
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance();

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForConstantColorAndUnsetAlphaShouldReturnConstantColorPipeline() {
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForUnsetColorAndConstantAlphaShouldReturnConstantColorPipeline() {
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withAlpha(new ConstantDoubleValueProvider(4.4));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForConstantColorAndConstantAlphaShouldReturnConstantColorPipeline() {
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withAlpha(new ConstantDoubleValueProvider(4.4));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForNonConstantColorAndConstantAlphaShouldReturnDynamicColorPipeline() {
        ValueProvider<Vector3.Accessible> colorProvider = createValueProviderMock();
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock(colorProvider);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(color)
                .withAlpha(new ConstantDoubleValueProvider(4.4));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

    @Test
    public void compileForConstantColorAndNonConstantAlphaShouldReturnDynamicColorPipeline() {
        DoubleValueProvider alphaProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider alpha = createCompilableDoubleValueProviderMock(alphaProvider);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withAlpha(alpha);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

    @Test
    public void compileForNonConstantColorAndUnsetAlphaShouldReturnDynamicColorPipeline() {
        ValueProvider<Vector3.Accessible> colorProvider = createValueProviderMock();
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock(colorProvider);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(color);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

    @Test
    public void compileForUnsetColorAndNonConstantAlphaShouldReturnDynamicColorPipeline() {
        DoubleValueProvider alphaProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider alpha = createCompilableDoubleValueProviderMock(alphaProvider);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withAlpha(alpha);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

    @Test
    public void compileForNonConstantColorAndNonConstantAlphaShouldReturnDynamicColorPipeline() {
        ValueProvider<Vector3.Accessible> colorProvider = createValueProviderMock();
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock(colorProvider);
        DoubleValueProvider alphaProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider alpha = createCompilableDoubleValueProviderMock(alphaProvider);
        PlainShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(color)
                .withAlpha(alpha);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

}
