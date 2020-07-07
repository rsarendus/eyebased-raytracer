package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;

public class BounceContextImpl implements BounceContext {

    private final double accumulatedSampleWeight;
    private final int bounce;

    public BounceContextImpl(final double sampleWeight) {
        this(0, sampleWeight);
    }

    public BounceContextImpl(final int bounce, final double accumulatedSampleWeight) {
        this.accumulatedSampleWeight = accumulatedSampleWeight;
        this.bounce = bounce;
    }

    @Override
    public int getBounce() {
        return bounce;
    }

    @Override
    public double getAccumulatedSampleWeight() {
        return accumulatedSampleWeight;
    }

    @Override
    public BounceContextImpl weightedBounce(final double sampleWeightMultiplier) {
        return new BounceContextImpl(
                this.bounce,
                this.accumulatedSampleWeight * sampleWeightMultiplier
        );
    }

    @Override
    public BounceContextImpl nextBounce(final double sampleWeightMultiplier) {
        return new BounceContextImpl(
                this.bounce + 1,
                this.accumulatedSampleWeight * sampleWeightMultiplier
        );
    }

    @Override
    public BounceContextImpl nextBounce() {
        return new BounceContextImpl(
                this.bounce + 1,
                this.accumulatedSampleWeight
        );
    }

}
