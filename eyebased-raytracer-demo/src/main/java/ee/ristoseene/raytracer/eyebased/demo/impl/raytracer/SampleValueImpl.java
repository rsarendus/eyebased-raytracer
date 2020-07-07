package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

public class SampleValueImpl extends ImmutableVector3 implements SampleValue {

    private final double depth;

    public SampleValueImpl(final Vector3.Accessible rgb, final double depth) {
        super(rgb);
        this.depth = depth;
    }

    public SampleValueImpl(final double red, final double green, final double blue, final double depth) {
        super(red, green, blue);
        this.depth = depth;
    }

    @Override
    public Vector3.Accessible getRGB() {
        return this;
    }

    @Override
    public double getDepth() {
        return depth;
    }

    @Override
    public SampleValue multiplied(double red, double green, double blue, double alpha) {
        return new SampleValueImpl(x() * red, y() * green, z() * blue, depth);
    }

    @Override
    public SampleValue multiplied(final Vector3.Accessible rgb, final double alpha) {
        return new SampleValueImpl(x() * rgb.x(), y() * rgb.y(), z() * rgb.z(), depth);
    }

    @Override
    public SampleValue multiplied(double red, double green, double blue) {
        return new SampleValueImpl(x() * red, y() * green, z() * blue, depth);
    }

    @Override
    public SampleValue multiplied(final Vector3.Accessible rgb) {
        return new SampleValueImpl(x() * rgb.x(), y() * rgb.y(), z() * rgb.z(), depth);
    }

    @Override
    public SampleValue multiplied(final double alpha) {
        return this;
    }

}
