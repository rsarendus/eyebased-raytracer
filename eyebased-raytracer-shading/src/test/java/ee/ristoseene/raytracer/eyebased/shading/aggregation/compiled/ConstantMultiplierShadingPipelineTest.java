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

public class ConstantMultiplierShadingPipelineTest extends AbstractUniAggregatingShadingPipelineTest {

    @Override
    protected ConstantMultiplierShadingPipeline createInstance(final ShadingPipeline shadingPipeline) {
        return new ConstantMultiplierShadingPipeline(shadingPipeline, 1.0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void constantMultiplierShadingPipelineShouldReturnShadedValueMultipliedByConstantMultiplier(final double multiplier) {
        ShadingPipeline aggregatedShadingPipeline = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline constantMultiplierShadingPipeline = new ConstantMultiplierShadingPipeline(aggregatedShadingPipeline, multiplier);

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        SampleValue shadedValue = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue).when(aggregatedShadingPipeline).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = constantMultiplierShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(multiplier);
        Mockito.verify(aggregatedShadingPipeline, Mockito.times(1)).shade(shadingContext, weightedBounceContext);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue, multiplier);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline, shadingContext, originalBounceContext, weightedBounceContext,
                accumulatorFactory, accumulator, shadedValue, combinedValue);
    }

}
