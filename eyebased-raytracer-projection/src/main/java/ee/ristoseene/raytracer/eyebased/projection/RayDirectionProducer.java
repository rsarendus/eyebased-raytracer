package ee.ristoseene.raytracer.eyebased.projection;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface RayDirectionProducer extends CompiledObject {

    Vector3.Accessible produceDirection(double x, double y);

}
