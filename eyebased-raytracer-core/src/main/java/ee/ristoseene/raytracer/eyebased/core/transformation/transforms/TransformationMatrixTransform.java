package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.ChainableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;

public class TransformationMatrixTransform extends ChainableTransform {

    private Matrix4x4.Accessible transformationMatrix;

    public Matrix4x4.Accessible getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(final Matrix4x4.Accessible transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }

    public TransformationMatrixTransform withTransformationMatrix(final Matrix4x4.Accessible transformationMatrix) {
        setTransformationMatrix(transformationMatrix);
        return this;
    }

    @Override
    public TransformationMatrixTransform withParentTransform(final CompilableTransform parentTransform) {
        return (TransformationMatrixTransform) super.withParentTransform(parentTransform);
    }

    @Override
    protected CompiledTransform createCompiledTransform(final CompiledTransform parent) {
        if (transformationMatrix != null) {
            return new CompiledTransform(VecMath.multiply(parent, transformationMatrix, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw));
        } else {
            return parent;
        }
    }

}
