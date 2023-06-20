package ee.ristoseene.raytracer.eyebased.geometry.vertices.indices;

import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexIndexSource;

public class RangedVertexIndexSource implements VertexIndexSource {

    private final int startInclusive, endExclusive;

    public RangedVertexIndexSource(final int count) {
        this(0, count);
    }

    public RangedVertexIndexSource(final int startInclusive, final int endExclusive) {
        if (startInclusive < 0) {
            throw new IllegalArgumentException("Range start cannot be negative");
        } else if (endExclusive < startInclusive) {
            throw new IllegalArgumentException("Range end cannot be less than its start");
        }

        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    public int getIndex(final int index) {
        if (index < 0 || index >= getIndexCount()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        return startInclusive + index;
    }

    @Override
    public int getIndexCount() {
        return endExclusive - startInclusive;
    }

}
