package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public abstract class AbstractUniAggregatingShadingPipelineTest {

    protected abstract AbstractUniAggregatingShadingPipeline createInstance(ShadingPipeline shadingPipeline);

    @Test
    public void uniAggregatingShadingPipelineShouldNotAllowMissingShadingPipeline() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstance(null)
        );

        Assertions.assertEquals("Shading pipeline not provided", exception.getMessage());
    }

    @Test
    public void uniAggregatingShadingPipelineShouldCompileToItself() {
        AbstractUniAggregatingShadingPipeline uniAggregatingShadingPipeline = createInstance(Mockito.mock(ShadingPipeline.class));

        ShadingPipeline compilationResult = uniAggregatingShadingPipeline.compile(Optional.empty());

        Assertions.assertSame(uniAggregatingShadingPipeline, compilationResult);
    }

}
