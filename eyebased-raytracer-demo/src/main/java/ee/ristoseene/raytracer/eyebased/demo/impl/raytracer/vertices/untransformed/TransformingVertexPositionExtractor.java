package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.VertexPositionExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public class TransformingVertexPositionExtractor extends VertexPositionExtractor {

    public static final TransformingVertexPositionExtractor INSTANCE = new TransformingVertexPositionExtractor();

    protected TransformingVertexPositionExtractor() {}

    @Override
    public Vector3.Accessible extract(final VertexAttributeSource source, final int vertexIndex, final CompiledTransform transform) {
        return VecMath.transformPosition(
                transform,
                super.extract(source, vertexIndex, null),
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

}
