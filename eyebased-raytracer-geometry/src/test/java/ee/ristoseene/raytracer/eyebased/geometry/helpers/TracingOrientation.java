package ee.ristoseene.raytracer.eyebased.geometry.helpers;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class TracingOrientation {

    private final double tracingDistance;
    private final Axis tracingDirection;
    private final Axis horizontalAxis;
    private final Axis verticalAxis;

    public TracingOrientation(final Axis tracingDirection, final Axis horizontalAxis, final Axis verticalAxis) {
        this(1.0, tracingDirection, horizontalAxis, verticalAxis);
    }

    public TracingOrientation(final double tracingDistance, final Axis tracingDirection, final Axis horizontalAxis, final Axis verticalAxis) {
        this.tracingDirection = Objects.requireNonNull(tracingDirection);
        this.horizontalAxis = Objects.requireNonNull(horizontalAxis);
        this.verticalAxis = Objects.requireNonNull(verticalAxis);
        this.tracingDistance = tracingDistance;
    }

    public TracingOrientation(final TracingOrientation tracingOrientation) {
        this(tracingOrientation.getTracingDistance(), tracingOrientation.getTracingDirection(), tracingOrientation.getHorizontalAxis(), tracingOrientation.getVerticalAxis());
    }

    public Vector3.Accessible getTracingOrigin(final double x, final double y) {
        return Factories.FACTORY_CONST_VECTOR3_xyz.create(
                -getTracingDistance() * getTracingDirection().x() + getHorizontalAxis().x() * x + getVerticalAxis().x() * y,
                -getTracingDistance() * getTracingDirection().y() + getHorizontalAxis().y() * x + getVerticalAxis().y() * y,
                -getTracingDistance() * getTracingDirection().z() + getHorizontalAxis().z() * x + getVerticalAxis().z() * y
        );
    }

    public double getTracingDistance() {
        return tracingDistance;
    }

    public Axis getTracingDirection() {
        return tracingDirection;
    }

    public Axis getHorizontalAxis() {
        return horizontalAxis;
    }

    public Axis getVerticalAxis() {
        return verticalAxis;
    }

    @Override
    public String toString() {
        return getTracingDirection().name();
    }

}
