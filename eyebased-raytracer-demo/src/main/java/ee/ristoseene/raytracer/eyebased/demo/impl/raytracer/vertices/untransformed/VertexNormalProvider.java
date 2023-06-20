package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed;

import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.CompiledVertexAttributeProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class VertexNormalProvider implements CompiledVertexAttributeProvider<Vector3.Accessible> {

    private final VertexAttributeSource vertexAttributeSource;

    public VertexNormalProvider(final VertexAttributeSource vertexAttributeSource) {
        this.vertexAttributeSource = Objects.requireNonNull(vertexAttributeSource);
    }

    @Override
    public Vector3.Accessible getVertexAttribute(final int vertexIndex) {
        return vertexAttributeSource.getAttributeValue(VertexAttributes.VERTEX_NORMAL, vertexIndex);
    }

}
