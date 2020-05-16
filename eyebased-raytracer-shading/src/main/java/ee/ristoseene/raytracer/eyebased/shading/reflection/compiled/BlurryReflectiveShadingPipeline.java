package ee.ristoseene.raytracer.eyebased.shading.reflection.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

/**
 * Instances of this class rely on {@link BounceSamplingProcessor} for bouncing off rays from the current surface.
 *
 * Instances of this class rely on {@link BouncingRayProcessor} for obtaining the {@link SampleValue} of a ray
 * bounced off the current surface.
 *
 * @see BounceSamplingProcessor
 * @see BounceSamplingProcessor#KEY
 *
 * @see BouncingRayProcessor
 * @see BouncingRayProcessor#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 * @see AbstractColorMultiplyingBounceShadingPipeline
 */
public class BlurryReflectiveShadingPipeline extends AbstractColorMultiplyingBounceShadingPipeline {

    private final DoubleValueProvider reflectionBlurriness;
    private final AdjustableHemisphericalSampler blurSampler;

    public BlurryReflectiveShadingPipeline(
            final ValueProvider<Vector3.Accessible> reflectionColor,
            final DoubleValueProvider reflectionBlurriness,
            final AdjustableHemisphericalSampler blurSampler
    ) {
        super(reflectionColor);
        this.reflectionBlurriness = Objects.requireNonNull(reflectionBlurriness, "Reflection blurriness not provided");
        this.blurSampler = Objects.requireNonNull(blurSampler, "Blur sampler not provided");
    }

    @Override
    protected SampleValue shadeBouncing(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final double blurrinessMultiplier = reflectionBlurriness.getDoubleValue(shadingContext);
        return shadingContext.getAttributeValue(BounceSamplingProcessor.KEY).processBounceSampling(
                new ReflectiveBounceSampler(blurSampler, blurrinessMultiplier),
                shadingContext, bounceContext, blurrinessMultiplier
        );
    }

    private static final class ReflectiveBounceSampler implements BounceSamplingProcessor.BounceSampler {

        private final AdjustableHemisphericalSampler blurSampler;
        private final double blurrinessMultiplier;

        ReflectiveBounceSampler(
                final AdjustableHemisphericalSampler blurSampler,
                final double blurrinessMultiplier
        ) {
            this.blurSampler = blurSampler;
            this.blurrinessMultiplier = blurrinessMultiplier;
        }

        @Override
        public SampleValue sampleBounce(final ShadingContext shadingContext, final BounceContext bounceContext) {
            return shadingContext.getAttributeValue(BouncingRayProcessor.KEY).processBouncingRay(
                    createBouncingRay(shadingContext),
                    bounceContext
            );
        }

        private Ray createBouncingRay(final ShadingContext shadingContext) {
            final RayIntersectionContext rayIntersectionContext = shadingContext.getRayIntersectionContext();
            final Vector3.Accessible bounceOrigin = rayIntersectionContext.getRayIntersectionPoint();
            final Vector3.Accessible bounceDirection = VecMath.reflect(
                    rayIntersectionContext.getIntersectingRay().getDirection(),
                    blurSampler.sample(shadingContext.getGeometryContext().getSurfaceNormal(), blurrinessMultiplier),
                    Factories.FACTORY_CONST_VECTOR3_xyz
            );
            return new SimpleRay(bounceOrigin, bounceDirection);
        }

    }

}
