package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix3x3;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.mutable.MutableMatrix4x4;

public class RotationMatrixAndPositionTransform extends AbstractPositionTransform {

    private Matrix3x3.Accessible rotationMatrix;

    public Matrix3x3.Accessible getRotationMatrix() {
        return rotationMatrix;
    }

    public void setRotationMatrix(final Matrix3x3.Accessible rotationMatrix) {
        this.rotationMatrix = rotationMatrix;
    }

    public RotationMatrixAndPositionTransform withRotationMatrix(final Matrix3x3.Accessible rotationMatrix) {
        setRotationMatrix(rotationMatrix);
        return this;
    }

    @Override
    public RotationMatrixAndPositionTransform withPosition(final Vector3.Accessible position) {
        return (RotationMatrixAndPositionTransform) super.withPosition(position);
    }

    @Override
    public RotationMatrixAndPositionTransform withParentTransform(final CompilableTransform parentTransform) {
        return (RotationMatrixAndPositionTransform) super.withParentTransform(parentTransform);
    }

    @Override
    protected CompiledTransform createCompiledTransform(final CompiledTransform parent) {
        if (rotationMatrix == null && position == null) {
            return parent;
        }

        final MutableMatrix4x4 transform = new MutableMatrix4x4(1.0, 1.0, 1.0, 1.0);

        if (rotationMatrix != null) {
            transform.XYZxyz(rotationMatrix);
        }
        if (position != null) {
            transform.Txyz(position);
        }

        VecMath.multiply(transform, parent, transform);
        return new CompiledTransform(transform);
    }

}
