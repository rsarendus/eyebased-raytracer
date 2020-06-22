package ee.ristoseene.raytracer.eyebased.shading.aggregation.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.vecmath.VecMath;

/**
 * Instances of this class rely on {@link SampleValueAccumulatorFactory} for creating accumulators for combining
 * shading results.
 *
 * @see SampleValueAccumulatorFactory
 * @see SampleValueAccumulatorFactory#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 */
public class ConstantRatioMixingShadingPipeline extends AbstractBiAggregatingShadingPipeline {

    private final double constantMultiplier1;
    private final double constantMultiplier2;

    public ConstantRatioMixingShadingPipeline(
            final ShadingPipeline shadingPipeline1,
            final ShadingPipeline shadingPipeline2,
            final double ratio
    ) {
        super(shadingPipeline1, shadingPipeline2);
        constantMultiplier1 = VecMath.lerp(1.0, 0.0, ratio);
        constantMultiplier2 = VecMath.lerp(0.0, 1.0, ratio);
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final SampleValueAccumulator accumulator = shadingContext.getAttributeValue(SampleValueAccumulatorFactory.KEY).create();
        accumulator.accumulate(
                shadingPipeline1.shade(shadingContext, bounceContext.weightedBounce(constantMultiplier1)),
                constantMultiplier1
        );
        accumulator.accumulate(
                shadingPipeline2.shade(shadingContext, bounceContext.weightedBounce(constantMultiplier2)),
                constantMultiplier2
        );
        return accumulator.getValue();
    }

}
