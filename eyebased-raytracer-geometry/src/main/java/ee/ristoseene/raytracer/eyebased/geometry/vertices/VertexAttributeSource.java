package ee.ristoseene.raytracer.eyebased.geometry.vertices;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

import java.util.stream.Stream;

public interface VertexAttributeSource extends CompiledObject {

    Stream<TypedAttribute<?>> getAttributeKeys();
    boolean isValidIndex(int vertexIndex);

    default <T> T getAttributeValue(final TypedAttribute<T> key, final int vertexIndex) {
        return key.getDefaultValue();
    }

}
