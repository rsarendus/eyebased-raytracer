package ee.ristoseene.raytracer.eyebased.shading.transparency;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.transparency.compiled.RoughSurfaceRefractiveShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.transparency.compiled.SimpleRefractiveShadingPipeline;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.*;

public class TransparentShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected TransparentShadingConfiguration createDefaultInstance() {
        return new TransparentShadingConfiguration();
    }

    @Test
    public void setTransparencyColorShouldSetTheTransparencyColor() {
        CompilableValueProvider<Vector3.Accessible> transparencyColor = createCompilableValueProviderMock();
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setTransparencyColor(transparencyColor);

        Assertions.assertSame(transparencyColor, shadingConfiguration.getTransparencyColor());
    }

    @Test
    public void withTransparencyColorShouldSetTheTransparencyColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> transparencyColor = createCompilableValueProviderMock();
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withTransparencyColor(transparencyColor));
        Assertions.assertSame(transparencyColor, shadingConfiguration.getTransparencyColor());
    }

    @Test
    public void setIndexOfRefractionShouldSetTheIndexOfRefraction() {
        CompilableDoubleValueProvider indexOfRefraction = Mockito.mock(CompilableDoubleValueProvider.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setIndexOfRefraction(indexOfRefraction);

        Assertions.assertSame(indexOfRefraction, shadingConfiguration.getIndexOfRefraction());
    }

    @Test
    public void withIndexOfRefractionShouldSetTheIndexOfRefractionAndReturnItself() {
        CompilableDoubleValueProvider indexOfRefraction = Mockito.mock(CompilableDoubleValueProvider.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withIndexOfRefraction(indexOfRefraction));
        Assertions.assertSame(indexOfRefraction, shadingConfiguration.getIndexOfRefraction());
    }

    @Test
    public void setTotalInternalReflectionShaderShouldSetTheTotalInternalReflectionShader() {
        ShadingConfiguration totalInternalReflectionShader = Mockito.mock(ShadingConfiguration.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setTotalInternalReflectionShader(totalInternalReflectionShader);

        Assertions.assertSame(totalInternalReflectionShader, shadingConfiguration.getTotalInternalReflectionShader());
    }

    @Test
    public void withTotalInternalReflectionShaderShouldSetTheTotalInternalReflectionShaderAndReturnItself() {
        ShadingConfiguration totalInternalReflectionShader = Mockito.mock(ShadingConfiguration.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withTotalInternalReflectionShader(totalInternalReflectionShader));
        Assertions.assertSame(totalInternalReflectionShader, shadingConfiguration.getTotalInternalReflectionShader());
    }

    @Test
    public void setSurfaceRoughnessShouldSetTheSurfaceRoughness() {
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setSurfaceRoughness(surfaceRoughness);

        Assertions.assertSame(surfaceRoughness, shadingConfiguration.getSurfaceRoughness());
    }

    @Test
    public void withSurfaceRoughnessShouldSetTheRefractionSurfaceRoughnessAndReturnItself() {
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withSurfaceRoughness(surfaceRoughness));
        Assertions.assertSame(surfaceRoughness, shadingConfiguration.getSurfaceRoughness());
    }

    @Test
    public void setRoughnessSamplerShouldSetTheRoughnessSampler() {
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setRoughnessSampler(roughnessSampler);

        Assertions.assertSame(roughnessSampler, shadingConfiguration.getRoughnessSampler());
    }

    @Test
    public void withRoughnessSamplerShouldSetTheRoughnessSamplerAndReturnItself() {
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withRoughnessSampler(roughnessSampler));
        Assertions.assertSame(roughnessSampler, shadingConfiguration.getRoughnessSampler());
    }

    @Test
    public void compileForConstantBlackTransparencyColorAndUnsetIorAndRoughnessShouldReturnBlackConstantColorPipeline() {
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withTransparencyColor(new ConstantValueProvider<>(new ImmutableVector3(0.0)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForConstantBlackTransparencyColorAndConstantOneIorAndConstantZeroRoughnessShouldReturnBlackConstantColorPipeline() {
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withTransparencyColor(new ConstantValueProvider<>(new ImmutableVector3(0.0)))
                .withIndexOfRefraction(new ConstantDoubleValueProvider(1.0))
                .withSurfaceRoughness(new ConstantDoubleValueProvider(0.0))
                .withTotalInternalReflectionShader(Mockito.mock(ShadingConfiguration.class));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForConstantBlackTransparencyColorAndUnsetTotalInternalReflectionPipelineShouldReturnBlackConstantColorPipeline() {
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withTransparencyColor(new ConstantValueProvider<>(new ImmutableVector3(0.0)))
                .withIndexOfRefraction(Mockito.mock(CompilableDoubleValueProvider.class))
                .withSurfaceRoughness(Mockito.mock(CompilableDoubleValueProvider.class));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
        Assertions.assertEquals(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForConstantZeroRoughnessShouldReturnSimpleRefractiveShadingPipeline() {
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withTransparencyColor(createCompilableValueProviderMock(createValueProviderMock()))
                .withIndexOfRefraction(createCompilableDoubleValueProviderMock(Mockito.mock(DoubleValueProvider.class)))
                .withSurfaceRoughness(new ConstantDoubleValueProvider(0.0));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SimpleRefractiveShadingPipeline);
    }

    @Test
    public void compileForDynamicProvidersShouldReturnRoughSurfaceRefractiveShadingPipeline() {
        TransparentShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withTransparencyColor(createCompilableValueProviderMock(createValueProviderMock()))
                .withIndexOfRefraction(createCompilableDoubleValueProviderMock(Mockito.mock(DoubleValueProvider.class)))
                .withSurfaceRoughness(createCompilableDoubleValueProviderMock(Mockito.mock(DoubleValueProvider.class)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof RoughSurfaceRefractiveShadingPipeline);
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        CompilableValueProvider<Vector3.Accessible> transparencyColor = createCompilableValueProviderMock();
        CompilableDoubleValueProvider indexOfRefraction = Mockito.mock(CompilableDoubleValueProvider.class);
        ShadingConfiguration totalInternalReflectionShader = Mockito.mock(ShadingConfiguration.class);
        CompilableDoubleValueProvider surfaceRoughness = Mockito.mock(CompilableDoubleValueProvider.class);
        AdjustableHemisphericalSampler roughnessSampler = Mockito.mock(AdjustableHemisphericalSampler.class);
        TransparentShadingConfiguration originalShadingConfiguration = createDefaultInstance()
                .withTransparencyColor(transparencyColor)
                .withIndexOfRefraction(indexOfRefraction)
                .withTotalInternalReflectionShader(totalInternalReflectionShader)
                .withSurfaceRoughness(surfaceRoughness)
                .withRoughnessSampler(roughnessSampler);

        TransparentShadingConfiguration clonedShadingConfiguration = originalShadingConfiguration.clone();

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(transparencyColor, clonedShadingConfiguration.getTransparencyColor());
        Assertions.assertSame(indexOfRefraction, clonedShadingConfiguration.getIndexOfRefraction());
        Assertions.assertSame(totalInternalReflectionShader, clonedShadingConfiguration.getTotalInternalReflectionShader());
        Assertions.assertSame(surfaceRoughness, clonedShadingConfiguration.getSurfaceRoughness());
        Assertions.assertSame(roughnessSampler, clonedShadingConfiguration.getRoughnessSampler());
    }

}
