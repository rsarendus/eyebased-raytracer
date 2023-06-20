package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.stream.Stream;

public class BufferBackedVertexAttributeSource implements VertexAttributeSource {

    private final ByteBuffer backingBuffer;
    private final AttributeBinding<?>[] bindings;
    private final int indexLimit;

    public BufferBackedVertexAttributeSource(final ByteBuffer backingBuffer, final AttributeBinding<?>... bindings) {
        this.backingBuffer = Objects.requireNonNull(backingBuffer, "Backing buffer not provided");
        this.bindings = Objects.requireNonNull(bindings, "Bindings not provided");

        int limit = Integer.MAX_VALUE;

        for (final AttributeBinding<?> binding : bindings) {
            final int usableBufferArea = backingBuffer.capacity() - binding.getOffset();
            int numberOfVertices = usableBufferArea / binding.getStride();
            if (usableBufferArea % binding.getStride() >= binding.getLoader().getAttributeSizeInBytes()) {
                numberOfVertices += 1;
            }
            limit = Math.min(numberOfVertices, limit);
        }

        this.indexLimit = (limit != Integer.MAX_VALUE) ? limit : 0;
    }

    @Override
    public Stream<TypedAttribute<?>> getAttributeKeys() {
        return Stream.of(bindings).map(AttributeBinding::getType);
    }

    @Override
    public boolean isValidIndex(final int vertexIndex) {
        return vertexIndex >= 0 & vertexIndex < indexLimit;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttributeValue(final TypedAttribute<T> key, final int vertexIndex) {
        for (final AttributeBinding<?> binding : bindings) {
            final AttributeBinding<T> typedBinding = (AttributeBinding<T>) binding;
            final TypedAttribute<T> bindingType = typedBinding.getType();

            if (bindingType.equals(key) || key.getValueType().isInstance(bindingType)) {
                return typedBinding.getLoader().loadAttribute(backingBuffer, typedBinding.getOffset() + typedBinding.getStride() * vertexIndex);
            }
        }

        return key.getDefaultValue();
    }

    public interface AttributeLoader<T> {

        T loadAttribute(ByteBuffer source, int offset);
        int getAttributeSizeInBytes();

    }

    public static class AttributeBinding<T> {

        private final TypedAttribute<T> type;
        private final AttributeLoader<T> loader;
        private final int offset, stride;

        public AttributeBinding(final TypedAttribute<T> type, final int offset, final int stride, final AttributeLoader<T> loader) {
            this.type = Objects.requireNonNull(type, "Type not provided");
            this.loader = Objects.requireNonNull(loader, "Loader not provided");

            if (offset < 0) {
                throw new IllegalArgumentException("Invalid offset: " + offset);
            } else if (stride <= 0) {
                throw new IllegalArgumentException("Invalid stride: " + stride);
            }

            this.offset = offset;
            this.stride = stride;
        }

        public AttributeLoader<T> getLoader() {
            return loader;
        }

        public TypedAttribute<T> getType() {
            return type;
        }

        public int getOffset() {
            return offset;
        }

        public int getStride() {
            return stride;
        }

    }

}
