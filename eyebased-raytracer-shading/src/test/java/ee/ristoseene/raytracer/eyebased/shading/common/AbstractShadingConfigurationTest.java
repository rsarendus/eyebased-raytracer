package ee.ristoseene.raytracer.eyebased.shading.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractShadingConfigurationTest {

    protected abstract AbstractShadingConfiguration createDefaultInstance();

    @Test
    public void compileShouldReturnNewShadingPipelineIfCompilationCacheNotPresent() {
        AbstractShadingConfiguration shadingConfiguration = createDefaultInstance();

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnNewShadingPipelineIfNotYetCached() {
        AbstractShadingConfiguration shadingConfiguration = createDefaultInstance();
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.of(compilationCache));

        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnCachedShadingPipelineIfAlreadyInCache() {
        AbstractShadingConfiguration shadingConfiguration = createDefaultInstance();
        ShadingPipeline cachedPipeline = Mockito.mock(ShadingPipeline.class);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        Mockito.doReturn(cachedPipeline).when(compilationCache).get(shadingConfiguration);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.of(compilationCache));

        Assertions.assertSame(cachedPipeline, compilationResult);
    }

}
