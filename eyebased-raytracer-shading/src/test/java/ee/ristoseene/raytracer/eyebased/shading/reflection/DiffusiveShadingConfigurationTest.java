package ee.ristoseene.raytracer.eyebased.shading.reflection;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.common.HemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.reflection.compiled.DiffusiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.createCompilableValueProviderMock;

public class DiffusiveShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected DiffusiveShadingConfiguration createDefaultInstance() {
        return new DiffusiveShadingConfiguration();
    }

    @Test
    public void setDiffuseColorShouldSetTheDiffuseColor() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setDiffuseColor(color);

        Assertions.assertSame(color, shadingConfiguration.getDiffuseColor());
    }

    @Test
    public void withDiffuseColorShouldSetTheDiffuseColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withDiffuseColor(color));
        Assertions.assertSame(color, shadingConfiguration.getDiffuseColor());
    }

    @Test
    public void setDiffuseDirectionSamplerShouldSetTheDiffuseDirectionSampler() {
        HemisphericalSampler diffuseDirectionSampler = Mockito.mock(HemisphericalSampler.class);
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setDiffuseDirectionSampler(diffuseDirectionSampler);

        Assertions.assertSame(diffuseDirectionSampler, shadingConfiguration.getDiffuseDirectionSampler());
    }

    @Test
    public void withDiffuseDirectionSamplerShouldSetTheDiffuseDirectionSamplerAndReturnItself() {
        HemisphericalSampler diffuseDirectionSampler = Mockito.mock(HemisphericalSampler.class);
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withDiffuseDirectionSampler(diffuseDirectionSampler));
        Assertions.assertSame(diffuseDirectionSampler, shadingConfiguration.getDiffuseDirectionSampler());
    }

    @Test
    public void setIntensityCompensationMultiplierShouldSetTheIntensityCompensationMultiplier() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setIntensityCompensationMultiplier(7.3);

        Assertions.assertEquals(7.3, shadingConfiguration.getIntensityCompensationMultiplier());
    }

    @Test
    public void withIntensityCompensationMultiplierShouldSetTheIntensityCompensationMultiplierAndReturnItself() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withIntensityCompensationMultiplier(7.3));
        Assertions.assertEquals(7.3, shadingConfiguration.getIntensityCompensationMultiplier());
    }

    @Test
    public void compileForConstantBlackDiffuseColorShouldReturnBlackConstantColorPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withDiffuseColor(new ConstantValueProvider<>(new ImmutableVector3(0.0)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForNullDiffuseDirectionSamplerShouldFail() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withDiffuseDirectionSampler(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> shadingConfiguration.compile(Optional.empty())
        );

        Assertions.assertEquals("Diffuse direction sampler not provided", exception.getMessage());
    }

    @Test
    public void compileForZeroIntensityCompensationMultiplierShouldReturnBlackConstantColorPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withIntensityCompensationMultiplier(0.0);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForUnsetDiffuseColorAndUnsetIntensityCompensationMultiplierShouldReturnDiffusiveShadingPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DiffusiveShadingPipeline);
    }

    @Test
    public void compileForSetDiffuseColorAndUnsetIntensityCompensationMultiplierShouldReturnDiffusiveShadingPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withDiffuseColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DiffusiveShadingPipeline);
    }

    @Test
    public void compileForUnsetDiffuseColorAndSetIntensityCompensationMultiplierShouldReturnDiffusiveShadingPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withIntensityCompensationMultiplier(3.75);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DiffusiveShadingPipeline);
    }

    @Test
    public void compileForSetDiffuseColorAndSetIntensityCompensationMultiplierShouldReturnDiffusiveShadingPipeline() {
        DiffusiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withDiffuseColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)))
                .withIntensityCompensationMultiplier(3.75);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DiffusiveShadingPipeline);
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        HemisphericalSampler diffuseDirectionSampler = Mockito.mock(HemisphericalSampler.class);
        DiffusiveShadingConfiguration originalShadingConfiguration = createDefaultInstance()
                .withDiffuseColor(color)
                .withDiffuseDirectionSampler(diffuseDirectionSampler)
                .withIntensityCompensationMultiplier(3.75);

        DiffusiveShadingConfiguration clonedShadingConfiguration = cloneShouldCreateValidCopyOfItself(originalShadingConfiguration);

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(color, clonedShadingConfiguration.getDiffuseColor());
        Assertions.assertSame(diffuseDirectionSampler, clonedShadingConfiguration.getDiffuseDirectionSampler());
        Assertions.assertEquals(3.75, clonedShadingConfiguration.getIntensityCompensationMultiplier());
    }

}
