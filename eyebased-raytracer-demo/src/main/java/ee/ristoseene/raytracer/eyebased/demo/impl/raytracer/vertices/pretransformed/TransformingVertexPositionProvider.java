package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.VertexPositionProvider;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class TransformingVertexPositionProvider extends VertexPositionProvider {

    private final Matrix4x4.Accessible transformationMatrix;

    public TransformingVertexPositionProvider(
            final VertexAttributeSource vertexAttributeSource,
            final CompiledTransform transform
    ) {
        super(vertexAttributeSource);
        this.transformationMatrix = Objects.requireNonNull(transform);
    }

    @Override
    public Vector3.Accessible getVertexAttribute(final int vertexIndex) {
        return VecMath.transformPosition(
                transformationMatrix,
                super.getVertexAttribute(vertexIndex),
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

}
