package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.utilities.BarycentricCoordinates;
import ee.ristoseene.raytracer.eyebased.core.utilities.NormalMatrixExtraction;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SimpleGeometryContextImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.IndexedTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class DeferredTriangleMeshSmoothShadedGeometryContextFactory extends DeferredTriangleMeshGeometryContextFactory {

    public static final DeferredTriangleMeshSmoothShadedGeometryContextFactory INSTANCE = new DeferredTriangleMeshSmoothShadedGeometryContextFactory();

    protected static Vector3.Accessible getVertexNormal(
            final VertexAttributeSource vertexAttributeSource, final int vertexIndex
    ) {
        return vertexAttributeSource.getAttributeValue(VertexAttributes.VERTEX_NORMAL, vertexIndex);
    }

    protected static Vector3.Accessible createSurfaceNormal(
            final Vector3.Accessible barycentricCoordinates,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final Matrix3x3.Accessible normalMatrix
    ) {
        final Vector3.Accessible resolvedNormal = BarycentricCoordinates.resolve(
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex0()),
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex1()),
                getVertexNormal(vertexAttributeSource, triangleIndices.getVertexIndex2()),
                barycentricCoordinates,
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
        return VecMath.multiply(
                normalMatrix, resolvedNormal, Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz
        );
    }

    protected DeferredTriangleMeshSmoothShadedGeometryContextFactory() {}

    @Override
    public GeometryContext create(
            final RayIntersectionContext rayIntersectionContext,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final CompiledTransform transform
    ) {
        final Vector3.Accessible barycentricCoordinates = getBarycentricCoordinates(rayIntersectionContext, vertexAttributeSource, triangleIndices, transform);
        final Matrix3x3.Accessible normalMatrix = NormalMatrixExtraction.getNormalMatrixFromInverse(transform.getInverseTransform(), Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
        return new SimpleGeometryContextImpl(
                rayIntersectionContext.getRayIntersectionPoint(),
                createSurfaceNormal(barycentricCoordinates, vertexAttributeSource, triangleIndices, normalMatrix)
        );
    }

}
