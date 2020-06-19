package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

@ExtendWith(MockitoExtension.class)
public class RayTraceableGroupCullerTest {

    @Mock
    private Predicate<AABB> mockFilter;

    @Test
    public void cullerShouldNotAllowMissingFilterForNoSorterConstructor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RayTraceableGroupCuller(null)
        );

        Assertions.assertEquals("Filter not provided", exception.getMessage());
    }

    @Test
    public void cullerShouldNotAllowMissingFilterForComparatorConstructor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RayTraceableGroupCuller(null, mockSorter())
        );

        Assertions.assertEquals("Filter not provided", exception.getMessage());
    }

    @Test
    public void cullerShouldNotAllowMissingFilterForOptionalSorterConstructor() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RayTraceableGroupCuller(null, Optional.empty())
        );

        Assertions.assertEquals("Filter not provided", exception.getMessage());
    }

    @Test
    public void cullerShouldNotAllowMissingSorterForComparatorConstructor() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new RayTraceableGroupCuller(mockFilter, (Comparator<AABB>) null)
        );
    }

    @Test
    public void cullerShouldNotAllowMissingSorterForOptionalSorterConstructor() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new RayTraceableGroupCuller(mockFilter, (Optional<Comparator<AABB>>) null)
        );
    }

    @Test
    public void cullerWithoutSorterShouldReturnNonGroupRayTraceableItself() {
        testCullerReturnsRayTraceableItselfIfNotGroup(
                new RayTraceableGroupCuller(mockFilter),
                Optional.empty()
        );
    }

    @Test
    public void cullerWithSpecificSorterShouldReturnNonGroupRayTraceableItself() {
        Comparator<AABB> mockSorter = mockSorter();
        testCullerReturnsRayTraceableItselfIfNotGroup(
                new RayTraceableGroupCuller(mockFilter, mockSorter),
                Optional.of(mockSorter)
        );
    }

    @Test
    public void cullerWithOptionalSorterShouldReturnNonGroupRayTraceableItself() {
        Comparator<AABB> mockSorter = mockSorter();
        testCullerReturnsRayTraceableItselfIfNotGroup(
                new RayTraceableGroupCuller(mockFilter, Optional.of(mockSorter)),
                Optional.of(mockSorter)
        );
    }

    @Test
    public void cullerWithOptionalEmptySorterShouldReturnNonGroupRayTraceableItself() {
        testCullerReturnsRayTraceableItselfIfNotGroup(
                new RayTraceableGroupCuller(mockFilter, Optional.empty()),
                Optional.empty()
        );
    }

    @Test
    public void cullerWithoutSorterShouldCallRayTraceableGroupCullWithEmptySorter() {
        testCullerCallsCullOnRayTraceableGroupAndReturnsResult(
                new RayTraceableGroupCuller(mockFilter),
                Optional.empty()
        );
    }

    @Test
    public void cullerWithSpecificSorterShouldCallRayTraceableGroupCullWithSpecifiedSorter() {
        Comparator<AABB> mockSorter = mockSorter();
        testCullerCallsCullOnRayTraceableGroupAndReturnsResult(
                new RayTraceableGroupCuller(mockFilter, mockSorter),
                Optional.of(mockSorter)
        );
    }

    @Test
    public void cullerWithOptionalSorterShouldCallRayTraceableGroupCullWithSpecifiedSorter() {
        Comparator<AABB> mockSorter = mockSorter();
        testCullerCallsCullOnRayTraceableGroupAndReturnsResult(
                new RayTraceableGroupCuller(mockFilter, Optional.of(mockSorter)),
                Optional.of(mockSorter)
        );
    }

    @Test
    public void cullerWithOptionalEmptySorterShouldCallRayTraceableGroupCullWithEmptySorter() {
        testCullerCallsCullOnRayTraceableGroupAndReturnsResult(
                new RayTraceableGroupCuller(mockFilter, Optional.empty()),
                Optional.empty()
        );
    }

    private void testCullerReturnsRayTraceableItselfIfNotGroup(RayTraceableGroupCuller culler, Optional<Comparator<AABB>> usedSorter) {
        RayTraceable mockRayTraceable = mockRayTraceable();

        RayTraceable culledRayTraceable = culler.apply(mockRayTraceable);
        Assertions.assertSame(mockRayTraceable, culledRayTraceable);

        Mockito.verifyNoInteractions(mockFilter);
        if (usedSorter.isPresent()) {
            Mockito.verifyNoInteractions(usedSorter.get());
        }
    }

    private void testCullerCallsCullOnRayTraceableGroupAndReturnsResult(RayTraceableGroupCuller culler, Optional<Comparator<AABB>> usedSorter) {
        RayTraceableGroup culledResult = mockRayTraceableGroup();
        RayTraceableGroup mockRayTraceable = mockRayTraceableGroup(culledResult);

        RayTraceable culledRayTraceable = culler.apply(mockRayTraceable);
        Assertions.assertSame(culledResult, culledRayTraceable);

        verifyRayTraceableGroupCullCalled(mockRayTraceable, usedSorter);
    }

    @SuppressWarnings("unchecked")
    private void verifyRayTraceableGroupCullCalled(RayTraceableGroup rayTraceableGroup, Optional<Comparator<AABB>> expectedSorter) {
        ArgumentCaptor<Optional<Comparator<AABB>>> argumentCaptor = ArgumentCaptor.forClass(Optional.class);
        Mockito.verify(rayTraceableGroup, Mockito.times(1)).cull(Mockito.same(mockFilter), argumentCaptor.capture());
        Mockito.verifyNoInteractions(mockFilter);

        Optional<Comparator<AABB>> capturedArgument = argumentCaptor.getValue();
        Assertions.assertNotNull(capturedArgument);

        if (expectedSorter.isPresent()) {
            Assertions.assertTrue(capturedArgument.isPresent());
            Assertions.assertSame(expectedSorter.get(), capturedArgument.get());
            Mockito.verifyNoInteractions(capturedArgument.get());
        } else {
            Assertions.assertFalse(capturedArgument.isPresent());
        }
    }

    @SuppressWarnings("unchecked")
    private static RayTraceableGroup mockRayTraceableGroup(RayTraceableGroup culledResult) {
        RayTraceableGroup mockedRayTraceableGroup = Mockito.mock(RayTraceableGroup.class);
        Mockito.doReturn(culledResult).when(mockedRayTraceableGroup).cull(Mockito.any(Predicate.class), Mockito.any(Optional.class));
        return mockedRayTraceableGroup;
    }

    private static RayTraceableGroup mockRayTraceableGroup() {
        return Mockito.mock(RayTraceableGroup.class);
    }

    private static RayTraceable mockRayTraceable() {
        return Mockito.mock(RayTraceable.class);
    }

    @SuppressWarnings("unchecked")
    private static Comparator<AABB> mockSorter() {
        return Mockito.mock(Comparator.class);
    }

}
