package ee.ristoseene.raytracer.eyebased.shading.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

public interface WeightedShadingFilter {

    boolean test(double multiplier);

    WeightedShadingFilter DEFAULT_INSTANCE = multiplier -> multiplier != 0.0;

    TypedAttribute<WeightedShadingFilter> KEY = new TypedAttribute<>() {

        @Override
        public Class<WeightedShadingFilter> getValueType() {
            return WeightedShadingFilter.class;
        }

        @Override
        public WeightedShadingFilter getDefaultValue() {
            return DEFAULT_INSTANCE;
        }

    };

}
