package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SingletonRayTraceableGroup implements RayTraceableGroup {

    private final RayTraceable contents;

    public SingletonRayTraceableGroup(final RayTraceable rayTraceable) {
        contents = Objects.requireNonNull(rayTraceable, "Ray-traceable not provided");
    }

    @Override
    public RayTraceableGroup cull(final Predicate<AABB> filter, final Optional<Comparator<AABB>> sorter) {
        if (contents instanceof RayTraceableGroup) {
            return ((RayTraceableGroup) contents).cull(filter, sorter);
        } else if (filter.test(contents.getAABB())) {
            return this;
        } else {
            return EmptyRayTraceableGroup.INSTANCE;
        }
    }

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final RayTracedState rayTracedState) {
        contents.interactWith(tracingRayContext, rayTracedState);
    }

    @Override
    public Stream<RayTraceable> contents() {
        return Stream.of(contents);
    }

    @Override
    public AABB getAABB() {
        return contents.getAABB();
    }

}
