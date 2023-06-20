package ee.ristoseene.raytracer.eyebased.demo.wireframe.meshes;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.TriangleMesh;
import ee.ristoseene.vecmath.Matrix4x4;

public final class TriangleMeshWireframeRenderer extends AbstractMeshWireframeRenderer<TriangleMesh> {

    public TriangleMeshWireframeRenderer() {
        super(3);
    }

    @Override
    public Class<TriangleMesh> getType() {
        return TriangleMesh.class;
    }

    @Override
    public void render(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final TriangleMesh mesh) {
        renderMesh(rasterizer, projection, mesh);
    }

}
