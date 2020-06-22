package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractBiAggregatingShadingPipelineTest {

    protected abstract AbstractBiAggregatingShadingPipeline createInstance(ShadingPipeline shadingPipeline1, ShadingPipeline shadingPipeline2);

    @Test
    public void biAggregatingShadingPipelineShouldNotAllowMissingFirstShadingPipeline() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstance(null, Mockito.mock(ShadingPipeline.class))
        );

        Assertions.assertEquals("First shading pipeline not provided", exception.getMessage());
    }

    @Test
    public void biAggregatingShadingPipelineShouldNotAllowMissingSecondShadingPipeline() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstance(Mockito.mock(ShadingPipeline.class), null)
        );

        Assertions.assertEquals("Second shading pipeline not provided", exception.getMessage());
    }

    @Test
    public void biAggregatingShadingPipelineShouldCompileToItself() {
        AbstractBiAggregatingShadingPipeline biAggregatingShadingPipeline = createInstance(
                Mockito.mock(ShadingPipeline.class), Mockito.mock(ShadingPipeline.class)
        );

        ShadingPipeline compilationResult = biAggregatingShadingPipeline.compile(Optional.empty());

        Assertions.assertSame(biAggregatingShadingPipeline, compilationResult);
    }

}
