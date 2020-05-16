package ee.ristoseene.raytracer.eyebased.shading.configuration;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface BounceSampleResolver {

    SampleValue resolveBounceSample(ShadingContext shadingContext, SampleValue bounceSamplingResult);

    default SampleValue resolveBounceSample(final ShadingContext shadingContext, final SampleValue bounceSamplingResult, final Vector3.Accessible colorMultiplier) {
        return resolveBounceSample(shadingContext, bounceSamplingResult).multiplied(colorMultiplier);
    }

    default SampleValue resolveBounceSample(
            final ShadingContext shadingContext, final SampleValue bounceSamplingResult,
            final double redMultiplier, final double greenMultiplier, final double blueMultiplier
    ) {
        return resolveBounceSample(shadingContext, bounceSamplingResult).multiplied(redMultiplier, greenMultiplier, blueMultiplier);
    }

    BounceSampleResolver DEFAULT_INSTANCE = new BounceSampleResolver() {

        @Override
        public SampleValue resolveBounceSample(final ShadingContext shadingContext, final SampleValue bounceSamplingResult) {
            return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(shadingContext, bounceSamplingResult.getRGB());
        }

        @Override
        public SampleValue resolveBounceSample(
                final ShadingContext shadingContext, final SampleValue bounceSamplingResult,
                final Vector3.Accessible colorMultiplier
        ) {
            final Vector3.Accessible bounceSamplingColor = bounceSamplingResult.getRGB();
            return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(shadingContext,
                    bounceSamplingColor.x() * colorMultiplier.x(),
                    bounceSamplingColor.y() * colorMultiplier.y(),
                    bounceSamplingColor.z() * colorMultiplier.z());
        }

        @Override
        public SampleValue resolveBounceSample(
                final ShadingContext shadingContext, final SampleValue bounceSamplingResult,
                final double redMultiplier, final double greenMultiplier, final double blueMultiplier
        ) {
            final Vector3.Accessible bounceSamplingColor = bounceSamplingResult.getRGB();
            return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(shadingContext,
                    bounceSamplingColor.x() * redMultiplier,
                    bounceSamplingColor.y() * greenMultiplier,
                    bounceSamplingColor.z() * blueMultiplier);
        }

    };

    TypedAttribute<BounceSampleResolver> KEY = new TypedAttribute<>() {

        @Override
        public Class<BounceSampleResolver> getValueType() {
            return BounceSampleResolver.class;
        }

        @Override
        public BounceSampleResolver getDefaultValue() {
            return DEFAULT_INSTANCE;
        }

    };

}
