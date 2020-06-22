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

public class DynamicRatioMixingShadingPipelineTest extends AbstractBiAggregatingShadingPipelineTest {

    @Override
    protected DynamicRatioMixingShadingPipeline createInstance(final ShadingPipeline shadingPipeline1, final ShadingPipeline shadingPipeline2) {
        return new DynamicRatioMixingShadingPipeline(shadingPipeline1, shadingPipeline2, new ConstantDoubleValueProvider(0.5));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicRatioMixingShadingPipelineShouldReturnShadedValuesMixedUsingProvidedRatio(final double ratio) {
        ShadingPipeline aggregatedShadingPipeline1 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline aggregatedShadingPipeline2 = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicRatioMixingShadingPipeline = new DynamicRatioMixingShadingPipeline(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider);
        Mockito.doReturn(ratio).when(ratioProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        WeightedShadingFilter shadingFilter = Mockito.mock(WeightedShadingFilter.class);
        Mockito.doReturn(true).when(shadingFilter).test(Mockito.anyDouble());

        SampleValue shadedValue1 = Mockito.mock(SampleValue.class);
        SampleValue shadedValue2 = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue1).when(aggregatedShadingPipeline1).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(shadedValue2).when(aggregatedShadingPipeline2).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory,
                WeightedShadingFilter.KEY, shadingFilter
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext1 = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext2 = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext1, weightedBounceContext2).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = dynamicRatioMixingShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(ratioProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        if (ratio == 0.5) {
            Mockito.verify(shadingFilter, Mockito.times(2)).test(ratio);
            Mockito.verify(originalBounceContext, Mockito.times(2)).weightedBounce(ratio);
        } else {
            Mockito.verify(shadingFilter, Mockito.times(1)).test(1.0 - ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(1.0 - ratio);
            Mockito.verify(shadingFilter, Mockito.times(1)).test(ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(ratio);
        }
        Mockito.verify(aggregatedShadingPipeline1, Mockito.times(1)).shade(shadingContext, weightedBounceContext1);
        Mockito.verify(aggregatedShadingPipeline2, Mockito.times(1)).shade(shadingContext, weightedBounceContext2);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue1, 1.0 - ratio);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue2, ratio);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider, shadingContext, originalBounceContext,
                weightedBounceContext1, weightedBounceContext2, accumulatorFactory, accumulator, shadingFilter, shadedValue1, shadedValue2, combinedValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicRatioMixingShadingPipelineShouldNotInvokeFirstAggregatedPipelineIfFilteredOut(final double ratio) {
        ShadingPipeline aggregatedShadingPipeline1 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline aggregatedShadingPipeline2 = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicRatioMixingShadingPipeline = new DynamicRatioMixingShadingPipeline(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider);
        Mockito.doReturn(ratio).when(ratioProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        WeightedShadingFilter shadingFilter = Mockito.mock(WeightedShadingFilter.class);
        Mockito.doReturn(false, true).when(shadingFilter).test(Mockito.anyDouble());

        SampleValue shadedValue2 = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue2).when(aggregatedShadingPipeline2).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory,
                WeightedShadingFilter.KEY, shadingFilter
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext2 = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext2).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = dynamicRatioMixingShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(ratioProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        if (ratio == 0.5) {
            Mockito.verify(shadingFilter, Mockito.times(2)).test(ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(ratio);
        } else {
            Mockito.verify(shadingFilter, Mockito.times(1)).test(1.0 - ratio);
            Mockito.verify(shadingFilter, Mockito.times(1)).test(ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(ratio);
        }
        Mockito.verify(aggregatedShadingPipeline2, Mockito.times(1)).shade(shadingContext, weightedBounceContext2);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue2, ratio);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider, shadingContext, originalBounceContext,
                weightedBounceContext2, accumulatorFactory, accumulator, shadingFilter, shadedValue2, combinedValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicRatioMixingShadingPipelineShouldNotInvokeSecondAggregatedPipelineIfFilteredOut(final double ratio) {
        ShadingPipeline aggregatedShadingPipeline1 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline aggregatedShadingPipeline2 = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicRatioMixingShadingPipeline = new DynamicRatioMixingShadingPipeline(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider);
        Mockito.doReturn(ratio).when(ratioProvider).getDoubleValue(Mockito.any(ShadingContext.class));

        SampleValueAccumulator accumulator = Mockito.mock(SampleValueAccumulator.class);
        SampleValueAccumulatorFactory accumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(accumulator).when(accumulatorFactory).create();

        WeightedShadingFilter shadingFilter = Mockito.mock(WeightedShadingFilter.class);
        Mockito.doReturn(true, false).when(shadingFilter).test(Mockito.anyDouble());

        SampleValue shadedValue1 = Mockito.mock(SampleValue.class);
        SampleValue combinedValue = Mockito.mock(SampleValue.class);
        Mockito.doReturn(shadedValue1).when(aggregatedShadingPipeline1).shade(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));
        Mockito.doReturn(combinedValue).when(accumulator).getValue();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, accumulatorFactory,
                WeightedShadingFilter.KEY, shadingFilter
        ));
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext weightedBounceContext1 = Mockito.mock(BounceContext.class);
        Mockito.doReturn(weightedBounceContext1).when(originalBounceContext).weightedBounce(Mockito.anyDouble());

        SampleValue resultValue = dynamicRatioMixingShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(combinedValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(ratioProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        if (ratio == 0.5) {
            Mockito.verify(shadingFilter, Mockito.times(2)).test(ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(ratio);
        } else {
            Mockito.verify(shadingFilter, Mockito.times(1)).test(1.0 - ratio);
            Mockito.verify(originalBounceContext, Mockito.times(1)).weightedBounce(1.0 - ratio);
            Mockito.verify(shadingFilter, Mockito.times(1)).test(ratio);
        }
        Mockito.verify(aggregatedShadingPipeline1, Mockito.times(1)).shade(shadingContext, weightedBounceContext1);
        Mockito.verify(accumulator, Mockito.times(1)).accumulate(shadedValue1, 1.0 - ratio);
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider, shadingContext, originalBounceContext,
                weightedBounceContext1, accumulatorFactory, accumulator, shadingFilter, shadedValue1, combinedValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.000001, 0.1, 0.25, 0.5, 0.75, 0.999999, 1.0})
    public void dynamicRatioMixingShadingPipelineShouldNotInvokeEitherAggregatedPipelineIfFilteredOut(final double ratio) {
        ShadingPipeline aggregatedShadingPipeline1 = Mockito.mock(ShadingPipeline.class);
        ShadingPipeline aggregatedShadingPipeline2 = Mockito.mock(ShadingPipeline.class);
        DoubleValueProvider ratioProvider = Mockito.mock(DoubleValueProvider.class);
        ShadingPipeline dynamicRatioMixingShadingPipeline = new DynamicRatioMixingShadingPipeline(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider);
        Mockito.doReturn(ratio).when(ratioProvider).getDoubleValue(Mockito.any(ShadingContext.class));

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
        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);

        SampleValue resultValue = dynamicRatioMixingShadingPipeline.shade(shadingContext, originalBounceContext);

        Assertions.assertSame(accumulatorValue, resultValue);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(accumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(WeightedShadingFilter.KEY);
        Mockito.verify(ratioProvider, Mockito.times(1)).getDoubleValue(shadingContext);
        if (ratio == 0.5) {
            Mockito.verify(shadingFilter, Mockito.times(2)).test(ratio);
        } else {
            Mockito.verify(shadingFilter, Mockito.times(1)).test(1.0 - ratio);
            Mockito.verify(shadingFilter, Mockito.times(1)).test(ratio);
        }
        Mockito.verify(accumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(aggregatedShadingPipeline1, aggregatedShadingPipeline2, ratioProvider, shadingContext,
                originalBounceContext, accumulatorFactory, accumulator, shadingFilter, accumulatorValue);
    }

}
