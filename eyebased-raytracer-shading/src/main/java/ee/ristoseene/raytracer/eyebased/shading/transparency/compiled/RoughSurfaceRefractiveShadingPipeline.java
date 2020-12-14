package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

/**
 * Instances of this class rely on {@link BounceSamplingProcessor} for bouncing off rays from the current surface.
 *
 * Instances of this class rely on {@link BounceShadingFilter} for deciding whether to bounce rays further or consider
 * the surface as black.
 *
 * Instances of this class rely on {@link BouncingRayProcessor} for obtaining the {@link SampleValue} of a ray
 * bounced off the current surface.
 *
 * Instances of this class rely on {@link BounceSampleResolver} for resolving the bounce sampling result into the
 * {@link SampleValue} emitted by the current surface.
 *
 * Instances of this class rely on {@link SampleValueFactory} for producing shading results.
 *
 * @see BounceSamplingProcessor
 * @see BounceSamplingProcessor#KEY
 *
 * @see BounceShadingFilter
 * @see BounceShadingFilter#KEY
 *
 * @see BouncingRayProcessor
 * @see BouncingRayProcessor#KEY
 *
 * @see BounceSampleResolver
 * @see BounceSampleResolver#KEY
 *
 * @see SampleValueFactory
 * @see SampleValueFactory#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 */
public class RoughSurfaceRefractiveShadingPipeline extends AbstractRefractiveShadingPipeline {

    private final DoubleValueProvider surfaceRoughness;
    private final AdjustableHemisphericalSampler roughnessSampler;

    public RoughSurfaceRefractiveShadingPipeline(
            final ValueProvider<Vector3.Accessible> transparencyColor,
            final DoubleValueProvider indexOfRefraction,
            final DoubleValueProvider surfaceRoughness,
            final AdjustableHemisphericalSampler roughnessSampler,
            final ShadingPipeline totalInternalReflectionPipeline
    ) {
        super(transparencyColor, indexOfRefraction, totalInternalReflectionPipeline);
        this.surfaceRoughness = Objects.requireNonNull(surfaceRoughness, "Surface roughness not provided");
        this.roughnessSampler = Objects.requireNonNull(roughnessSampler, "Roughness sampler not provided");
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final Vector3.Accessible transparencyColor = transparencyColorProvider.getValue(shadingContext);
        final double roughnessMultiplier = surfaceRoughness.getDoubleValue(shadingContext);
        final double refractiveIndex = indexOfRefractionProvider.getDoubleValue(shadingContext);

        SampleValue bounceShadingResult = shadingContext.getAttributeValue(BounceSamplingProcessor.KEY).processBounceSampling(
                new RefractiveBounceSampler(transparencyColor, roughnessMultiplier, refractiveIndex),
                shadingContext, bounceContext, roughnessMultiplier
        );
        return shadingContext.getAttributeValue(BounceSampleResolver.KEY)
                .resolveBounceSample(shadingContext, bounceShadingResult);
    }

    AdjustableHemisphericalSampler getRoughnessSampler() {
        return roughnessSampler;
    }

    ShadingPipeline getTotalInternalReflectionPipeline() {
        return totalInternalReflectionPipeline;
    }

    private final class RefractiveBounceSampler implements BounceSamplingProcessor.BounceSampler {

        private final Vector3.Accessible transparencyColor;
        private final double roughnessMultiplier;
        private final double refractiveIndex;

        RefractiveBounceSampler(
                final Vector3.Accessible transparencyColor,
                final double roughnessMultiplier,
                final double refractiveIndex
        ) {
            this.transparencyColor = transparencyColor;
            this.roughnessMultiplier = roughnessMultiplier;
            this.refractiveIndex = refractiveIndex;
        }

        @Override
        public SampleValue sampleBounce(final ShadingContext shadingContext, final BounceContext bounceContext) {
            final RayIntersectionContext rayIntersectionContext = shadingContext.getRayIntersectionContext();
            final Vector3.Accessible bounceDirection = VecMath.refract(
                    rayIntersectionContext.getIntersectingRay().getDirection(),
                    getRoughnessSampler().sample(shadingContext.getGeometryContext().getSurfaceNormal(), roughnessMultiplier),
                    refractiveIndex,
                    Factories.FACTORY_CONST_VECTOR3_xyz
            );

            if (VecMathExtended.equal(bounceDirection, Vectors.VECTOR3_ZERO_ZERO_ZERO)) {
                // TODO: figure out whether it would be reasonable to somehow send the new rough-sampled normal to the total internal reflection pipeline as the surface normal
                return getTotalInternalReflectionPipeline().shade(shadingContext, bounceContext);
            } else if (shadingContext.getAttributeValue(BounceShadingFilter.KEY).test(bounceContext, transparencyColor)) {
                return shadingContext.getAttributeValue(BouncingRayProcessor.KEY).processBouncingRay(
                        new SimpleRay(rayIntersectionContext.getRayIntersectionPoint(), bounceDirection),
                        bounceContext.nextBounce()
                ).multiplied(transparencyColor);
            } else {
                return shadingContext.getAttributeValue(SampleValueFactory.KEY)
                        .create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
            }
        }

    }

}
