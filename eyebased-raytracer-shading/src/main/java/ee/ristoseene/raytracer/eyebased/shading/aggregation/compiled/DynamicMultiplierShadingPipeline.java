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
public class DynamicMultiplierShadingPipeline extends AbstractUniAggregatingShadingPipeline {

    private final DoubleValueProvider multiplierProvider;

    public DynamicMultiplierShadingPipeline(final ShadingPipeline shadingPipeline, final DoubleValueProvider multiplierProvider) {
        super(shadingPipeline);
        this.multiplierProvider = Objects.requireNonNull(multiplierProvider, "Multiplier not provided");
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final SampleValueAccumulator accumulator = shadingContext.getAttributeValue(SampleValueAccumulatorFactory.KEY).create();
        final double multiplier = multiplierProvider.getDoubleValue(shadingContext);

        if (shadingContext.getAttributeValue(WeightedShadingFilter.KEY).test(multiplier)) {
            accumulator.accumulate(
                    shadingPipeline.shade(shadingContext, bounceContext.weightedBounce(multiplier)),
                    multiplier
            );
        }

        return accumulator.getValue();
    }

}
