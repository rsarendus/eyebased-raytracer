package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class RayTraceableGroupCuller implements Function<RayTraceable, RayTraceable> {

    private final Predicate<AABB> filter;
    private final Optional<Comparator<AABB>> sorter;

    public RayTraceableGroupCuller(final Predicate<AABB> filter) {
        this(filter, Optional.empty());
    }

    public RayTraceableGroupCuller(final Predicate<AABB> filter, final Comparator<AABB> sorter) {
        this(filter, Optional.of(sorter));
    }

    public RayTraceableGroupCuller(final Predicate<AABB> filter, final Optional<Comparator<AABB>> sorter) {
        this.filter = Objects.requireNonNull(filter, "Filter not provided");
        this.sorter = Objects.requireNonNull(sorter);
    }

    @Override
    public RayTraceable apply(final RayTraceable rayTraceable) {
        if (rayTraceable instanceof RayTraceableGroup) {
            return ((RayTraceableGroup) rayTraceable).cull(filter, sorter);
        }
        return rayTraceable;
    }

}
