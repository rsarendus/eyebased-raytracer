package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.utilities.NormalMatrixExtraction;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.VertexNormalProvider;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class TransformingVertexNormalProvider extends VertexNormalProvider {

    private final Matrix3x3.Accessible normalMatrix;

    public TransformingVertexNormalProvider(
            final VertexAttributeSource vertexAttributeSource,
            final CompiledTransform transform
    ) {
        super(vertexAttributeSource);
        this.normalMatrix = NormalMatrixExtraction.getNormalMatrixFromInverse(
                Objects.requireNonNull(transform).getInverseTransform(),
                Factories.FACTORY_CONST_MATRIX3x3_XYZxyz
        );
    }

    @Override
    public Vector3.Accessible getVertexAttribute(final int vertexIndex) {
        return VecMath.multiply(
                normalMatrix,
                super.getVertexAttribute(vertexIndex),
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz
        );
    }

}
