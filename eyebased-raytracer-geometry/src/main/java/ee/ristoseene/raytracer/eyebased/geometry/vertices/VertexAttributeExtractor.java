package ee.ristoseene.raytracer.eyebased.geometry.vertices;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;

@FunctionalInterface
public interface VertexAttributeExtractor<T> {

    T extract(VertexAttributeSource source, int vertexIndex, CompiledTransform transform);

}
