package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface TracedState {

    boolean isAbleToPassDepthTest(double distance);

    boolean registerRayInteraction(double distance, Shadeable shadeable);

}
