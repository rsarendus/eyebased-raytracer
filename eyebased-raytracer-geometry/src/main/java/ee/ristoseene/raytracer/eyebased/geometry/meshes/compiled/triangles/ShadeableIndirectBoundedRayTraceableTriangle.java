package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingProcessor;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.AABBBuilder;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.ShadeableRayTraceable;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class ShadeableIndirectBoundedRayTraceableTriangle extends AbstractIndirectBoundedRayTraceableTriangle implements ShadeableRayTraceable {

    public interface Configuration {
        Vector3.Accessible getVertexPosition(int vertexIndex);
        SampleValue shadeTriangle(RayIntersectionContext rayIntersectionContext, ShadingProcessor shadingProcessor, IndexedTriangle triangle);
    }

    protected final Configuration configuration;

    public ShadeableIndirectBoundedRayTraceableTriangle(
            final Configuration configuration, final int vertexIndex0, final int vertexIndex1, final int vertexIndex2
    ) {
        super(
                calculateBounds(configuration, vertexIndex0, vertexIndex1, vertexIndex2),
                vertexIndex0, vertexIndex1, vertexIndex2
        );

        this.configuration = configuration;
    }

    private static AABB calculateBounds(final Configuration configuration, final int i0, final int i1, final int i2) {
        Objects.requireNonNull(configuration, "Configuration not provided");

        final Vector3.Accessible v0 = configuration.getVertexPosition(i0);
        final Vector3.Accessible v1 = configuration.getVertexPosition(i1);
        final Vector3.Accessible v2 = configuration.getVertexPosition(i2);

        if (VecMathExtended.equal(v0, v1) || VecMathExtended.equal(v1, v2) || VecMathExtended.equal(v2, v0)) {
            throw new DegenerateTriangleException(String.format("Degenerate triangle: [%d, %d, %d]", i0, i1, i2));
        }

        return new AABBBuilder()
                .addPoint(v0).addPoint(v1).addPoint(v2)
                .build(TraceableAABB::new);
    }

    @Override
    public SampleValue shade(final RayIntersectionContext rayIntersectionContext, final ShadingProcessor shadingProcessor) {
        return configuration.shadeTriangle(rayIntersectionContext, shadingProcessor, this);
    }

    @Override
    protected boolean isNotTraceable(final double determinant) {
        return determinant <= 0.0; // TODO: support double sided and flipped meshes ?
    }

    @Override
    protected void processRayInteraction(final RayTracedState rayTracedState, final Ray tracingRay, final double intersectionDistance) {
        rayTracedState.registerRayInteraction(intersectionDistance, this);
    }

    @Override
    protected Vector3.Accessible getVertexPosition(final int vertexIndex) {
        return configuration.getVertexPosition(vertexIndex);
    }

}
