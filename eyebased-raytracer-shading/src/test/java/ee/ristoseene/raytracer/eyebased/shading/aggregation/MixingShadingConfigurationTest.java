package ee.ristoseene.raytracer.eyebased.shading.aggregation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.ConstantMultiplierShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.ConstantRatioMixingShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.DynamicMultiplierShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled.DynamicRatioMixingShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MixingShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected MixingShadingConfiguration createDefaultInstance() {
        return new MixingShadingConfiguration().withRatio(new ConstantDoubleValueProvider(0.5));
    }

    @Test
    public void setFirstShaderShouldSetTheFirstShader() {
        ShadingConfiguration firstShader = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        shadingConfiguration.setFirstShader(firstShader);

        Assertions.assertSame(firstShader, shadingConfiguration.getFirstShader());
    }

    @Test
    public void withFirstShaderShouldSetTheFirstShaderAndReturnItself() {
        ShadingConfiguration firstShader = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withFirstShader(firstShader));
        Assertions.assertSame(firstShader, shadingConfiguration.getFirstShader());
    }

    @Test
    public void setSecondShaderShouldSetTheSecondShader() {
        ShadingConfiguration secondShader = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        shadingConfiguration.setSecondShader(secondShader);

        Assertions.assertSame(secondShader, shadingConfiguration.getSecondShader());
    }

    @Test
    public void withSecondShaderShouldSetTheSecondShaderAndReturnItself() {
        ShadingConfiguration secondShader = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withSecondShader(secondShader));
        Assertions.assertSame(secondShader, shadingConfiguration.getSecondShader());
    }

    @Test
    public void setRatioShouldSetTheRatio() {
        CompilableDoubleValueProvider ratio = Mockito.mock(CompilableDoubleValueProvider.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        shadingConfiguration.setRatio(ratio);

        Assertions.assertSame(ratio, shadingConfiguration.getRatio());
    }

    @Test
    public void withRatioShouldSetTheRatioAndReturnItself() {
        CompilableDoubleValueProvider ratio = Mockito.mock(CompilableDoubleValueProvider.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withRatio(ratio));
        Assertions.assertSame(ratio, shadingConfiguration.getRatio());
    }

    @Test
    public void compileForMissingShadersShouldReturnBlackConstantColorPipeline() {
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(Mockito.mock(CompilableDoubleValueProvider.class));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertSame(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
    }

    @Test
    public void compileForConstantZeroRatioShouldReturnFirstPipelineWhenProvided() {
        ShadingPipeline firstShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(firstShadingPipeline).when(firstShadingConfiguration).compile(Mockito.any());
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(0.0))
                .withFirstShader(firstShadingConfiguration);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertSame(firstShadingPipeline, compilationResult);
        Mockito.verify(firstShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(firstShadingConfiguration, firstShadingPipeline);
    }

    @Test
    public void compileForConstantZeroRatioShouldReturnBlackConstantColorPipelineWhenFirstShaderIsMissing() {
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(0.0))
                .withSecondShader(secondShadingConfiguration);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertSame(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
        Mockito.verifyNoInteractions(secondShadingConfiguration);
    }

    @Test
    public void compileForConstantOneRatioShouldReturnSecondPipelineWhenProvided() {
        ShadingPipeline secondShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(secondShadingPipeline).when(secondShadingConfiguration).compile(Mockito.any());
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(1.0))
                .withSecondShader(secondShadingConfiguration);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertSame(secondShadingPipeline, compilationResult);
        Mockito.verify(secondShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(secondShadingConfiguration, secondShadingPipeline);
    }

    @Test
    public void compileForConstantOneRatioShouldReturnBlackConstantColorPipelineWhenSecondShaderIsMissing() {
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(1.0))
                .withFirstShader(firstShadingConfiguration);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertSame(ConstantColorShadingPipeline.BLACK_INSTANCE, compilationResult);
        Mockito.verifyNoInteractions(firstShadingConfiguration);
    }

    @Test
    public void compileForArbitraryConstantRatioShouldReturnConstantMultiplierShadingPipelineWhenFirstShaderIsMissing() {
        ShadingPipeline secondShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(secondShadingPipeline).when(secondShadingConfiguration).compile(Mockito.any());
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(0.39))
                .withSecondShader(secondShadingConfiguration);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof ConstantMultiplierShadingPipeline);
        Mockito.verify(secondShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(secondShadingConfiguration, secondShadingPipeline);
    }

    @Test
    public void compileForArbitraryConstantRatioShouldReturnConstantMultiplierShadingPipelineWhenSecondShaderIsMissing() {
        ShadingPipeline firstShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(firstShadingPipeline).when(firstShadingConfiguration).compile(Mockito.any());
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(0.39))
                .withFirstShader(firstShadingConfiguration);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof ConstantMultiplierShadingPipeline);
        Mockito.verify(firstShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(firstShadingConfiguration, firstShadingPipeline);
    }

    @Test
    public void compileForArbitraryConstantRatioShouldReturnConstantRatioMixingShadingPipelineWhenBothShadersProvided() {
        ShadingPipeline firstShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(firstShadingPipeline).when(firstShadingConfiguration).compile(Mockito.any());
        ShadingPipeline secondShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(secondShadingPipeline).when(secondShadingConfiguration).compile(Mockito.any());
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withRatio(new ConstantDoubleValueProvider(0.39))
                .withFirstShader(firstShadingConfiguration)
                .withSecondShader(secondShadingConfiguration);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof ConstantRatioMixingShadingPipeline);
        Mockito.verify(firstShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verify(secondShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(firstShadingConfiguration, secondShadingConfiguration, firstShadingPipeline, secondShadingPipeline);
    }

    @Test
    public void compileForNonConstantRatioShouldReturnDynamicMultiplierShadingPipelineWhenFirstShaderIsMissing() {
        ShadingPipeline secondShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(secondShadingPipeline).when(secondShadingConfiguration).compile(Mockito.any());
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider ratio = ShadingTestingUtilities.createCompilableDoubleValueProviderMock(ratioProvider);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withSecondShader(secondShadingConfiguration)
                .withRatio(ratio);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof DynamicMultiplierShadingPipeline);
        Mockito.verify(secondShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verify(ratio, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(secondShadingConfiguration, ratio,
                secondShadingPipeline, ratioProvider);
    }

    @Test
    public void compileForNonConstantRatioShouldReturnDynamicMultiplierShadingPipelineWhenSecondShaderIsMissing() {
        ShadingPipeline firstShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(firstShadingPipeline).when(firstShadingConfiguration).compile(Mockito.any());
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider ratio = ShadingTestingUtilities.createCompilableDoubleValueProviderMock(ratioProvider);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withFirstShader(firstShadingConfiguration)
                .withRatio(ratio);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof DynamicMultiplierShadingPipeline);
        Mockito.verify(firstShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verify(ratio, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(firstShadingConfiguration, ratio,
                firstShadingPipeline, ratioProvider);
    }

    @Test
    public void compileForNonConstantRatioShouldReturnDynamicRatioMixingShadingPipelineWhenBothShadersProvided() {
        ShadingPipeline firstShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration firstShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(firstShadingPipeline).when(firstShadingConfiguration).compile(Mockito.any());
        ShadingPipeline secondShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingConfiguration secondShadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        Mockito.doReturn(secondShadingPipeline).when(secondShadingConfiguration).compile(Mockito.any());
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        CompilableDoubleValueProvider ratio = ShadingTestingUtilities.createCompilableDoubleValueProviderMock(ratioProvider);
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration()
                .withFirstShader(firstShadingConfiguration)
                .withSecondShader(secondShadingConfiguration)
                .withRatio(ratio);
        Optional<CompilationCache> compilationCache = Optional.empty();

        ShadingPipeline compilationResult = shadingConfiguration.compile(compilationCache);

        Assertions.assertTrue(compilationResult instanceof DynamicRatioMixingShadingPipeline);
        Mockito.verify(firstShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verify(secondShadingConfiguration, Mockito.times(1)).compile(compilationCache);
        Mockito.verify(ratio, Mockito.times(1)).compile(compilationCache);
        Mockito.verifyNoMoreInteractions(firstShadingConfiguration, secondShadingConfiguration, ratio,
                firstShadingPipeline, secondShadingPipeline, ratioProvider);
    }

    @Test
    public void compileShouldFailWhenRatioIsMissing() {
        MixingShadingConfiguration shadingConfiguration = new MixingShadingConfiguration();

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> shadingConfiguration.compile(Optional.empty())
        );

        Assertions.assertEquals("Ratio not provided", exception.getMessage());
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        ShadingConfiguration firstShader = Mockito.mock(ShadingConfiguration.class);
        ShadingConfiguration secondShader = Mockito.mock(ShadingConfiguration.class);
        CompilableDoubleValueProvider ratio = Mockito.mock(CompilableDoubleValueProvider.class);
        MixingShadingConfiguration originalShadingConfiguration = new MixingShadingConfiguration()
                .withFirstShader(firstShader)
                .withSecondShader(secondShader)
                .withRatio(ratio);

        MixingShadingConfiguration clonedShadingConfiguration = originalShadingConfiguration.clone();

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(firstShader, clonedShadingConfiguration.getFirstShader());
        Assertions.assertSame(secondShader, clonedShadingConfiguration.getSecondShader());
        Assertions.assertSame(ratio, clonedShadingConfiguration.getRatio());
    }

}
