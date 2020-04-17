package ee.ristoseene.raytracer.eyebased.projection;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface RayOriginProducer extends CompiledObject {

    Vector3.Accessible produceOrigin(double x, double y);

}
