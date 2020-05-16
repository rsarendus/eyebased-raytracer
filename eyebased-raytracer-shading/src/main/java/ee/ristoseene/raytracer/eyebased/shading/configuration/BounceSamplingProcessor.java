package ee.ristoseene.raytracer.eyebased.shading.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

@FunctionalInterface
public interface BounceSamplingProcessor {

    @FunctionalInterface
    interface BounceSampler {
        SampleValue sampleBounce(ShadingContext shadingContext, BounceContext bounceContext);
    }

    SampleValue processBounceSampling(
            BounceSampler bounceSampler, ShadingContext shadingContext, BounceContext bounceContext, double scatter
    );

    BounceSamplingProcessor NO_OP_INSTANCE = (bounceSampler, shadingContext, bounceContext, scatter) -> SampleValue.NO_OP_INSTANCE;

    TypedAttribute<BounceSamplingProcessor> KEY = new TypedAttribute<>() {

        @Override
        public Class<BounceSamplingProcessor> getValueType() {
            return BounceSamplingProcessor.class;
        }

        @Override
        public BounceSamplingProcessor getDefaultValue() {
            return NO_OP_INSTANCE;
        }

    };

}
