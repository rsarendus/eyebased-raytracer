package ee.ristoseene.raytracer.eyebased.geometry.vertices.indices;

import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexIndexSource;

import java.util.Objects;

public class ArrayBackedVertexIndexSource implements VertexIndexSource {

    private final int[] indices;

    public ArrayBackedVertexIndexSource(final int... indices) {
        this.indices = Objects.requireNonNull(indices, "Index array not provided");
    }

    @Override
    public int getIndex(final int index) {
        return indices[index];
    }

    @Override
    public int getIndexCount() {
        return indices.length;
    }

}
