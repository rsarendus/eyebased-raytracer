package ee.ristoseene.raytracer.eyebased.projection.direction;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class ForwardAxisRayDirectionProducer implements RayDirectionProducer {

    private final Axis forwardAxis;

    public ForwardAxisRayDirectionProducer(final Axis forwardAxis) {
        this.forwardAxis = Objects.requireNonNull(forwardAxis, "Forward axis not provided");
    }

    @Override
    public Vector3.Accessible produceDirection(final double x, final double y) {
        return forwardAxis;
    }

}
