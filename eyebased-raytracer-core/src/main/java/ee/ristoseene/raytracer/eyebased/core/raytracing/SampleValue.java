package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.vecmath.Vector3;

public interface SampleValue {

    SampleValue NO_OP_INSTANCE = (r, g, b, a) -> SampleValue.NO_OP_INSTANCE;

    default Vector3.Accessible getRGB() {
        return Vectors.VECTOR3_ZERO_ZERO_ZERO;
    }

    default double getAlpha() {
        return 1.0;
    }

    default double getDepth() {
        return Double.NaN;
    }

    default <T> T getAttributeValue(final TypedAttribute<T> key) {
        return key.getDefaultValue();
    }

    SampleValue multiplied(double red, double green, double blue, double alpha);

    default SampleValue multiplied(final Vector3.Accessible rgb, final double alpha) {
        return multiplied(rgb.x(), rgb.y(), rgb.z(), alpha);
    }

    default SampleValue multiplied(final double red, final double green, final double blue) {
        return multiplied(red, green, blue, 1.0);
    }

    default SampleValue multiplied(final Vector3.Accessible rgb) {
        return multiplied(rgb.x(), rgb.y(), rgb.z(), 1.0);
    }

    default SampleValue multiplied(final double alpha) {
        return multiplied(1.0, 1.0, 1.0, alpha);
    }

}
