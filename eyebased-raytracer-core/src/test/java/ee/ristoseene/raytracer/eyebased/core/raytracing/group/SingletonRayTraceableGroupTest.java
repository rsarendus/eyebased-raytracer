package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SingletonRayTraceableGroupTest extends AbstractRayTraceableGroupTest {

    @Test
    public void singletonRayTraceableGroupShouldNotAllowMissingRayTraceable() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new SingletonRayTraceableGroup(null)
        );

        Assertions.assertEquals("Ray-traceable not provided", exception.getMessage());
    }

    @Test
    public void cullShouldDelegateToContentsWhenContentsIsRayTraceableGroupAndSorterNotProvided() {
        RayTraceableGroup originalContent = Mockito.mock(RayTraceableGroup.class);
        RayTraceableGroup culledContent = Mockito.mock(RayTraceableGroup.class);
        Mockito.doReturn(culledContent).when(originalContent).cull(anyAabbFilter(), anyOptionalAabbSorter());
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(originalContent);
        Predicate<AABB> filter = createAabbFilterMock();
        Optional<Comparator<AABB>> sorterOptional = Optional.empty();

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertSame(culledContent, culledResult);
        Mockito.verify(originalContent, Mockito.times(1)).cull(filter, sorterOptional);
        Mockito.verifyNoMoreInteractions(originalContent, culledContent, filter);
    }

    @Test
    public void cullShouldDelegateToContentsWhenContentsIsRayTraceableGroupAndSorterProvided() {
        RayTraceableGroup originalContent = Mockito.mock(RayTraceableGroup.class);
        RayTraceableGroup culledContent = Mockito.mock(RayTraceableGroup.class);
        Mockito.doReturn(culledContent).when(originalContent).cull(anyAabbFilter(), anyOptionalAabbSorter());
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(originalContent);
        Predicate<AABB> filter = createAabbFilterMock();
        Comparator<AABB> sorter = createAabbSorterMock();
        Optional<Comparator<AABB>> sorterOptional = Optional.of(sorter);

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertSame(culledContent, culledResult);
        Mockito.verify(originalContent, Mockito.times(1)).cull(filter, sorterOptional);
        Mockito.verifyNoMoreInteractions(originalContent, culledContent, filter, sorter);
    }

    @Test
    public void cullShouldReturnItselfWhenContentsIsNotRayTraceableGroupAndContentsPassesFilterAndSorterNotProvided() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        AABB aabb = Mockito.mock(AABB.class);
        Mockito.doReturn(aabb).when(content).getAABB();
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);
        Predicate<AABB> filter = createAabbFilterMock(true);
        Optional<Comparator<AABB>> sorterOptional = Optional.empty();

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertSame(rayTraceableGroup, culledResult);
        Mockito.verify(content, Mockito.times(1)).getAABB();
        Mockito.verify(filter, Mockito.times(1)).test(aabb);
        Mockito.verifyNoMoreInteractions(content, aabb, filter);
    }

    @Test
    public void cullShouldReturnItselfWhenContentsIsNotRayTraceableGroupAndContentsPassesFilterAndSorterProvided() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        AABB aabb = Mockito.mock(AABB.class);
        Mockito.doReturn(aabb).when(content).getAABB();
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);
        Predicate<AABB> filter = createAabbFilterMock(true);
        Comparator<AABB> sorter = createAabbSorterMock();
        Optional<Comparator<AABB>> sorterOptional = Optional.of(sorter);

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertSame(rayTraceableGroup, culledResult);
        Mockito.verify(content, Mockito.times(1)).getAABB();
        Mockito.verify(filter, Mockito.times(1)).test(aabb);
        Mockito.verifyNoMoreInteractions(content, aabb, filter, sorter);
    }

    @Test
    public void cullShouldReturnEmptyGroupWhenContentsIsNotRayTraceableGroupAndContentsDoesNotPassFilterAndSorterNotProvided() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        AABB aabb = Mockito.mock(AABB.class);
        Mockito.doReturn(aabb).when(content).getAABB();
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);
        Predicate<AABB> filter = createAabbFilterMock(false);
        Optional<Comparator<AABB>> sorterOptional = Optional.empty();

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertTrue(culledResult instanceof EmptyRayTraceableGroup);
        Mockito.verify(content, Mockito.times(1)).getAABB();
        Mockito.verify(filter, Mockito.times(1)).test(aabb);
        Mockito.verifyNoMoreInteractions(content, aabb, filter);
    }

    @Test
    public void cullShouldReturnEmptyGroupWhenContentsIsNotRayTraceableGroupAndContentsDoesNotPassFilterAndSorterProvided() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        AABB aabb = Mockito.mock(AABB.class);
        Mockito.doReturn(aabb).when(content).getAABB();
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);
        Predicate<AABB> filter = createAabbFilterMock(false);
        Comparator<AABB> sorter = createAabbSorterMock();
        Optional<Comparator<AABB>> sorterOptional = Optional.of(sorter);

        RayTraceableGroup culledResult = rayTraceableGroup.cull(filter, sorterOptional);

        Assertions.assertTrue(culledResult instanceof EmptyRayTraceableGroup);
        Mockito.verify(content, Mockito.times(1)).getAABB();
        Mockito.verify(filter, Mockito.times(1)).test(aabb);
        Mockito.verifyNoMoreInteractions(content, aabb, filter, sorter);
    }

    @Test
    public void interactWithShouldDelegateToContents() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        RayTracedState rayTracedState = Mockito.mock(RayTracedState.class);

        rayTraceableGroup.interactWith(tracingRayContext, rayTracedState);

        Mockito.verify(content, Mockito.times(1)).interactWith(tracingRayContext, rayTracedState);
        Mockito.verifyNoMoreInteractions(content, tracingRayContext, rayTracedState);
    }

    @Test
    public void contentsShouldReturnStreamOfSingleContentObject() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);

        List<RayTraceable> contentStreamList = rayTraceableGroup.contents().collect(Collectors.toList());

        Assertions.assertEquals(List.of(content), contentStreamList);
        Mockito.verifyNoInteractions(content);
    }

    @Test
    public void getAABBShouldReturnTheAABBOfTheContent() {
        RayTraceable content = Mockito.mock(RayTraceable.class);
        AABB contentAABB = Mockito.mock(AABB.class);
        Mockito.doReturn(contentAABB).when(content).getAABB();
        RayTraceableGroup rayTraceableGroup = new SingletonRayTraceableGroup(content);

        AABB resultAABB = rayTraceableGroup.getAABB();

        Assertions.assertSame(contentAABB, resultAABB);
        Mockito.verify(content, Mockito.times(1)).getAABB();
        Mockito.verifyNoMoreInteractions(content, contentAABB);
    }

}
