package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public abstract class AbstractIndirectBoundedRayTraceableTriangle extends AbstractBoundedRayTraceableTriangle implements IndexedTriangle {

    protected final int vertexIndex0, vertexIndex1, vertexIndex2;

    protected AbstractIndirectBoundedRayTraceableTriangle(
            final AABB bounds, final int vertexIndex0, final int vertexIndex1, final int vertexIndex2
    ) {
        super(bounds);

        if (vertexIndex0 == vertexIndex1 || vertexIndex1 == vertexIndex2 || vertexIndex2 == vertexIndex0) {
            throw new IllegalArgumentException("Overlapping vertex indices not allowed");
        }

        this.vertexIndex0 = vertexIndex0;
        this.vertexIndex1 = vertexIndex1;
        this.vertexIndex2 = vertexIndex2;
    }

    protected abstract Vector3.Accessible getVertexPosition(int vertexIndex);

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final RayTracedState rayTracedState) {
        final Vector3.Accessible v0 = getVertexPosition(vertexIndex0);

        interactWith(rayTracedState, tracingRayContext.getTracingRay(), v0,
                VecMath.subtract(getVertexPosition(vertexIndex1), v0, Factories.FACTORY_CONST_VECTOR3_xyz),
                VecMath.subtract(getVertexPosition(vertexIndex2), v0, Factories.FACTORY_CONST_VECTOR3_xyz)
        );
    }

    @Override
    public int getVertexIndex0() {
        return vertexIndex0;
    }

    @Override
    public int getVertexIndex1() {
        return vertexIndex1;
    }

    @Override
    public int getVertexIndex2() {
        return vertexIndex2;
    }

}
