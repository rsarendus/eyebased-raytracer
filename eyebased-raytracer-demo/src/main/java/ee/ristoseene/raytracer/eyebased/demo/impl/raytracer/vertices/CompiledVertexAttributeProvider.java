package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices;

@FunctionalInterface
public interface CompiledVertexAttributeProvider<T> {
    T getVertexAttribute(int vertexIndex);
}
