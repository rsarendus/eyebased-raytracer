package ee.ristoseene.raytracer.eyebased.shading.processors;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;

import java.util.Objects;

public class UniformSampleCountBounceSamplingProcessor implements BounceSamplingProcessor {

    @FunctionalInterface
    public interface SampleCountResolver {
        int resolveSampleCount(BounceContext bounceContext, double scatter);
    }

    private final SampleCountResolver sampleCountResolver;

    public UniformSampleCountBounceSamplingProcessor(final SampleCountResolver sampleCountResolver) {
        this.sampleCountResolver = Objects.requireNonNull(sampleCountResolver, "Sample count resolver not provided");
    }

    @Override
    public SampleValue processBounceSampling(
            final BounceSampler bounceSampler, final ShadingContext shadingContext, final BounceContext bounceContext, final double scatter
    ) {
        final int sampleCount = sampleCountResolver.resolveSampleCount(bounceContext, scatter);

        if (sampleCount == 1) {
            return bounceSampler.sampleBounce(shadingContext, bounceContext.nextBounce());
        }

        final double weight = 1.0 / sampleCount;
        final SampleValueAccumulator accumulator = shadingContext.getAttributeValue(SampleValueAccumulatorFactory.KEY).create();
        final BounceContext newBounceContext = bounceContext.nextBounce(weight);

        for (int i = 0; i < sampleCount; ++i) {
            final SampleValue sampleValue = bounceSampler.sampleBounce(shadingContext, newBounceContext);
            accumulator.accumulate(sampleValue, weight);
        }

        return accumulator.getValue();
    }

}
