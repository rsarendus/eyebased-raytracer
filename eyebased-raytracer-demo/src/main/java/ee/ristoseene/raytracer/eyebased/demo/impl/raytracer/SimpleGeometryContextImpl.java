package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.vecmath.Vector3;

public class SimpleGeometryContextImpl implements GeometryContext {

    private final Vector3.Accessible position;
    private final Vector3.Accessible surfaceNormal;

    public SimpleGeometryContextImpl(final Vector3.Accessible position, final Vector3.Accessible surfaceNormal) {
        this.position = position;
        this.surfaceNormal = surfaceNormal;
    }

    @Override
    public Vector3.Accessible getPosition() {
        return position;
    }

    @Override
    public Vector3.Accessible getSurfaceNormal() {
        return surfaceNormal;
    }

}
