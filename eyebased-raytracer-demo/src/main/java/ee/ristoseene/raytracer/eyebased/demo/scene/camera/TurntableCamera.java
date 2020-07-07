package ee.ristoseene.raytracer.eyebased.demo.scene.camera;

import ee.ristoseene.raytracer.eyebased.core.transformation.ChainableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.transforms.QuaternionAndScaleAndPositionTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.scene.Camera;
import ee.ristoseene.raytracer.eyebased.demo.scene.Projection;
import ee.ristoseene.vecmath.Quaternion;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.mutable.MutableQuaternion;
import ee.ristoseene.vecmath.mutable.MutableVector3;

import java.util.Objects;

public class TurntableCamera extends Camera {

    private final Quaternion.AccessibleAndMutable rotation;
    private final Vector3.AccessibleAndMutable zoomVector;
    private double zoom;

    public TurntableCamera(final Vector3.Accessible origin) {
        Objects.requireNonNull(origin);

        rotation = new MutableQuaternion();
        VecMath.toRotation(rotation, Constants.ORIENTATION.getUpAxis(), 0.0);

        zoom = 1.0;
        zoomVector = new MutableVector3();
        VecMath.multiply(zoomVector, Constants.ORIENTATION.getForwardAxis(), -zoom);

        final ChainableTransform baseTransform = new QuaternionAndScaleAndPositionTransform()
                .withQuaternion(rotation)
                .withPosition(origin);

        final ChainableTransform zoomTransform = new QuaternionAndScaleAndPositionTransform()
                .withParentTransform(baseTransform)
                .withPosition(zoomVector);

        super.transformableEye.setParentTransform(zoomTransform);
    }

    @Override
    public TurntableCamera withProjection(final Projection projection) {
        return (TurntableCamera) super.withProjection(projection);
    }

    @Override
    public void rotate(final double x, final double y) {
        final Quaternion.AccessibleAndMutable quaternion = new MutableQuaternion();
        VecMath.toRotation(quaternion, Constants.ORIENTATION.getUpAxis(), x);
        VecMath.multiply(rotation, quaternion, rotation);

        final Vector3.AccessibleAndMutable rightAxis = new MutableVector3();
        VecMath.transformVector(rightAxis, rotation, Constants.ORIENTATION.getRightAxis());
        VecMath.toRotation(quaternion, rightAxis, y);
        VecMath.multiply(rotation, quaternion, rotation);

        VecMath.normalize(rotation, rotation);
    }

    @Override
    public void zoom(final double z, final boolean alt) {
        if (alt) {
            this.projection.setFov(this.projection.getFov() + z);
        } else {
            this.zoom = VecMath.max(this.zoom + (this.zoom * z), 0.001);
            VecMath.multiply(zoomVector, Constants.ORIENTATION.getForwardAxis(), -zoom);
        }
    }

}
