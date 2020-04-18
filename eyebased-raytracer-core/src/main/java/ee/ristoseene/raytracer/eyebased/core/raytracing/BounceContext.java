package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface BounceContext {

    int getBounce();
    double getAccumulatedSampleWeight();

    BounceContext nextBounce(double sampleWeightMultiplier);
    BounceContext weightedBounce(double sampleWeightMultiplier);

    default BounceContext nextBounce() {
        return nextBounce(1.0);
    }

}
