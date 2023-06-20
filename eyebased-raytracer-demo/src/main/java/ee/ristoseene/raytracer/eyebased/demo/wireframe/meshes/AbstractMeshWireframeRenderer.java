package ee.ristoseene.raytracer.eyebased.demo.wireframe.meshes;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.TransformableWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.AbstractMesh;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.TriangleMesh;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexIndexSource;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public abstract class AbstractMeshWireframeRenderer<T extends AbstractMesh> implements TransformableWireframeRenderer<T> {

    private final int verticesPerElement;

    protected AbstractMeshWireframeRenderer(final int verticesPerElement) {
        this.verticesPerElement = verticesPerElement;
    }

    protected void renderMesh(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final TriangleMesh mesh) {
        final AbstractMesh.VertexAttributeSourceProvider vertexAttributeSourceProvider = mesh.getVertexAttributeSourceProvider();
        final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor = mesh.getVertexPositionExtractor();
        final VertexIndexSource vertexIndexSource = mesh.getVertexIndexSource();

        if (vertexAttributeSourceProvider == null || vertexPositionExtractor == null || vertexIndexSource == null) {
            return;
        }

        final CompiledTransform transform = TransformableWireframeRenderer.getTransform(mesh);
        final VertexAttributeSource vertexAttributeSource = vertexAttributeSourceProvider.getVertexAttributeSource(transform);

        if (vertexAttributeSource == null) {
            return;
        }

        final int elementCount = vertexIndexSource.getIndexCount() / verticesPerElement;

        for (int e = 0; e < elementCount; ++e) {
            int index = e * verticesPerElement;

            Vector4.Accessible v0, v1 = VecMath.transformPosition(projection,
                    vertexPositionExtractor.extract(vertexAttributeSource, vertexIndexSource.getIndex(index), transform),
                    Factories.FACTORY_CONST_VECTOR4_xyzw
            );

            for (int v = 1; v < verticesPerElement; ++v) {
                index += 1;
                v0 = v1;

                v1 = VecMath.transformPosition(projection,
                        vertexPositionExtractor.extract(vertexAttributeSource, vertexIndexSource.getIndex(index), transform),
                        Factories.FACTORY_CONST_VECTOR4_xyzw
                );

                rasterizer.rasterizeLine(v0, v1);
            }
        }
    }

}
