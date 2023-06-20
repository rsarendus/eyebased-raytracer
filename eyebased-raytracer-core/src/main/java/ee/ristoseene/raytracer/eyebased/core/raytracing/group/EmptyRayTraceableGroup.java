package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EmptyRayTraceableGroup implements EmptyRayTraceable, RayTraceableGroup {

    public static final EmptyRayTraceableGroup INSTANCE = new EmptyRayTraceableGroup();

    private EmptyRayTraceableGroup() {}

    @Override
    public RayTraceableGroup cull(final Predicate<AABB> filter, final Optional<Comparator<AABB>> sorter) {
        return this;
    }

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final RayTracedState rayTracedState) {}

    @Override
    public Stream<RayTraceable> contents() {
        return Stream.empty();
    }

    @Override
    public AABB getAABB() {
        return BoundlessAABB.INSTANCE;
    }

}
