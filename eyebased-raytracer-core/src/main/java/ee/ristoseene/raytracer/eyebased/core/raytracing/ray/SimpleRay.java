package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.vecmath.Vector3;

public class SimpleRay implements Ray {

    private final Vector3.Accessible origin;
    private final Vector3.Accessible direction;

    public SimpleRay(final Ray ray) {
        this(ray.getOrigin(), ray.getDirection());
    }

    public SimpleRay(final Vector3.Accessible origin, final Vector3.Accessible direction) {
        this.origin = origin;
        this.direction = direction;
    }

    @Override
    public Vector3.Accessible getOrigin() {
        return origin;
    }

    @Override
    public Vector3.Accessible getDirection() {
        return direction;
    }

}
