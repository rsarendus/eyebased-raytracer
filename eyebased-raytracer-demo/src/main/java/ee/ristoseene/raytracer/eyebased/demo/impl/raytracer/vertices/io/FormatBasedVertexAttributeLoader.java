package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.io;

import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.BufferBackedVertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.demo.io.Format;

import java.util.Objects;

public abstract class FormatBasedVertexAttributeLoader<T> implements BufferBackedVertexAttributeSource.AttributeLoader<T> {

    protected final Format format;

    protected FormatBasedVertexAttributeLoader(final Format format) {
        this.format = Objects.requireNonNull(format, "Format not provided");
    }

    @Override
    public int getAttributeSizeInBytes() {
        return format.getSizeInBytes();
    }

}
