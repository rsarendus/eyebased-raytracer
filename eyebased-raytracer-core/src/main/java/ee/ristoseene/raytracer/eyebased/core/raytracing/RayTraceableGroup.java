package ee.ristoseene.raytracer.eyebased.core.raytracing;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface RayTraceableGroup extends RayTraceable {

    RayTraceableGroup cull(Predicate<AABB> filter, Optional<Comparator<AABB>> sorter);

    Stream<RayTraceable> contents();

}
