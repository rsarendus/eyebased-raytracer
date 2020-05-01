package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Shadeable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingProcessor;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.Vector3;

import java.util.function.Function;

public abstract class AbstractShadeableRayTraceablePrimitive extends AbstractRayTraceablePrimitive implements Shadeable {

    protected AbstractShadeableRayTraceablePrimitive(
            final Configuration configuration, final double scale,
            final Function<Matrix4x4.Accessible, AABB> aabbResolver
    ) {
        super(configuration, scale, aabbResolver);
    }

    protected abstract Vector3.Accessible resolveSurfaceNormal(final RayIntersectionContext rayIntersectionContext);

    @Override
    public SampleValue shade(final RayIntersectionContext rayIntersectionContext, final ShadingProcessor shadingProcessor) {
        return shadingProcessor.processShading(
                geometryContextFactory.create(rayIntersectionContext, resolveSurfaceNormal(rayIntersectionContext), transform),
                rayIntersectionContext,
                shadingPipeline
        );
    }

}
