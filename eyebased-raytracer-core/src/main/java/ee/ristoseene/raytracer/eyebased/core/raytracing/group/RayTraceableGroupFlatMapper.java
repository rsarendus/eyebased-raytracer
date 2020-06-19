package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class RayTraceableGroupFlatMapper implements Function<RayTraceable, Stream<RayTraceable>> {

    private final Class<? extends RayTraceableGroup>[] flatMappableTypes;
    private final boolean recursive;

    public RayTraceableGroupFlatMapper() {
        this(false);
    }

    public RayTraceableGroupFlatMapper(final boolean recursive) {
        this(recursive, RayTraceableGroup.class);
    }

    @SafeVarargs
    public RayTraceableGroupFlatMapper(Class<? extends RayTraceableGroup>... flatMappableTypes) {
        this(false, flatMappableTypes);
    }

    @SafeVarargs
    public RayTraceableGroupFlatMapper(final boolean recursive, Class<? extends RayTraceableGroup>... flatMappableTypes) {
        this.flatMappableTypes = Objects.requireNonNull(flatMappableTypes);
        this.recursive = recursive;
    }

    @Override
    public Stream<RayTraceable> apply(final RayTraceable rayTraceable) {
        for (final Class<? extends RayTraceableGroup> flatMappableType : flatMappableTypes) {
            if (flatMappableType.isInstance(rayTraceable)) {
                Stream<RayTraceable> stream = ((RayTraceableGroup) rayTraceable).contents();
                if (recursive) stream = stream.flatMap(this);
                return stream;
            }
        }
        return Stream.of(rayTraceable);
    }

}
