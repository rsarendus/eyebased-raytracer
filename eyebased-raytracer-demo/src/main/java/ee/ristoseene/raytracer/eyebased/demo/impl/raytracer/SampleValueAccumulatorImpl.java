package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.mutable.MutableVector3;

public class SampleValueAccumulatorImpl extends MutableVector3 implements SampleValueAccumulator {

    private double depth = 0.0;

    @Override
    public void accumulate(final SampleValue sampleValue) {
        VecMath.add(this, sampleValue.getRGB(), this);
        depth += sampleValue.getDepth();
    }

    @Override
    public void accumulate(final SampleValue sampleValue, final double sampleWeight) {
        VecMath.multiplyAdd(this, sampleValue.getRGB(), sampleWeight, this);
        depth += sampleValue.getDepth() * sampleWeight;
    }

    @Override
    public void multiplyAndAccumulate(final double accumulatorMultiplier, final SampleValue sampleValue) {
        VecMath.multiplyAdd(this, this, accumulatorMultiplier, sampleValue.getRGB());
        depth = depth * accumulatorMultiplier + sampleValue.getDepth();
    }

    @Override
    public void multiplyAndAccumulate(final double accumulatorMultiplier, final SampleValue sampleValue, final double sampleWeight) {
        VecMathExtended.addProducts(this, this, accumulatorMultiplier, sampleValue.getRGB(), sampleWeight);
        depth = depth * accumulatorMultiplier + sampleValue.getDepth() * sampleWeight;
    }

    @Override
    public void multiply(final double accumulatorMultiplier) {
        VecMath.multiply(this, this, accumulatorMultiplier);
        depth *= accumulatorMultiplier;
    }

    @Override
    public SampleValue getValue() {
        return new SampleValueImpl(x(), y(), z(), depth);
    }

}
