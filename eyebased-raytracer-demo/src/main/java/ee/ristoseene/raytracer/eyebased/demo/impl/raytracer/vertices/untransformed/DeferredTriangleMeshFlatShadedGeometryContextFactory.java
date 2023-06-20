package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.utilities.NormalMatrixExtraction;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SimpleGeometryContextImpl;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.IndexedTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class DeferredTriangleMeshFlatShadedGeometryContextFactory extends DeferredTriangleMeshGeometryContextFactory {

    public static final DeferredTriangleMeshFlatShadedGeometryContextFactory INSTANCE = new DeferredTriangleMeshFlatShadedGeometryContextFactory();

    protected static Vector3.Accessible createFlatSurfaceNormal(
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final Matrix3x3.Accessible normalMatrix
    ) {
        final Vector3.Accessible v0 = getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex0());
        final Vector3.Accessible v0v1 = VecMath.subtract(
                getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex1()), v0, Factories.FACTORY_CONST_VECTOR3_xyz
        );
        final Vector3.Accessible v0v2 = VecMath.subtract(
                getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex2()), v0, Factories.FACTORY_CONST_VECTOR3_xyz
        );
        final Vector3.Accessible resolvedNormal = VecMath.cross(v0v1, v0v2, Factories.FACTORY_CONST_VECTOR3_xyz);
        return VecMath.multiply(
                normalMatrix, resolvedNormal, Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz
        );
    }

    protected DeferredTriangleMeshFlatShadedGeometryContextFactory() {}

    @Override
    public GeometryContext create(
            final RayIntersectionContext rayIntersectionContext,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final CompiledTransform transform
    ) {
        final Matrix3x3.Accessible normalMatrix = NormalMatrixExtraction.getNormalMatrixFromInverse(transform.getInverseTransform(), Factories.FACTORY_CONST_MATRIX3x3_XYZxyz);
        return new SimpleGeometryContextImpl(
                rayIntersectionContext.getRayIntersectionPoint(),
                createFlatSurfaceNormal(vertexAttributeSource, triangleIndices, normalMatrix)
        );
    }

}
