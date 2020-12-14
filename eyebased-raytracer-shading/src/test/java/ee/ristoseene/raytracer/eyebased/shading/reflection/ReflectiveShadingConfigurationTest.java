package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.RoughSurfaceReflectiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.SimpleReflectiveShadingPipeline;
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
        CompilableValueProvider<Vector3.Accessible> reflectionColor = createCompilableValueProviderMock();
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setReflectionColor(reflectionColor);

        Assertions.assertSame(reflectionColor, shadingConfiguration.getReflectionColor());
    }

    @Test
    public void withReflectionColorShouldSetTheReflectionColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> reflectionColor = createCompilableValueProviderMock();
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withReflectionColor(reflectionColor));
        Assertions.assertSame(reflectionColor, shadingConfiguration.getReflectionColor());
    }

    @Test
    public void setSurfaceRoughnessShouldSetTheSurfaceRoughness() {
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setSurfaceRoughness(surfaceRoughness);

        Assertions.assertSame(surfaceRoughness, shadingConfiguration.getSurfaceRoughness());
    }

    @Test
    public void withSurfaceRoughnessShouldSetTheSurfaceRoughnessAndReturnItself() {
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withSurfaceRoughness(surfaceRoughness));
        Assertions.assertSame(surfaceRoughness, shadingConfiguration.getSurfaceRoughness());
    }

    @Test
    public void setRoughnessSamplerShouldSetTheRoughnessSampler() {
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setRoughnessSampler(roughnessSampler);

        Assertions.assertSame(roughnessSampler, shadingConfiguration.getRoughnessSampler());
    }

    @Test
    public void withRoughnessSamplerShouldSetTheRoughnessSamplerAndReturnItself() {
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withRoughnessSampler(roughnessSampler));
        Assertions.assertSame(roughnessSampler, shadingConfiguration.getRoughnessSampler());
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
    public void compileForUnsetSurfaceRoughnessShouldReturnSimpleReflectivePipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SimpleReflectiveShadingPipeline);
    }

    @Test
    public void compileForZeroSurfaceRoughnessShouldReturnSimpleReflectivePipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withSurfaceRoughness(new ConstantDoubleValueProvider(0.0));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SimpleReflectiveShadingPipeline);
    }

    @Test
    public void compileForUnsetColorAndNonZeroRoughnessShouldReturnRoughSurfaceReflectiveShadingPipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withSurfaceRoughness(new ConstantDoubleValueProvider(3.7));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof RoughSurfaceReflectiveShadingPipeline);
    }

    @Test
    public void compileForNonBlackColorAndNonZeroRoughnessShouldReturnRoughSurfaceReflectiveShadingPipeline() {
        ReflectiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withReflectionColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withSurfaceRoughness(new ConstantDoubleValueProvider(3.7));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof RoughSurfaceReflectiveShadingPipeline);
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        CompilableValueProvider<Vector3.Accessible> reflectionColor = createCompilableValueProviderMock();
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        ReflectiveShadingConfiguration originalShadingConfiguration = createDefaultInstance()
                .withReflectionColor(reflectionColor)
                .withSurfaceRoughness(surfaceRoughness)
                .withRoughnessSampler(roughnessSampler);

        ReflectiveShadingConfiguration clonedShadingConfiguration = cloneShouldCreateValidCopyOfItself(originalShadingConfiguration);

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(reflectionColor, clonedShadingConfiguration.getReflectionColor());
        Assertions.assertSame(surfaceRoughness, clonedShadingConfiguration.getSurfaceRoughness());
        Assertions.assertSame(roughnessSampler, clonedShadingConfiguration.getRoughnessSampler());
    }

}
