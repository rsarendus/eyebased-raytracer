package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.utilities.BarycentricCoordinates;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RayTriangleIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.IndexedTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public abstract class DeferredTriangleMeshGeometryContextFactory implements RayTriangleIntersectionGeometryContextFactory {

    protected static Vector3.Accessible getBarycentricCoordinates(
            final RayIntersectionContext rayIntersectionContext,
            final VertexAttributeSource vertexAttributeSource,
            final IndexedTriangle triangleIndices,
            final CompiledTransform transform
    ) {
        return BarycentricCoordinates.calculateCoordinatesFromPointInTriangle(
                VecMath.transformPosition(transform.getInverseTransform(), rayIntersectionContext.getRayIntersectionPoint(), Factories.FACTORY_CONST_VECTOR3_xyz),
                getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex0()),
                getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex1()),
                getVertexPosition(vertexAttributeSource, triangleIndices.getVertexIndex2()),
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

    protected static Vector3.Accessible getVertexPosition(
            final VertexAttributeSource vertexAttributeSource, final int vertexIndex
    ) {
        return vertexAttributeSource.getAttributeValue(VertexAttributes.VERTEX_POSITION, vertexIndex);
    }

    protected DeferredTriangleMeshGeometryContextFactory() {}

}
