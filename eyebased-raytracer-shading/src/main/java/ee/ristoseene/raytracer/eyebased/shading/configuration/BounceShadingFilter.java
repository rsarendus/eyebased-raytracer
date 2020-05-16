package ee.ristoseene.raytracer.eyebased.shading.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface BounceShadingFilter {

    boolean test(BounceContext bounceContext, double redMultiplier, double greenMultiplier, double blueMultiplier);

    default boolean test(final BounceContext bounceContext, final Vector3.Accessible colorMultiplier) {
        return test(bounceContext, colorMultiplier.x(), colorMultiplier.y(), colorMultiplier.z());
    }

    BounceShadingFilter DEFAULT_INSTANCE = (bounceContext, r, g, b) -> bounceContext
            .getBounce() == 0 && r != 0.0 && g != 0.0 && b != 0.0;

    TypedAttribute<BounceShadingFilter> KEY = new TypedAttribute<>() {

        @Override
        public Class<BounceShadingFilter> getValueType() {
            return BounceShadingFilter.class;
        }

        @Override
        public BounceShadingFilter getDefaultValue() {
            return DEFAULT_INSTANCE;
        }

    };

}
