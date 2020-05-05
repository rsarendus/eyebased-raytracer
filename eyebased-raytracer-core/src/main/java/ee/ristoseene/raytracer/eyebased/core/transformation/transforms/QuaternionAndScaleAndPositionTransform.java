package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.mutable.MutableMatrix4x4;

public class QuaternionAndScaleAndPositionTransform extends AbstractScalingPositionTransform {

    private Quaternion.Accessible quaternion;

    public Quaternion.Accessible getQuaternion() {
        return quaternion;
    }

    public void setQuaternion(final Quaternion.Accessible quaternion) {
        this.quaternion = quaternion;
    }

    public QuaternionAndScaleAndPositionTransform withQuaternion(final Quaternion.Accessible quaternion) {
        setQuaternion(quaternion);
        return this;
    }

    @Override
    public QuaternionAndScaleAndPositionTransform withScale(final Vector3.Accessible scale) {
        return (QuaternionAndScaleAndPositionTransform) super.withScale(scale);
    }

    @Override
    public QuaternionAndScaleAndPositionTransform withPosition(final Vector3.Accessible position) {
        return (QuaternionAndScaleAndPositionTransform) super.withPosition(position);
    }

    @Override
    public QuaternionAndScaleAndPositionTransform withParentTransform(final CompilableTransform parentTransform) {
        return (QuaternionAndScaleAndPositionTransform) super.withParentTransform(parentTransform);
    }

    @Override
    public QuaternionAndScaleAndPositionTransform clone() {
        return (QuaternionAndScaleAndPositionTransform) super.clone();
    }

    @Override
    protected CompiledTransform createCompiledTransform(final CompiledTransform parent) {
        if (quaternion == null && scale == null && position == null) {
            return parent;
        }

        final MutableMatrix4x4 transform = new MutableMatrix4x4(1.0, 1.0, 1.0, 1.0);

        if (quaternion != null) {
            VecMath.toRotation(transform::XYZxyz, quaternion);
        }
        if (scale != null) {
            VecMath.multiply(transform::XYZxyz, new ImmutableMatrix3x3(scale), transform.const$XYZxyz());
        }
        if (position != null) {
            transform.Txyz(position);
        }

        VecMath.multiply(transform, parent, transform);
        return new CompiledTransform(transform);
    }

}
