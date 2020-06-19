package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RayTraceableGroupFlatMapperTest {

    @Test
    public void defaultFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper()
        );
    }

    @Test
    public void specificFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper(SpecificRayTraceableGroup.class)
        );
    }

    @Test
    public void nonRecursiveDefaultFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper(false)
        );
    }

    @Test
    public void nonRecursiveSpecificFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper(false, SpecificRayTraceableGroup.class)
        );
    }

    @Test
    public void recursiveDefaultFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper(true)
        );
    }

    @Test
    public void recursiveSpecificFlatMapperShouldReturnStreamOfNonGroupRayTraceableItself() {
        testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(
                new RayTraceableGroupFlatMapper(true, SpecificRayTraceableGroup.class)
        );
    }

    @Test
    public void defaultFlatMapperShouldReturnStreamOfRayTraceableInsideRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(),
                RayTraceableGroup.class
        );
    }

    @Test
    public void specificFlatMapperShouldReturnStreamOfRayTraceableInsideSpecificRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    @Test
    public void specificFlatMapperShouldReturnStreamOfNonSpecificRayTraceableGroupItself() {
        testFlatMapperReturnsStreamOfRayTraceableGroupItself(
                new RayTraceableGroupFlatMapper(SpecificRayTraceableGroup.class),
                RayTraceableGroup.class
        );
    }

    @Test
    public void nonRecursiveDefaultFlatMapperShouldReturnStreamOfRayTraceableInsideRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(false),
                RayTraceableGroup.class
        );
    }

    @Test
    public void nonRecursiveSpecificFlatMapperShouldReturnStreamOfRayTraceableInsideSpecificRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(false, SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    @Test
    public void nonRecursiveSpecificFlatMapperShouldReturnStreamOfNonSpecificRayTraceableGroupItself() {
        testFlatMapperReturnsStreamOfRayTraceableGroupItself(
                new RayTraceableGroupFlatMapper(false, SpecificRayTraceableGroup.class),
                RayTraceableGroup.class
        );
    }

    @Test
    public void recursiveDefaultFlatMapperShouldReturnStreamOfRayTraceableInsideRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(true),
                RayTraceableGroup.class
        );
    }

    @Test
    public void recursiveSpecificFlatMapperShouldReturnStreamOfRayTraceableInsideSpecificRayTraceableGroup() {
        testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(true, SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    @Test
    public void recursiveSpecificFlatMapperShouldReturnStreamOfNonSpecificRayTraceableGroupItself() {
        testFlatMapperReturnsStreamOfRayTraceableGroupItself(
                new RayTraceableGroupFlatMapper(true, SpecificRayTraceableGroup.class),
                RayTraceableGroup.class
        );
    }

    @Test
    public void defaultFlatMapperShouldNotFlatMapRayTraceableGroupInsideRayTraceableGroup() {
        testNonRecursiveFlatMapperDoesNotFlatMapRayTraceableGroupInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(),
                RayTraceableGroup.class
        );
    }

    @Test
    public void specificFlatMapperShouldNotFlatMapSpecificRayTraceableGroupInsideSpecificRayTraceableGroup() {
        testNonRecursiveFlatMapperDoesNotFlatMapRayTraceableGroupInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    @Test
    public void nonRecursiveDefaultFlatMapperShouldNotFlatMapRayTraceableGroupInsideRayTraceableGroup() {
        testNonRecursiveFlatMapperDoesNotFlatMapRayTraceableGroupInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(false),
                RayTraceableGroup.class
        );
    }

    @Test
    public void nonRecursiveSpecificFlatMapperShouldNotFlatMapSpecificRayTraceableGroupInsideSpecificRayTraceableGroup() {
        testNonRecursiveFlatMapperDoesNotFlatMapRayTraceableGroupInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(false, SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    @Test
    public void recursiveDefaultFlatMapperShouldReturnDeepestRayTraceableInsideRayTraceableGroup() {
        testRecursiveFlatMapperReturnsDeepestRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(true),
                RayTraceableGroup.class
        );
    }

    @Test
    public void recursiveSpecificFlatMapperShouldReturnDeepestRayTraceableInsideSpecificRayTraceableGroup() {
        testRecursiveFlatMapperReturnsDeepestRayTraceableInsideRayTraceableGroup(
                new RayTraceableGroupFlatMapper(true, SpecificRayTraceableGroup.class),
                SpecificRayTraceableGroup.class
        );
    }

    private void testFlatMapperReturnsStreamOfNonGroupRayTraceableItself(RayTraceableGroupFlatMapper flatMapper) {
        RayTraceable mockRayTraceable = mockRayTraceable();
        Stream<RayTraceable> flatMappedStream = flatMapper.apply(mockRayTraceable);
        assertStreamContents(flatMappedStream, mockRayTraceable);
    }

    private void testFlatMapperReturnsStreamOfRayTraceableInsideRayTraceableGroup(RayTraceableGroupFlatMapper flatMapper, Class<? extends RayTraceableGroup> groupType) {
        RayTraceable mockRayTraceable = mockRayTraceable();
        RayTraceableGroup mockRayTraceableGroup = mockRayTraceableGroup(groupType, mockRayTraceable);

        Stream<RayTraceable> flatMappedStream = flatMapper.apply(mockRayTraceableGroup);
        assertStreamContents(flatMappedStream, mockRayTraceable);

        Mockito.verify(mockRayTraceableGroup, Mockito.times(1)).contents();
    }

    private void testFlatMapperReturnsStreamOfRayTraceableGroupItself(RayTraceableGroupFlatMapper flatMapper, Class<? extends RayTraceableGroup> groupType) {
        RayTraceableGroup mockRayTraceableGroup = mockRayTraceableGroup(groupType);

        Stream<RayTraceable> flatMappedStream = flatMapper.apply(mockRayTraceableGroup);
        assertStreamContents(flatMappedStream, mockRayTraceableGroup);

        Mockito.verify(mockRayTraceableGroup, Mockito.never()).contents();
    }

    private void testNonRecursiveFlatMapperDoesNotFlatMapRayTraceableGroupInsideRayTraceableGroup(RayTraceableGroupFlatMapper flatMapper, Class<? extends RayTraceableGroup> groupType) {
        RayTraceable mockRayTraceable = mockRayTraceable();
        RayTraceableGroup mockInnerRayTraceableGroup = mockRayTraceableGroup(groupType, mockRayTraceable);
        RayTraceableGroup mockRayTraceableGroup = mockRayTraceableGroup(groupType, mockInnerRayTraceableGroup);

        Stream<RayTraceable> flatMappedStream = flatMapper.apply(mockRayTraceableGroup);
        assertStreamContents(flatMappedStream, mockInnerRayTraceableGroup);

        Mockito.verify(mockRayTraceableGroup, Mockito.times(1)).contents();
        Mockito.verify(mockInnerRayTraceableGroup, Mockito.never()).contents();
    }

    private void testRecursiveFlatMapperReturnsDeepestRayTraceableInsideRayTraceableGroup(RayTraceableGroupFlatMapper flatMapper, Class<? extends RayTraceableGroup> groupType) {
        RayTraceable mockRayTraceable = mockRayTraceable();
        RayTraceableGroup mockInnerRayTraceableGroup = mockRayTraceableGroup(groupType, mockRayTraceable);
        RayTraceableGroup mockRayTraceableGroup = mockRayTraceableGroup(groupType, mockInnerRayTraceableGroup);

        Stream<RayTraceable> flatMappedStream = flatMapper.apply(mockRayTraceableGroup);
        assertStreamContents(flatMappedStream, mockRayTraceable);

        Mockito.verify(mockRayTraceableGroup, Mockito.times(1)).contents();
        Mockito.verify(mockInnerRayTraceableGroup, Mockito.times(1)).contents();
    }

    private static void assertStreamContents(Stream<RayTraceable> stream, RayTraceable... expectedContents) {
        List<RayTraceable> streamContents = stream.collect(Collectors.toList());
        Assertions.assertEquals(expectedContents.length, streamContents.size());

        for (int i = 0; i < expectedContents.length; ++i) {
            Assertions.assertSame(expectedContents[i], streamContents.get(i));
        }
    }

    interface SpecificRayTraceableGroup extends RayTraceableGroup {}

    private static RayTraceableGroup mockRayTraceableGroup(Class<? extends RayTraceableGroup> groupType, RayTraceable... contents) {
        RayTraceableGroup mockedRayTraceableGroup = Mockito.mock(groupType);
        Mockito.doReturn(Arrays.stream(contents)).when(mockedRayTraceableGroup).contents();
        return mockedRayTraceableGroup;
    }

    private static RayTraceable mockRayTraceable() {
        return Mockito.mock(RayTraceable.class);
    }

}
