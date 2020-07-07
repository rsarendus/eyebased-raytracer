package ee.ristoseene.raytracer.eyebased.demo.scene.camera;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.scene.Projection;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.raytracer.eyebased.projection.direction.UniformlyAngledRayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.origin.ConstantOffsetRayOriginProducer;
import ee.ristoseene.vecmath.Matrix4x4;

public class PerspectiveProjection implements Projection {

    private double fov;

    public PerspectiveProjection() {
        fov = 60.0 * Math.PI / 180.0;
    }

    @Override
    public double getFov() {
        return fov;
    }

    @Override
    public void setFov(final double fov) {
        this.fov = fov;
    }

    @Override
    public RayOriginProducer createRayOriginProducer(final int width, final int height) {
        return new ConstantOffsetRayOriginProducer(Vectors.VECTOR3_ZERO_ZERO_ZERO);
    }

    @Override
    public RayDirectionProducer createRayDirectionProducer(final int width, final int height) {
        final double multiplier = fov / Math.min(width, height);
        final double horizontalFOV = width * multiplier;
        final double verticalFOV = height * multiplier;

        return new UniformlyAngledRayDirectionProducer(Constants.ORIENTATION, horizontalFOV, verticalFOV);
    }

    @Override
    public Matrix4x4.Accessible createProjectionMatrix(final int width, final int height) {
        final double multiplier = Math.tan(fov * 0.5) / Math.min(width, height);

        return Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw.create(
                1.0 / (width * multiplier),
                0.0,
                0.0,
                0.0,
                0.0,
                1.0 / (height * multiplier),
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                -1.0,
                0.0,
                0.0,
                -1.0,
                0.0
        );
    }
}
