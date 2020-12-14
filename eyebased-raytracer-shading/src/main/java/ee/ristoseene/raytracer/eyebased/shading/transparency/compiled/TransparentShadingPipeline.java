package ee.ristoseene.raytracer.eyebased.shading.transparency.compiled;

import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.shading.common.compiled.AbstractColorMultiplyingBounceShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.vecmath.Vector3;

/**
 * Instances of this class rely on {@link BouncingRayProcessor} for obtaining the {@link SampleValue} of a ray
 * bounced off the current surface.
 *
 * @see BouncingRayProcessor
 * @see BouncingRayProcessor#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 * @see AbstractColorMultiplyingBounceShadingPipeline
 */
public class TransparentShadingPipeline extends AbstractColorMultiplyingBounceShadingPipeline {

    public TransparentShadingPipeline(final ValueProvider<Vector3.Accessible> transparencyColor) {
        super(transparencyColor);
    }

    @Override
    protected SampleValue shadeBouncing(final ShadingContext shadingContext, final BounceContext bounceContext) {
        return shadingContext.getAttributeValue(BouncingRayProcessor.KEY).processBouncingRay(
                createBouncingRay(shadingContext),
                bounceContext.nextBounce()
        );
    }

    private static Ray createBouncingRay(final ShadingContext shadingContext) {
        final RayIntersectionContext rayIntersectionContext = shadingContext.getRayIntersectionContext();
        return new SimpleRay(
                rayIntersectionContext.getRayIntersectionPoint(),
                rayIntersectionContext.getIntersectingRay().getDirection()
        );
    }

}
