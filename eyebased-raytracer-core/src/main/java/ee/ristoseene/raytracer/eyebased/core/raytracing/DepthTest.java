package ee.ristoseene.raytracer.eyebased.core.raytracing;

@FunctionalInterface
public interface DepthTest {

    boolean testDepthRange(double minimumDepthToTest, double maximumDepthToTest);

    default boolean testDepth(double depthToTest) {
        return testDepthRange(depthToTest, depthToTest);
    }

}
