package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EmptyRayTraceableGroupTest extends AbstractRayTraceableGroupTest {

    @Test
    public void cullShouldReturnItselfWhenSorterNotProvided() {
        Predicate<AABB> filter = createAabbFilterMock();

        RayTraceableGroup culledResult = EmptyRayTraceableGroup.INSTANCE.cull(filter, Optional.empty());

        Assertions.assertSame(EmptyRayTraceableGroup.INSTANCE, culledResult);
        Mockito.verifyNoInteractions(filter);
    }

    @Test
    public void cullShouldReturnItselfWhenSorterProvided() {
        Predicate<AABB> filter = createAabbFilterMock();
        Comparator<AABB> sorter = createAabbSorterMock();

        RayTraceableGroup culledResult = EmptyRayTraceableGroup.INSTANCE.cull(filter, Optional.of(sorter));

        Assertions.assertSame(EmptyRayTraceableGroup.INSTANCE, culledResult);
        Mockito.verifyNoInteractions(filter, sorter);
    }

    @Test
    public void interactWithShouldDoNothing() {
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        RayTracedState rayTracedState = Mockito.mock(RayTracedState.class);

        EmptyRayTraceableGroup.INSTANCE.interactWith(tracingRayContext, rayTracedState);

        Mockito.verifyNoInteractions(tracingRayContext, rayTracedState);
    }

    @Test
    public void contentsShouldReturnAnEmptyStream() {
        Stream<RayTraceable> contentStream = EmptyRayTraceableGroup.INSTANCE.contents();

        Assertions.assertEquals(0L, contentStream.count());
    }

    @Test
    public void getAABBShouldReturnBoundlessAABB() {
        AABB aabb = EmptyRayTraceableGroup.INSTANCE.getAABB();

        Assertions.assertTrue(aabb instanceof BoundlessAABB);
    }

}
