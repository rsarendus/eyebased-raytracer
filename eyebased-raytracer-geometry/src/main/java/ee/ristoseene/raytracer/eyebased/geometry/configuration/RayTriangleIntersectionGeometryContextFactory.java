package ee.ristoseene.raytracer.eyebased.geometry.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.IndexedTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;

@FunctionalInterface
public interface RayTriangleIntersectionGeometryContextFactory {

    RayTriangleIntersectionGeometryContextFactory NO_OP_INSTANCE = (
            rayIntersectionContext, vertexAttributeSource, triangleIndices, transform
    ) -> GeometryContext.NO_OP_INSTANCE;

    GeometryContext create(
            RayIntersectionContext rayIntersectionContext,
            VertexAttributeSource vertexAttributeSource,
            IndexedTriangle triangleIndices,
            CompiledTransform transform
    );

}
