package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracingResult;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Shadeable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingProcessor;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;

public class StatefulRayTracedState implements RayTracedState {

    private final double minimumDepth;

    private Shadeable currentShadeable;
    private double currentDepth;

    public StatefulRayTracedState(final Shadeable fallbackShadeable) {
        this.currentShadeable = fallbackShadeable;
        this.minimumDepth = Constants.MINIMUM_DEPTH;
        this.currentDepth = Constants.MAXIMUM_DEPTH;
    }

    @Override
    public boolean testDepth(final double depthToTest) {
        return depthToTest > minimumDepth & depthToTest < currentDepth;
    }

    @Override
    public boolean testDepthRange(final double minimumDepthToTest, final double maximumDepthToTest) {
        return minimumDepthToTest < currentDepth & maximumDepthToTest > minimumDepth;
    }

    @Override
    public boolean registerRayInteraction(final double distance, final RayTracingResult rayTracingResult) {
        if (testDepth(distance)) {
            currentShadeable = rayTracingResult.getAttributeValue(Shadeable.KEY);
            currentDepth = distance;
            return true;
        } else {
            return false;
        }
    }

    public SampleValue shadeCurrentState(final Ray tracingRay, final ShadingProcessor shadingProcessor) {
        final RayIntersectionContext intersectionContext = new SimpleRayIntersectionContext(tracingRay, currentDepth);
        return currentShadeable.shade(intersectionContext, shadingProcessor);
    }

}
