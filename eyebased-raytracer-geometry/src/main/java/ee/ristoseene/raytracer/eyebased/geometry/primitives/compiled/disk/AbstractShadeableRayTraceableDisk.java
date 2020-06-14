package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.AbstractShadeableRayTraceableFlatSurface;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.utilities.AABBFactory;

public abstract class AbstractShadeableRayTraceableDisk extends AbstractShadeableRayTraceableFlatSurface {

    protected AbstractShadeableRayTraceableDisk(final Axis normalAxis, final Configuration configuration, final double diameter) {
        super(normalAxis, configuration, diameter * 0.5, AABBFactory::createUnitSphereAABB);
    }

    @Override
    protected boolean isIntersectionPointInBounds(final double x, final double y, final double z) {
        return (x * x + y * y + z * z) <= 1.0;
    }

}
