package ee.ristoseene.raytracer.eyebased.core.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface SampleValueFactory {

    SampleValue create(ShadingContext shadingContext, double red, double green, double blue, double alpha);

    default SampleValue create(final ShadingContext shadingContext, final Vector3.Accessible rgb, final double alpha) {
        return create(shadingContext, rgb.x(), rgb.y(), rgb.z(), alpha);
    }

    default SampleValue create(final ShadingContext shadingContext, final Vector3.Accessible rgb) {
        return create(shadingContext, rgb.x(), rgb.y(), rgb.z(), 1.0);
    }

    default SampleValue create(final ShadingContext shadingContext, final double alpha) {
        return create(shadingContext, 0.0, 0.0, 0.0, alpha);
    }

    SampleValueFactory NO_OP_INSTANCE = (context, r, g, b, a) -> SampleValue.NO_OP_INSTANCE;

    ShadingContext.Attribute<SampleValueFactory> KEY = new ShadingContext.Attribute<>() {

        @Override
        public Class<SampleValueFactory> getValueType() {
            return SampleValueFactory.class;
        }

        @Override
        public SampleValueFactory getDefaultValue() {
            return NO_OP_INSTANCE;
        }

    };

}
