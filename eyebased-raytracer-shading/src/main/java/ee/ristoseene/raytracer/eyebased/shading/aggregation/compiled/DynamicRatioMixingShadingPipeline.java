package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.shading.configuration.WeightedShadingFilter;

import java.util.Objects;

/**
 * Instances of this class rely on {@link SampleValueAccumulatorFactory} for creating accumulators for combining
 * shading results.
 *
 * Instances of this class rely on {@link WeightedShadingFilter} for deciding whether it is necessary to invoke
 * aggregated shading pipelines.
 *
 * @see SampleValueAccumulatorFactory
 * @see SampleValueAccumulatorFactory#KEY
 *
 * @see WeightedShadingFilter
 * @see WeightedShadingFilter#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 */
public class DynamicRatioMixingShadingPipeline extends AbstractBiAggregatingShadingPipeline {

    private final DoubleValueProvider ratioProvider;

    public DynamicRatioMixingShadingPipeline(
            final ShadingPipeline shadingPipeline1,
            final ShadingPipeline shadingPipeline2,
            final DoubleValueProvider ratioProvider
    ) {
        super(shadingPipeline1, shadingPipeline2);
        this.ratioProvider = Objects.requireNonNull(ratioProvider, "Ratio provider not provided");
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final SampleValueAccumulator accumulator = shadingContext.getAttributeValue(SampleValueAccumulatorFactory.KEY).create();
        final WeightedShadingFilter shadingFilter = shadingContext.getAttributeValue(WeightedShadingFilter.KEY);
        final double multiplier2 = ratioProvider.getDoubleValue(shadingContext);
        final double multiplier1 = 1.0 - multiplier2;

        if (shadingFilter.test(multiplier1)) {
            accumulator.accumulate(
                    shadingPipeline1.shade(shadingContext, bounceContext.weightedBounce(multiplier1)),
                    multiplier1
            );
        }
        if (shadingFilter.test(multiplier2)) {
            accumulator.accumulate(
                    shadingPipeline2.shade(shadingContext, bounceContext.weightedBounce(multiplier2)),
                    multiplier2
            );
        }

        return accumulator.getValue();
    }

}
