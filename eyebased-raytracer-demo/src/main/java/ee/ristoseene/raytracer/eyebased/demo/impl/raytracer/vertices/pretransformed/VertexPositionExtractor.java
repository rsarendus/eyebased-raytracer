package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Vector3;

public class VertexPositionExtractor implements VertexAttributeExtractor<Vector3.Accessible> {

    public static final VertexPositionExtractor INSTANCE = new VertexPositionExtractor();

    protected VertexPositionExtractor() {}

    @Override
    public Vector3.Accessible extract(final VertexAttributeSource source, final int vertexIndex, final CompiledTransform transform) {
        return source.getAttributeValue(VertexAttributes.VERTEX_POSITION, vertexIndex);
    }

}
