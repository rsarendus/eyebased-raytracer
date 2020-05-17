package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface TracedState extends DepthTest {

    boolean registerRayInteraction(double distance, Shadeable shadeable);

}
