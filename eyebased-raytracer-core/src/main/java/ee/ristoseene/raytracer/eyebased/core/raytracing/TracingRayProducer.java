package ee.ristoseene.raytracer.eyebased.core.raytracing;

@FunctionalInterface
public interface TracingRayProducer {

    Ray produceRay(double x, double y);

}
