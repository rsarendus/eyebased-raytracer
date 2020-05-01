package ee.ristoseene.raytracer.eyebased.geometry.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface RaySurfaceIntersectionGeometryContextFactory {

    RaySurfaceIntersectionGeometryContextFactory NO_OP_INSTANCE = (rayIntersectionContext, surfaceNormal, transform) -> GeometryContext.NO_OP_INSTANCE;

    GeometryContext create(RayIntersectionContext rayIntersectionContext, Vector3.Accessible surfaceNormal, CompiledTransform transform);

}
