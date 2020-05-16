package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.BlurryReflectiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.SharpReflectiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.createCompilableValueProviderMock;

public class ReflectiveShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected ReflectiveShadingConfiguration createDefaultInstance() {
        return new ReflectiveShadingConfiguration();
    }

    @Test
    public void setReflectionColorShouldSetTheReflectionColor() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setReflectionColor(color);

        Assertions.assertSame(color, shadingConfiguration.getReflectionColor());
    }

    @Test
    public void withReflectionColorShouldSetTheReflectionColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withReflectionColor(color));
        Assertions.assertSame(color, shadingConfiguration.getReflectionColor());
    }

    @Test
    public void setReflectionBlurrinessShouldSetTheReflectionBlurriness() {
        CompilableDoubleValueProvider blurriness = Mockito.mock(CompilableDoubleValueProvider.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setReflectionBlurriness(blurriness);

        Assertions.assertSame(blurriness, shadingConfiguration.getReflectionBlurriness());
    }

    @Test
    public void withReflectionBlurrinessShouldSetTheReflectionBlurrinessAndReturnItself() {
        CompilableDoubleValueProvider blurriness = Mockito.mock(CompilableDoubleValueProvider.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withReflectionBlurriness(blurriness));
        Assertions.assertSame(blurriness, shadingConfiguration.getReflectionBlurriness());
    }

    @Test
    public void setBlurSamplerShouldSetTheBlurSampler() {
        AdjustableHemisphericalSampler blurSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setBlurSampler(blurSampler);

        Assertions.assertSame(blurSampler, shadingConfiguration.getBlurSampler());
    }

    @Test
    public void withBlurSamplerShouldSetTheBlurSamplerAndReturnItself() {
        AdjustableHemisphericalSampler blurSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withBlurSampler(blurSampler));
        Assertions.assertSame(blurSampler, shadingConfiguration.getBlurSampler());
    }

    @Test
    public void compileForConstantBlackReflectionColorShouldReturnBlackConstantColorPipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(0.0)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForUnsetReflectionBlurrinessShouldReturnSharpReflectivePipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SharpReflectiveShadingPipeline);
    }

    @Test
    public void compileForZeroReflectionBlurrinessShouldReturnSharpReflectivePipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withReflectionBlurriness(new ConstantDoubleValueProvider(0.0));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SharpReflectiveShadingPipeline);
    }

    @Test
    public void compileForUnsetColorAndNonZeroBlurrinessShouldReturnBlurryReflectiveShadingPipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionBlurriness(new ConstantDoubleValueProvider(3.7));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof BlurryReflectiveShadingPipeline);
    }

    @Test
    public void compileForNonBlackColorAndNonZeroBlurrinessShouldReturnBlurryReflectiveShadingPipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withReflectionBlurriness(new ConstantDoubleValueProvider(3.7));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof BlurryReflectiveShadingPipeline);
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        CompilableValueProvider<Vector3.Accessible> reflectionColor = createCompilableValueProviderMock();
        CompilableDoubleValueProvider reflectionBlurriness = Mockito.mock(CompilableDoubleValueProvider.class);
        AdjustableHemisphericalSampler blurSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration originalShadingConfiguration = createDefaultInstance()
                .withReflectionColor(reflectionColor)
                .withReflectionBlurriness(reflectionBlurriness)
                .withBlurSampler(blurSampler);

        ReflectiveShadingConfiguration clonedShadingConfiguration = cloneShouldCreateValidCopyOfItself(originalShadingConfiguration);

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(reflectionColor, clonedShadingConfiguration.getReflectionColor());
        Assertions.assertSame(reflectionBlurriness, clonedShadingConfiguration.getReflectionBlurriness());
        Assertions.assertSame(blurSampler, clonedShadingConfiguration.getBlurSampler());
    }

}
