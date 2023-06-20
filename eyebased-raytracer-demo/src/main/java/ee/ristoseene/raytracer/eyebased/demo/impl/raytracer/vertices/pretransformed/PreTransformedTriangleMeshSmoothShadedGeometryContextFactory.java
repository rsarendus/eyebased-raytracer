package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.utilities.BarycentricCoordinates;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SimpleGeometryContextImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.IndexedTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Vector3;

public class PreTransformedTriangleMeshSmoothShadedGeometryContextFactory extends PreTransformedTriangleMeshGeometryContextFactory {

    public static final PreTransformedTriangleMeshSmoothShadedGeometryContextFactory INSTANCE = new PreTransformedTriangleMeshSmoothShadedGeometryContextFactory();

    protected static Vector3.Accessible getVertexNormal(
            final VertexAttributeSource vertexAttributeSource, final int vertexIndex
    ) {
        return vertexAttributeSource.getAttributeValue(VertexAttributes.VERTEX_NORMAL, vertexIndex);
    }

    protected static Vector3.Accessible createSurfaceNormal(
            final Vector3.Accessible barycentricCoordinates,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices
    ) {
        return BarycentricCoordinates.resolve(
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex0()),
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex1()),
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex2()),
                barycentricCoordinates,
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz
        );
    }

    protected PreTransformedTriangleMeshSmoothShadedGeometryContextFactory() {}

    @Override
    public GeometryContext create(
            final RayIntersectionContext rayIntersectionContext,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final CompiledTransform transform
    ) {
        final Vector3.Accessible barycentricCoordinates = getBarycentricCoordinates(rayIntersectionContext, vertexAttributeSource, triangleIndices);
        return new SimpleGeometryContextImpl(
                rayIntersectionContext.getRayIntersectionPoint(),
                createSurfaceNormal(barycentricCoordinates, vertexAttributeSource, triangleIndices)
        );
    }

}
