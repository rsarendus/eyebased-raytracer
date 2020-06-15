package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface RayTracedState extends DepthTest {

    boolean registerRayInteraction(double distance, RayTracingResult rayTracingResult);

}
