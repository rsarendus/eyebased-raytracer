package ee.ristoseene.raytracer.eyebased.shading.reflection.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.HemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
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
public class DiffusiveShadingPipeline extends AbstractColorMultiplyingBounceShadingPipeline {

    private final HemisphericalSampler diffuseDirectionSampler;
    private final double intensityCompensationMultiplier;

    public DiffusiveShadingPipeline(
            final ValueProvider<Vector3.Accessible> diffuseColorProvider,
            final HemisphericalSampler diffuseDirectionSampler,
            final double intensityCompensationMultiplier
    ) {
        super(diffuseColorProvider);
        this.diffuseDirectionSampler = Objects.requireNonNull(diffuseDirectionSampler, "Diffuse direction sampler not provided");
        this.intensityCompensationMultiplier = intensityCompensationMultiplier;
    }

    @Override
    protected SampleValue shadeBouncing(final ShadingContext shadingContext, final BounceContext bounceContext) {
        return shadingContext.getAttributeValue(BounceSamplingProcessor.KEY).processBounceSampling(
                new DiffusiveBounceSampler(diffuseDirectionSampler, intensityCompensationMultiplier),
                shadingContext, bounceContext, 1.0
        );
    }

    private static final class DiffusiveBounceSampler implements BounceSamplingProcessor.BounceSampler {

        private final HemisphericalSampler diffuseDirectionSampler;
        private final double intensityCompensationMultiplier;

        DiffusiveBounceSampler(
                final HemisphericalSampler diffuseDirectionSampler,
                final double intensityCompensationMultiplier
        ) {
            this.diffuseDirectionSampler = diffuseDirectionSampler;
            this.intensityCompensationMultiplier = intensityCompensationMultiplier;
        }

        @Override
        public SampleValue sampleBounce(final ShadingContext shadingContext, final BounceContext bounceContext) {
            final Vector3.Accessible surfaceNormal = shadingContext.getGeometryContext().getSurfaceNormal();
            final Vector3.Accessible bounceDirection = diffuseDirectionSampler.sample(surfaceNormal);
            final double weight = VecMath.dot(surfaceNormal, bounceDirection);

            final SampleValue reflectedColor = shadingContext.getAttributeValue(BouncingRayProcessor.KEY).processBouncingRay(
                    new SimpleRay(shadingContext.getRayIntersectionContext().getRayIntersectionPoint(), bounceDirection),
                    bounceContext.weightedBounce(weight)
            );

            final double compensatedWeight = weight * intensityCompensationMultiplier;
            return reflectedColor.multiplied(compensatedWeight, compensatedWeight, compensatedWeight);
        }

    }

}
