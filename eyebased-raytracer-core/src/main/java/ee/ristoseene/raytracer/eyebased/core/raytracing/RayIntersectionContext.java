package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.vecmath.Vector3;

public interface RayIntersectionContext {

    Ray getIntersectingRay();

    Vector3.Accessible getRayIntersectionPoint();

    double getRayIntersectionDistance();

}
