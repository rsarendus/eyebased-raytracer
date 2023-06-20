package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;

/**
 * A marker interface indicating that an implementing object does not add any value in
 * {@link ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup}s.
 */
public interface EmptyRayTraceable extends RayTraceable {
}
