package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class AbstractRayTraceableGroupTest {

    @SuppressWarnings("unchecked")
    protected static Predicate<AABB> anyAabbFilter() {
        return (Predicate<AABB>) Mockito.any(Predicate.class);
    }

    @SuppressWarnings("unchecked")
    protected static Comparator<AABB> anyAabbSorter() {
        return (Comparator<AABB>) Mockito.any(Comparator.class);
    }

    @SuppressWarnings("unchecked")
    protected static Optional<Comparator<AABB>> anyOptionalAabbSorter() {
        return (Optional<Comparator<AABB>>) Mockito.any(Optional.class);
    }

    @SuppressWarnings("unchecked")
    protected static Predicate<AABB> createAabbFilterMock() {
        return (Predicate<AABB>) Mockito.mock(Predicate.class);
    }

    protected static Predicate<AABB> createAabbFilterMock(boolean returnValue) {
        Predicate<AABB> filter = createAabbFilterMock();
        Mockito.doReturn(returnValue).when(filter).test(Mockito.any(AABB.class));
        return filter;
    }

    @SuppressWarnings("unchecked")
    protected static Comparator<AABB> createAabbSorterMock() {
        return (Comparator<AABB>) Mockito.mock(Comparator.class);
    }

    protected static RayTraceable createRayTraceableMockWithAABB(AABB aabb) {
        RayTraceable rayTraceable = Mockito.mock(RayTraceable.class);
        Mockito.doReturn(aabb).when(rayTraceable).getAABB();
        return rayTraceable;
    }

}
