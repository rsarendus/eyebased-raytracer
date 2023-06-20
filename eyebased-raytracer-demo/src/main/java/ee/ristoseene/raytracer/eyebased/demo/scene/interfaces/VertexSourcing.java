package ee.ristoseene.raytracer.eyebased.demo.scene.interfaces;

public interface VertexSourcing {

    enum VertexSourceMode {
        RAW_DEFERRED, COMPILED_DEFERRED, COMPILED_PRE_TRANSFORMED;
    }

    VertexSourceMode getVertexSourceMode();
    void setVertexSourceMode(VertexSourceMode mode);

}
