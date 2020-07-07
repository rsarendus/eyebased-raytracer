package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class OrientedVectorBuilder {

    private final Orientation orientation;

    private double forwardMultiplier;
    private double rightMultiplier;
    private double upMultiplier;

    private OrientedVectorBuilder(final Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public double getForwardMultiplier() {
        return forwardMultiplier;
    }

    public void setForwardMultiplier(final double forwardMultiplier) {
        this.forwardMultiplier = forwardMultiplier;
    }

    public OrientedVectorBuilder withForwardMultiplier(final double forwardMultiplier) {
        setForwardMultiplier(forwardMultiplier);
        return this;
    }

    public double getRightMultiplier() {
        return rightMultiplier;
    }

    public void setRightMultiplier(final double rightMultiplier) {
        this.rightMultiplier = rightMultiplier;
    }

    public OrientedVectorBuilder withRightMultiplier(final double rightMultiplier) {
        setRightMultiplier(rightMultiplier);
        return this;
    }

    public double getUpMultiplier() {
        return upMultiplier;
    }

    public void setUpMultiplier(final double upMultiplier) {
        this.upMultiplier = upMultiplier;
    }

    public OrientedVectorBuilder withUpMultiplier(final double upMultiplier) {
        setUpMultiplier(upMultiplier);
        return this;
    }

    public <R extends Vector3.Accessible> R build(final Vector3.Factory<R> resultFactory) {
        return orientation.multiply(forwardMultiplier, rightMultiplier, upMultiplier, resultFactory);
    }

    public static OrientedVectorBuilder positionBuilder(final Orientation orientation) {
        return new OrientedVectorBuilder(Objects.requireNonNull(orientation))
                .withForwardMultiplier(0.0)
                .withRightMultiplier(0.0)
                .withUpMultiplier(0.0);
    }

    public static OrientedVectorBuilder scaleBuilder(final Orientation orientation) {
        return new OrientedVectorBuilder(Objects.requireNonNull(orientation))
                .withForwardMultiplier(1.0)
                .withRightMultiplier(1.0)
                .withUpMultiplier(1.0);
    }

    public static OrientedVectorBuilder positionBuilder() {
        return positionBuilder(Constants.ORIENTATION);
    }

    public static OrientedVectorBuilder scaleBuilder() {
        return scaleBuilder(Constants.ORIENTATION);
    }

}
