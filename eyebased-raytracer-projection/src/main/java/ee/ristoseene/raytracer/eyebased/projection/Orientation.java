package ee.ristoseene.raytracer.eyebased.projection;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class Orientation implements CompiledObject {

    private final Axis forwardAxis;
    private final Axis rightAxis;
    private final Axis upAxis;

    private Orientation(final Builder builder) {
        forwardAxis = builder.getForwardAxis();
        rightAxis = builder.getRightAxis();
        upAxis = builder.getUpAxis();
    }

    public Axis getForwardAxis() {
        return forwardAxis;
    }

    public Axis getRightAxis() {
        return rightAxis;
    }

    public Axis getUpAxis() {
        return upAxis;
    }

    public static final class Builder {

        private Axis forwardAxis;
        private Axis rightAxis;
        private Axis upAxis;

        public Axis getForwardAxis() {
            return forwardAxis;
        }

        public void setForwardAxis(final Axis forwardAxis) {
            this.forwardAxis = forwardAxis;
        }

        public Builder withForwardAxis(final Axis forwardAxis) {
            setForwardAxis(forwardAxis);
            return this;
        }

        public Axis getRightAxis() {
            return rightAxis;
        }

        public void setRightAxis(final Axis rightAxis) {
            this.rightAxis = rightAxis;
        }

        public Builder withRightAxis(final Axis rightAxis) {
            setRightAxis(rightAxis);
            return this;
        }

        public Axis getUpAxis() {
            return upAxis;
        }

        public void setUpAxis(final Axis upAxis) {
            this.upAxis = upAxis;
        }

        public Builder withUpAxis(final Axis upAxis) {
            setUpAxis(upAxis);
            return this;
        }

        public Orientation build() {
            Objects.requireNonNull(forwardAxis, "Forward axis not provided");
            Objects.requireNonNull(rightAxis, "Right axis not provided");
            Objects.requireNonNull(upAxis, "Up axis not provided");

            validateAxesCombination(forwardAxis, rightAxis);
            validateAxesCombination(forwardAxis, upAxis);
            validateAxesCombination(rightAxis, upAxis);

            return new Orientation(this);
        }

    }

    private static void validateAxesCombination(final Axis axis1, final Axis axis2) {
        switch (axis1) {
            case POSITIVE_X:
            case NEGATIVE_X:
                if (axis2 == Axis.POSITIVE_X || axis2 == Axis.NEGATIVE_X) {
                    throwIllegalAxesCombinationException(axis1, axis2);
                }
                break;
            case POSITIVE_Y:
            case NEGATIVE_Y:
                if (axis2 == Axis.POSITIVE_Y || axis2 == Axis.NEGATIVE_Y) {
                    throwIllegalAxesCombinationException(axis1, axis2);
                }
                break;
            case POSITIVE_Z:
            case NEGATIVE_Z:
                if (axis2 == Axis.POSITIVE_Z || axis2 == Axis.NEGATIVE_Z) {
                    throwIllegalAxesCombinationException(axis1, axis2);
                }
                break;
        }
    }

    private static void throwIllegalAxesCombinationException(final Axis axis1, final Axis axis2) {
        throw new IllegalStateException("Illegal combination of perpendicular axes: " +
                axis1.name() + " and " + axis2.name());
    }

    public static Builder builder() {
        return new Builder();
    }

    public <R> R multiply(final double forward, final double right, final double up, final Vector3.Factory<R> resultFactory) {
        return resultFactory.create(
                forwardAxis.x() * forward + rightAxis.x() * right + upAxis.x() * up,
                forwardAxis.y() * forward + rightAxis.y() * right + upAxis.y() * up,
                forwardAxis.z() * forward + rightAxis.z() * right + upAxis.z() * up
        );
    }

    public void multiply(final Vector3.Consumer resultConsumer, final double forward, final double right, final double up) {
        resultConsumer.xyz(
                forwardAxis.x() * forward + rightAxis.x() * right + upAxis.x() * up,
                forwardAxis.y() * forward + rightAxis.y() * right + upAxis.y() * up,
                forwardAxis.z() * forward + rightAxis.z() * right + upAxis.z() * up
        );
    }

}
