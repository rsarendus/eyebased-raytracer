package ee.ristoseene.raytracer.eyebased.shading.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

@FunctionalInterface
public interface BouncingRayProcessor {

    SampleValue processBouncingRay(Ray bouncingRay, BounceContext bounceContext);

    BouncingRayProcessor NO_OP_INSTANCE = (bouncingRay, bounceContext) -> SampleValue.NO_OP_INSTANCE;

    TypedAttribute<BouncingRayProcessor> KEY = new TypedAttribute<>() {

        @Override
        public Class<BouncingRayProcessor> getValueType() {
            return BouncingRayProcessor.class;
        }

        @Override
        public BouncingRayProcessor getDefaultValue() {
            return NO_OP_INSTANCE;
        }

    };

}
