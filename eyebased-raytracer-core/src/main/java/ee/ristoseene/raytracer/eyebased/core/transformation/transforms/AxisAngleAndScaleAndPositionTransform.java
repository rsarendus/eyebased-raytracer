package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix3x3;
import ee.ristoseene.vecmath.mutable.MutableMatrix4x4;

public class AxisAngleAndScaleAndPositionTransform extends AbstractScalingPositionTransform {

    private Vector3.Accessible axis;
    private double angle;

    public void setAxisAngle(final Vector3.Accessible axis, final double angle) {
        this.axis = axis;
        this.angle = angle;
    }

    public AxisAngleAndScaleAndPositionTransform withAxisAngle(final Vector3.Accessible axis, final double angle) {
        setAxisAngle(axis, angle);
        return this;
    }

    public Vector3.Accessible getAxis() {
        return axis;
    }

    public void setAxis(final Vector3.Accessible axis) {
        this.axis = axis;
    }

    public AxisAngleAndScaleAndPositionTransform withAxis(final Vector3.Accessible axis) {
        setAxis(axis);
        return this;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(final double angle) {
        this.angle = angle;
    }

    public AxisAngleAndScaleAndPositionTransform withAngle(final double angle) {
        setAngle(angle);
        return this;
    }

    @Override
    public AxisAngleAndScaleAndPositionTransform withScale(final Vector3.Accessible scale) {
        return (AxisAngleAndScaleAndPositionTransform) super.withScale(scale);
    }

    @Override
    public AxisAngleAndScaleAndPositionTransform withPosition(final Vector3.Accessible position) {
        return (AxisAngleAndScaleAndPositionTransform) super.withPosition(position);
    }

    @Override
    public AxisAngleAndScaleAndPositionTransform withParentTransform(final CompilableTransform parentTransform) {
        return (AxisAngleAndScaleAndPositionTransform) super.withParentTransform(parentTransform);
    }

    @Override
    public AxisAngleAndScaleAndPositionTransform clone() {
        return (AxisAngleAndScaleAndPositionTransform) super.clone();
    }

    @Override
    protected CompiledTransform createCompiledTransform(final CompiledTransform parent) {
        if (axis == null && scale == null && position == null) {
            return parent;
        }

        final MutableMatrix4x4 transform = new MutableMatrix4x4(1.0, 1.0, 1.0, 1.0);

        if (axis != null) {
            VecMath.toRotation(transform::XYZxyz, axis, angle);
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
