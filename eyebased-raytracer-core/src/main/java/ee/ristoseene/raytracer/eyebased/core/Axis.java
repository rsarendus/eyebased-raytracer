package ee.ristoseene.raytracer.eyebased.core;

import ee.ristoseene.vecmath.Vector3;

public enum Axis implements Vector3.Accessible {

    POSITIVE_X(+1.0, 0.0, 0.0),
    NEGATIVE_X(-1.0, 0.0, 0.0),

    POSITIVE_Y(0.0, +1.0, 0.0),
    NEGATIVE_Y(0.0, -1.0, 0.0),

    POSITIVE_Z(0.0, 0.0, +1.0),
    NEGATIVE_Z(0.0, 0.0, -1.0);

    private final double x;
    private final double y;
    private final double z;

    Axis(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

}
