package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Map;

public class ConstantRatioMixingShadingPipelineTest extends AbstractBiAggregatingShadingPipelineTest {

    @Override
    protected ConstantRatioMixingShadingPipeline createInstance(final ShadingPipeline shadingPipeline1, final ShadingPipeline shadingPipeline2) {
        return new ConstantRatioMixingShadingPipeline(shadingPipeline1, shadingPipeline2, 0.5);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void constantRatioMixingShadingPipelineShouldReturnShadedValuesMixedUsingConstantRatio(final double ratio) {
        ShadingPipeline aggregatedShadingPipeline1 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline aggregatedShadingPipeline2 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline constantRatioMixingShadingPipeline = new ConstantRatioMixingShadingPipeline(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratio);

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        SampleValue shadedValue1 = Mockito.mock(SampleValue.class);
        SampleValue shadedValue2 = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue1).when(aggregatedShadingPipeline1).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(shadedValue2).when(aggregatedShadingPipeline2).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext1 = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext2 = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext1, weightedBounceContext2).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = constantRatioMixingShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        if (ratio == 0.5) {
            Mockito.verify(originalBounceContext, Mockito.times(2)).weightedBounce(ratio);
        } else {
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(1.0 - ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(ratio);
        }
        Mockito.verify(aggregatedShadingPipeline1, Mockito.times(1)).shade(shadingContext, weightedBounceContext1);
        Mockito.verify(aggregatedShadingPipeline2, Mockito.times(1)).shade(shadingContext, weightedBounceContext2);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue1, 1.0 - ratio);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue2, ratio);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline1, aggregatedShadingPipeline2, shadingContext, originalBounceContext,
                weightedBounceContext1, weightedBounceContext2, accumulatorFactory, accumulator, shadedValue1, shadedValue2, combinedValue);
    }

}
