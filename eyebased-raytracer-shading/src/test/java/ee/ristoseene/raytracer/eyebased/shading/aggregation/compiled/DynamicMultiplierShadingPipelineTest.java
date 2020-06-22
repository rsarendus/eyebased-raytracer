package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.configuration.WeightedShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantDoubleValueProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Map;

public class DynamicMultiplierShadingPipelineTest extends AbstractUniAggregatingShadingPipelineTest {

    @Override
    protected DynamicMultiplierShadingPipeline createInstance(final ShadingPipeline shadingPipeline) {
        return new DynamicMultiplierShadingPipeline(shadingPipeline, new ConstantDoubleValueProvider(1.0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicMultiplierShadingPipelineShouldReturnShadedValueMultipliedByProvidedMultiplier(final double multiplier) {
        ShadingPipeline aggregatedShadingPipeline = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider multiplierProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicMultiplierShadingPipeline = new DynamicMultiplierShadingPipeline(aggregatedShadingPipeline, multiplierProvider);
        Mockito.doReturn(multiplier).when(multiplierProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        WeightedShadingFilter shadingFilter = Mockito.mock(WeightedShadingFilter.class);
        Mockito.doReturn(true).when(shadingFilter).test(Mockito.anyDouble());

        SampleValue shadedValue = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue).when(aggregatedShadingPipeline).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory,
                WeightedShadingFilter.KEY, shadingFilter
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = dynamicMultiplierShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(multiplierProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(shadingFilter, Mockito.times(1)).test(multiplier);
        Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(multiplier);
        Mockito.verify(aggregatedShadingPipeline, Mockito.times(1)).shade(shadingContext, weightedBounceContext);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue, multiplier);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline, multiplierProvider, shadingContext, originalBounceContext, weightedBounceContext,
                accumulatorFactory, accumulator, shadingFilter, shadedValue, combinedValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicMultiplierShadingPipelineShouldNotInvokeAggregatedPipelineIfFilteredOut(final double multiplier) {
        ShadingPipeline aggregatedShadingPipeline = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider multiplierProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicMultiplierShadingPipeline = new DynamicMultiplierShadingPipeline(aggregatedShadingPipeline, multiplierProvider);
        Mockito.doReturn(multiplier).when(multiplierProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        WeightedShadingFilter shadingFilter = Mockito.mock(WeightedShadingFilter.class);
        Mockito.doReturn(false).when(shadingFilter).test(Mockito.anyDouble());

        SampleValue accumulatorValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(accumulatorValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory,
                WeightedShadingFilter.KEY, shadingFilter
        ));
        BounceContext bounceContext = Mockito.mock(BounceContext.class);

        SampleValue resultValue = dynamicMultiplierShadingPipeline.shade(shadingContext, bounceContext);

        Assertions.assertSame(accumulatorValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(multiplierProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(shadingFilter, Mockito.times(1)).test(multiplier);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline, multiplierProvider, shadingContext, bounceContext,
                accumulatorFactory, accumulator, shadingFilter, accumulatorValue);
    }

}
