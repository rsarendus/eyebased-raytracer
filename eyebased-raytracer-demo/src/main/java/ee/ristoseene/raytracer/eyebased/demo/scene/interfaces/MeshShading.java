package ee.ristoseene.raytracer.eyebased.demo.scene.interfaces;

public interface MeshShading {

    enum MeshShadingMode {
        FLAT_SHADED, SMOOTH_SHADED;
    }

    MeshShadingMode getMeshShadingMode();
    void setMeshShadingMode(MeshShadingMode mode);

}
