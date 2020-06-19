package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.AABBBuilder;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SimpleRayTraceableGroup implements RayTraceableGroup {

    private final RayTraceable[] contents;
    private final AABB bounds;

    private SimpleRayTraceableGroup(final RayTraceable[] preparedContents) {
        contents = preparedContents;
        bounds = getBoundsOf(contents);
    }

    public SimpleRayTraceableGroup(final Stream<RayTraceable> rayTraceables) {
        contents = rayTraceables
                .filter(SimpleRayTraceableGroup::nonEmpty)
                .flatMap(new RayTraceableGroupFlatMapper(true, EmptyRayTraceableGroup.class, SingletonRayTraceableGroup.class))
                .distinct()
                .toArray(RayTraceable[]::new);

        bounds = getBoundsOf(contents);
    }

    public SimpleRayTraceableGroup(final Collection<RayTraceable> rayTraceables) {
        this(rayTraceables.stream());
    }

    public SimpleRayTraceableGroup(final Stream<RayTraceable> rayTraceables, final Comparator<AABB> sorter) {
        this(rayTraceables);
        sort(contents, sorter);
    }

    public SimpleRayTraceableGroup(final Collection<RayTraceable> rayTraceables, final Comparator<AABB> sorter) {
        this(rayTraceables.stream(), sorter);
    }

    private static void sort(final RayTraceable[] array, final Comparator<AABB> sorter) {
        Arrays.sort(array, Comparator.comparing(RayTraceable::getAABB, sorter));
    }

    private static boolean nonEmpty(final RayTraceable object) {
        return !(object == null || object instanceof EmptyRayTraceableGroup);
    }

    private static AABB getBoundsOf(final RayTraceable[] array) {
        if (array.length == 1) {
            return array[0].getAABB();
        } else if (array.length < 1) {
            return BoundlessAABB.INSTANCE;
        }

        final AABBBuilder aabbBuilder = new AABBBuilder();

        for (final RayTraceable traceable : array) {
            aabbBuilder.addAABB(traceable.getAABB());
        }

        return aabbBuilder.build(TraceableAABB::new);
    }

    @Override
    public RayTraceableGroup cull(final Predicate<AABB> filter, final Optional<Comparator<AABB>> sorter) {
        final RayTraceable[] filteredContents = Arrays.stream(contents)
                .filter(rt -> filter.test(rt.getAABB()))
                .map(new RayTraceableGroupCuller(filter, sorter))
                .flatMap(new RayTraceableGroupFlatMapper(true, EmptyRayTraceableGroup.class, SingletonRayTraceableGroup.class))
                .toArray(RayTraceable[]::new);

        if (filteredContents.length > 1) {
            if (sorter.isPresent()) {
                sort(filteredContents, sorter.get());
            } else if (Arrays.equals(filteredContents, contents)) {
                return this;
            }
            return new SimpleRayTraceableGroup(filteredContents);
        } else if (filteredContents.length == 1) {
            if (filteredContents[0] instanceof RayTraceableGroup) {
                return (RayTraceableGroup) filteredContents[0];
            }
            return new SingletonRayTraceableGroup(filteredContents[0]);
        } else {
            return EmptyRayTraceableGroup.INSTANCE;
        }
    }

    @Override
    public void interactWith(final TracingRayContext tracingRayContext, final RayTracedState rayTracedState) {
        for (final RayTraceable rayTraceable : contents) {
            if (rayTraceable.getAABB().intersects(tracingRayContext, rayTracedState)) {
                rayTraceable.interactWith(tracingRayContext, rayTracedState);
            }
        }
    }

    @Override
    public Stream<RayTraceable> contents() {
        return Arrays.stream(contents);
    }

    @Override
    public AABB getAABB() {
        return bounds;
    }

}
