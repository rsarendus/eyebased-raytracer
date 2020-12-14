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
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

/**
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
public class SimpleRefractiveShadingPipeline extends AbstractRefractiveShadingPipeline {

    public SimpleRefractiveShadingPipeline(
            final ValueProvider<Vector3.Accessible> transparencyColor,
            final DoubleValueProvider indexOfRefraction,
            final ShadingPipeline totalInternalReflectionPipeline
    ) {
        super(transparencyColor, indexOfRefraction, totalInternalReflectionPipeline);
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final RayIntersectionContext rayIntersectionContext = shadingContext.getRayIntersectionContext();
        final Vector3.Accessible bounceDirection = VecMath.refract(
                rayIntersectionContext.getIntersectingRay().getDirection(),
                shadingContext.getGeometryContext().getSurfaceNormal(),
                indexOfRefractionProvider.getDoubleValue(shadingContext),
                Factories.FACTORY_CONST_VECTOR3_xyz
        );

        if (VecMathExtended.equal(bounceDirection, Vectors.VECTOR3_ZERO_ZERO_ZERO)) {
            return totalInternalReflectionPipeline.shade(shadingContext, bounceContext);
        }

        final Vector3.Accessible transparencyColor = transparencyColorProvider.getValue(shadingContext);

        if (shadingContext.getAttributeValue(BounceShadingFilter.KEY).test(bounceContext, transparencyColor)) {
            final SampleValue bounceShadingResult = shadingContext.getAttributeValue(BouncingRayProcessor.KEY).processBouncingRay(
                    new SimpleRay(rayIntersectionContext.getRayIntersectionPoint(), bounceDirection),
                    bounceContext.nextBounce()
            );
            return shadingContext.getAttributeValue(BounceSampleResolver.KEY)
                    .resolveBounceSample(shadingContext, bounceShadingResult, transparencyColor);
        } else {
            return shadingContext.getAttributeValue(SampleValueFactory.KEY)
                    .create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
        }
    }

}
