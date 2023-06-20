package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Vector3;

import java.util.stream.Stream;

public class CompiledVertexPositionAndNormalSource implements VertexAttributeSource {

    private final float[] positionArray;
    private final float[] normalArray;
    protected final int vertexCount;

    public CompiledVertexPositionAndNormalSource(
            final int vertexCount,
            final CompiledVertexAttributeProvider<Vector3.Accessible> vertexPositionProvider,
            final CompiledVertexAttributeProvider<Vector3.Accessible> vertexNormalProvider
    ) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Negative vertex count");
        }

        this.positionArray = new float[vertexCount * 3];
        this.normalArray = new float[vertexCount * 3];
        this.vertexCount = vertexCount;

        for (int i = 0; i < vertexCount; ++i) {
            final Vector3.Accessible vertexPosition = vertexPositionProvider.getVertexAttribute(i);
            final Vector3.Accessible vertexNormal = vertexNormalProvider.getVertexAttribute(i);

            int offset = i * 3;
            positionArray[offset] = (float) vertexPosition.x();
            normalArray[offset] = (float) vertexNormal.x();

            offset += 1;
            positionArray[offset] = (float) vertexPosition.y();
            normalArray[offset] = (float) vertexNormal.y();

            offset += 1;
            positionArray[offset] = (float) vertexPosition.z();
            normalArray[offset] = (float) vertexNormal.z();
        }
    }

    @Override
    public Stream<TypedAttribute<?>> getAttributeKeys() {
        return Stream.of(VertexAttributes.VERTEX_POSITION, VertexAttributes.VERTEX_NORMAL);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttributeValue(final TypedAttribute<T> key, final int vertexIndex) {
        if (VertexAttributes.VERTEX_POSITION.equals(key)) {
            return (T) getVector3FromFloatArray(positionArray, vertexIndex);
        } else if (VertexAttributes.VERTEX_NORMAL.equals(key)) {
            return (T) getVector3FromFloatArray(normalArray, vertexIndex);
        } else {
            return key.getDefaultValue();
        }
    }

    protected static Vector3.Accessible getVector3FromFloatArray(final float[] array, final int vectorIndex) {
        final int offset = vectorIndex * 3;
        return Factories.FACTORY_CONST_VECTOR3_xyz.create(
                array[offset],
                array[offset + 1],
                array[offset + 2]
        );
    }

    @Override
    public final boolean isValidIndex(final int vertexIndex) {
        return vertexIndex >= 0 & vertexIndex < vertexCount;
    }

}
