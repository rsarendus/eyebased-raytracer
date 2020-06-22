package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

/**
 * Instances of this class rely on {@link SampleValueAccumulatorFactory} for creating accumulators for combining
 * shading results.
 *
 * @see SampleValueAccumulatorFactory
 * @see SampleValueAccumulatorFactory#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 */
public class ConstantMultiplierShadingPipeline extends AbstractUniAggregatingShadingPipeline {

    private final double constantMultiplier;

    public ConstantMultiplierShadingPipeline(final ShadingPipeline shadingPipeline, final double multiplier) {
        super(shadingPipeline);
        constantMultiplier = multiplier;
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final SampleValueAccumulator accumulator = shadingContext.getAttributeValue(SampleValueAccumulatorFactory.KEY).create();
        accumulator.accumulate(
                shadingPipeline.shade(shadingContext, bounceContext.weightedBounce(constantMultiplier)),
                constantMultiplier
        );
        return accumulator.getValue();
    }

}
