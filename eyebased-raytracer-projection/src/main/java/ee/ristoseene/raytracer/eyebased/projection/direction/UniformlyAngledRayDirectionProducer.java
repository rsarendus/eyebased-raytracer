package ee.ristoseene.raytracer.eyebased.projection.direction;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class UniformlyAngledRayDirectionProducer implements RayDirectionProducer {

    private final Orientation orientation;

    private final double halfHorizontalFOV;
    private final double halfVerticalFOV;

    public UniformlyAngledRayDirectionProducer(final Orientation orientation, final double horizontalFOV, final double verticalFOV) {
        this.orientation = Objects.requireNonNull(orientation, "Orientation not provided");

        this.halfHorizontalFOV = horizontalFOV * 0.5;
        this.halfVerticalFOV = verticalFOV * 0.5;
    }

    @Override
    public Vector3.Accessible produceDirection(final double x, final double y) {
        final double horizontalAngle = halfHorizontalFOV * x;
        final double verticalAngle = halfVerticalFOV * y;

        final double cosHorizontalAngle = Math.cos(horizontalAngle);
        final double cosVerticalAngle = Math.cos(verticalAngle);

        final double forward = cosHorizontalAngle * cosVerticalAngle;
        final double right = Math.sin(horizontalAngle) * cosVerticalAngle;
        final double up = Math.sin(verticalAngle) * cosHorizontalAngle;

        return orientation.multiply(forward, right, up, Factories.FACTORY_CONST_VECTOR3_xyz);
    }

}
