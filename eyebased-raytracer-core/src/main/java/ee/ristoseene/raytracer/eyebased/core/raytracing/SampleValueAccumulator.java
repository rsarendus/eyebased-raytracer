package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface SampleValueAccumulator {

   SampleValueAccumulator NO_OP_INSTANCE = new SampleValueAccumulator() {

        @Override
        public void accumulate(final SampleValue sampleValue) {}

        @Override
        public void accumulate(final SampleValue sampleValue, final double sampleWeight) {}

        @Override
        public void multiplyAndAccumulate(final double accumulatorMultiplier, final SampleValue sampleValue) {}

        @Override
        public void multiplyAndAccumulate(final double accumulatorMultiplier, final SampleValue sampleValue, final double sampleWeight) {}

        @Override
        public void multiply(double accumulatorMultiplier) {}

        @Override
        public SampleValue getValue() {
            return SampleValue.NO_OP_INSTANCE;
        }

    };

    void accumulate(SampleValue sampleValue);
    void accumulate(SampleValue sampleValue, double sampleWeight);
    void multiplyAndAccumulate(double accumulatorMultiplier, SampleValue sampleValue);
    void multiplyAndAccumulate(double accumulatorMultiplier, SampleValue sampleValue, double sampleWeight);
    void multiply(double accumulatorMultiplier);
    SampleValue getValue();

}
