package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector3;

public interface TracingRayContext {

    Ray getTracingRay();

    Vector3.Accessible getInverseRayDirection();

}
