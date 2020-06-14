package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.vecmath.Vector3;

public class FrontFacingShadeableRayTraceableDisk extends AbstractShadeableRayTraceableDisk {

    public FrontFacingShadeableRayTraceableDisk(final Axis normalAxis, final Configuration configuration, final double diameter) {
        super(normalAxis, configuration, diameter);
    }

    @Override
    protected Vector3.Accessible resolveSurfaceNormal(final RayIntersectionContext rayIntersectionContext) {
        return transformedNormal;
    }

    @Override
    protected boolean isNotTraceable(final double dotRayNormal) {
        return dotRayNormal >= 0.0;
    }

}
