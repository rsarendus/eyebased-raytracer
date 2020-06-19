package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleRayTraceableGroupTest extends AbstractRayTraceableGroupTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void noRayTraceablesShouldResultInEmptySimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables());

        Stream<RayTraceable> contentStream = simpleRayTraceableGroup.contents();
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(0L, contentStream.count());
        Assertions.assertTrue(groupAABB instanceof BoundlessAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void singleRayTraceableShouldResultInSingleElementSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        AABB aabb = Mockito.mock(AABB.class);
        RayTraceable rayTraceable = createRayTraceableMockWithAABB(aabb);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(rayTraceable));

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable), contentStreamList);
        Assertions.assertSame(aabb, groupAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void nullRayTraceableShouldBeCulledFromSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables((RayTraceable) null));

        Stream<RayTraceable> contentStream = simpleRayTraceableGroup.contents();
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(0L, contentStream.count());
        Assertions.assertTrue(groupAABB instanceof BoundlessAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void emptyRayTraceableGroupShouldBeCulledFromSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        RayTraceable emptyRayTraceableGroup = EmptyRayTraceableGroup.INSTANCE;
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(emptyRayTraceableGroup));

        Stream<RayTraceable> contentStream = simpleRayTraceableGroup.contents();
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(0L, contentStream.count());
        Assertions.assertTrue(groupAABB instanceof BoundlessAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void singletonRayTraceableGroupShouldBeFlattenedInSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        AABB aabb = Mockito.mock(AABB.class);
        RayTraceable rayTraceable = createRayTraceableMockWithAABB(aabb);
        RayTraceable singletonRayTraceableGroup = new SingletonRayTraceableGroup(rayTraceable);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(singletonRayTraceableGroup));

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable), contentStreamList);
        Assertions.assertSame(aabb, groupAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void recursiveSingletonRayTraceableGroupShouldBeFlattenedInSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        AABB aabb = Mockito.mock(AABB.class);
        RayTraceable rayTraceable = createRayTraceableMockWithAABB(aabb);
        RayTraceable singletonRayTraceableGroupInner = new SingletonRayTraceableGroup(rayTraceable);
        RayTraceable singletonRayTraceableGroupOuter = new SingletonRayTraceableGroup(singletonRayTraceableGroupInner);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(singletonRayTraceableGroupOuter));

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable), contentStreamList);
        Assertions.assertSame(aabb, groupAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void recurringRayTraceablesShouldBeCulledFromSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        AABB aabb = Mockito.mock(AABB.class);
        RayTraceable rayTraceable = createRayTraceableMockWithAABB(aabb);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(rayTraceable, rayTraceable));

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable), contentStreamList);
        Assertions.assertSame(aabb, groupAABB);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("singleArgumentConstructorCalls")
    public void multipleRayTraceablesShouldResultInMultipleElementSimpleRayTraceableGroup(final String constructorSignature, final Function<RayTraceable[], SimpleRayTraceableGroup> constructorCall) {
        AABB aabb1 = new TraceableAABB(new ImmutableVector3(1.0), new ImmutableVector3(1.0));
        RayTraceable rayTraceable1 = createRayTraceableMockWithAABB(aabb1);
        AABB aabb2 = new TraceableAABB(new ImmutableVector3(2.0), new ImmutableVector3(2.0));
        RayTraceable rayTraceable2 = createRayTraceableMockWithAABB(aabb2);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(rayTraceable1, rayTraceable2));

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable1, rayTraceable2), contentStreamList);
        VecMathAssertions.assertEquals(aabb1.getMinimum(), groupAABB.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(aabb2.getMaximum(), groupAABB.getMaximum(), 0.0);
    }

    static Stream<Arguments> singleArgumentConstructorCalls() {
        final Comparator<AABB> noOpSorter = createAabbSorterMock();
        return Stream.of(
                Arguments.of("(Stream<RayTraceable>)",
                        (Function<RayTraceable[], SimpleRayTraceableGroup>) (rayTraceables -> new SimpleRayTraceableGroup(Arrays.stream(rayTraceables)))
                ),
                Arguments.of("(Collection<RayTraceable>)",
                        (Function<RayTraceable[], SimpleRayTraceableGroup>) (rayTraceables -> new SimpleRayTraceableGroup(Arrays.asList(rayTraceables)))
                ),
                Arguments.of("(Stream<RayTraceable>, Comparator<AABB>)",
                        (Function<RayTraceable[], SimpleRayTraceableGroup>) (rayTraceables -> new SimpleRayTraceableGroup(Arrays.stream(rayTraceables), noOpSorter))
                ),
                Arguments.of("(Collection<RayTraceable>, Comparator<AABB>)",
                        (Function<RayTraceable[], SimpleRayTraceableGroup>) (rayTraceables -> new SimpleRayTraceableGroup(Arrays.asList(rayTraceables), noOpSorter))
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("doubleArgumentConstructorCalls")
    public void multipleRayTraceablesShouldResultInMultipleElementSimpleRayTraceableGroup(final String constructorSignature, final BiFunction<RayTraceable[], Comparator<AABB>, SimpleRayTraceableGroup> constructorCall) {
        AABB aabb1 = new TraceableAABB(new ImmutableVector3(1.0), new ImmutableVector3(1.0));
        RayTraceable rayTraceable1 = createRayTraceableMockWithAABB(aabb1);
        AABB aabb2 = new TraceableAABB(new ImmutableVector3(2.0), new ImmutableVector3(2.0));
        RayTraceable rayTraceable2 = createRayTraceableMockWithAABB(aabb2);
        Comparator<AABB> sorter = createAabbSorterMock();
        Mockito.doReturn(+1).when(sorter).compare(aabb1, aabb2);
        Mockito.doReturn(-1).when(sorter).compare(aabb2, aabb1);
        SimpleRayTraceableGroup simpleRayTraceableGroup = constructorCall.apply(rayTraceables(rayTraceable1, rayTraceable2), sorter);

        List<RayTraceable> contentStreamList = simpleRayTraceableGroup.contents().collect(Collectors.toList());
        AABB groupAABB = simpleRayTraceableGroup.getAABB();

        Assertions.assertEquals(List.of(rayTraceable2, rayTraceable1), contentStreamList);
        VecMathAssertions.assertEquals(aabb1.getMinimum(), groupAABB.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(aabb2.getMaximum(), groupAABB.getMaximum(), 0.0);
    }

    static Stream<Arguments> doubleArgumentConstructorCalls() {
        return Stream.of(
                Arguments.of("(Stream<RayTraceable>, Comparator<AABB>)",
                        (BiFunction<RayTraceable[], Comparator<AABB>, SimpleRayTraceableGroup>) ((rayTraceables, sorter) -> new SimpleRayTraceableGroup(Arrays.stream(rayTraceables), sorter))
                ),
                Arguments.of("(Collection<RayTraceable>, Comparator<AABB>)",
                        (BiFunction<RayTraceable[], Comparator<AABB>, SimpleRayTraceableGroup>) ((rayTraceables, sorter) -> new SimpleRayTraceableGroup(Arrays.asList(rayTraceables), sorter))
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    public void cullShouldReturnEmptyRayTraceableGroupWhenNoContentsPassFiltering(int contentCount) {
        AABB[] aabbs = new AABB[contentCount];
        RayTraceable[] rayTraceables = new RayTraceable[contentCount];
        for (int i = 0; i < contentCount; ++i) {
            Vector3.Accessible p = new ImmutableVector3(1.1 * i, 2.2 * i, 3.3 * i);
            aabbs[i] = new TraceableAABB(p, p);
            rayTraceables[i] = createRayTraceableMockWithAABB(aabbs[i]);
        }
        RayTraceableGroup simpleRayTraceableGroup = new SimpleRayTraceableGroup(Arrays.asList(rayTraceables));
        Predicate<AABB> filter = createAabbFilterMock(false);

        RayTraceableGroup culledRayTraceableGroup = simpleRayTraceableGroup.cull(filter, Optional.empty());

        Assertions.assertTrue(culledRayTraceableGroup instanceof EmptyRayTraceableGroup);
        for (int i = 0; i < contentCount; ++i) {
            Mockito.verify(filter, Mockito.times(1)).test(aabbs[i]);
        }
        Mockito.verifyNoMoreInteractions(filter);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    public void cullShouldReturnEquivalentRayTraceableGroupWhenAllContentsPassFiltering(int contentCount) {
        AABB[] aabbs = new AABB[contentCount];
        RayTraceable[] rayTraceables = new RayTraceable[contentCount];
        for (int i = 0; i < contentCount; ++i) {
            Vector3.Accessible p = new ImmutableVector3(1.1 * i, 2.2 * i, 3.3 * i);
            aabbs[i] = new TraceableAABB(p, p);
            rayTraceables[i] = createRayTraceableMockWithAABB(aabbs[i]);
        }
        RayTraceableGroup simpleRayTraceableGroup = new SimpleRayTraceableGroup(Arrays.asList(rayTraceables));
        Predicate<AABB> filter = createAabbFilterMock(true);

        RayTraceableGroup culledRayTraceableGroup = simpleRayTraceableGroup.cull(filter, Optional.empty());
        List<RayTraceable> contentStreamList = culledRayTraceableGroup.contents().collect(Collectors.toList());

        Assertions.assertEquals(Arrays.asList(rayTraceables), contentStreamList);
        for (int i = 0; i < contentCount; ++i) {
            Mockito.verify(filter, Mockito.times(1)).test(aabbs[i]);
        }
        Mockito.verifyNoMoreInteractions(filter);
    }

    @Test
    public void cullShouldReturnRayTraceableGroupContainingOnlyRayTraceablesThatPassFiltering() {
        AABB aabb = new TraceableAABB(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        List<RayTraceable> rayTraceables = List.of(
                createRayTraceableMockWithAABB(aabb),
                createRayTraceableMockWithAABB(aabb),
                createRayTraceableMockWithAABB(aabb),
                createRayTraceableMockWithAABB(aabb)
        );
        RayTraceableGroup simpleRayTraceableGroup = new SimpleRayTraceableGroup(rayTraceables);
        Predicate<AABB> filter = createAabbFilterMock();
        Mockito.doReturn(false, true, false, true).when(filter).test(aabb);

        RayTraceableGroup culledRayTraceableGroup = simpleRayTraceableGroup.cull(filter, Optional.empty());
        List<RayTraceable> contentStreamList = culledRayTraceableGroup.contents().collect(Collectors.toList());

        Assertions.assertEquals(List.of(rayTraceables.get(1), rayTraceables.get(3)), contentStreamList);
    }

    @Test
    public void cullShouldReturnRayTraceableGroupContainingSortedRaytraceablesWhenSorterPresent() {
        RayTraceable[] rayTraceables = {
                createRayTraceableMockWithAABB(new TraceableAABB(new ImmutableVector3(2), new ImmutableVector3(2))),
                createRayTraceableMockWithAABB(new TraceableAABB(new ImmutableVector3(1), new ImmutableVector3(1))),
                createRayTraceableMockWithAABB(new TraceableAABB(new ImmutableVector3(5), new ImmutableVector3(5))),
                createRayTraceableMockWithAABB(new TraceableAABB(new ImmutableVector3(4), new ImmutableVector3(4))),
                createRayTraceableMockWithAABB(new TraceableAABB(new ImmutableVector3(3), new ImmutableVector3(3)))
        };
        RayTraceableGroup simpleRayTraceableGroup = new SimpleRayTraceableGroup(Arrays.asList(rayTraceables));
        Predicate<AABB> filter = createAabbFilterMock(true);
        Comparator<AABB> sorter = (aabb1, aabb2) -> (int) (aabb1.getMinimumX() - aabb2.getMinimumX());

        RayTraceableGroup culledRayTraceableGroup = simpleRayTraceableGroup.cull(filter, Optional.of(sorter));
        List<RayTraceable> contentStreamList = culledRayTraceableGroup.contents().collect(Collectors.toList());

        Assertions.assertEquals(List.of(
                rayTraceables[1], rayTraceables[0], rayTraceables[4], rayTraceables[3], rayTraceables[2]
        ), contentStreamList);
    }

    @Test
    public void interactWithShouldInteractWithAllTheAABBsOfTayTraceablesInOrderAndAllTheRayTraceablesThatPassAABBTest() {
        AABB aabb1 = Mockito.spy(new TraceableAABB(new ImmutableVector3(1.1, 2.2, 3.3), new ImmutableVector3(4.4, 5.5, 6.6)));
        Mockito.doReturn(false).when(aabb1).intersects(Mockito.any(TracingRayContext.class), Mockito.any(DepthTest.class));
        RayTraceable rayTraceable1 = createRayTraceableMockWithAABB(aabb1);
        AABB aabb2 = Mockito.spy(new TraceableAABB(new ImmutableVector3(7.7, 8.8, 9.9), new ImmutableVector3(9.9, 6.6, 3.3)));
        Mockito.doReturn(true).when(aabb2).intersects(Mockito.any(TracingRayContext.class), Mockito.any(DepthTest.class));
        RayTraceable rayTraceable2 = createRayTraceableMockWithAABB(aabb2);
        RayTraceableGroup simpleRayTraceableGroup = new SimpleRayTraceableGroup(List.of(rayTraceable1, rayTraceable2));
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        RayTracedState rayTracedState = Mockito.mock(RayTracedState.class);

        simpleRayTraceableGroup.interactWith(tracingRayContext, rayTracedState);

        Mockito.verify(aabb1, Mockito.times(1)).intersects(tracingRayContext, rayTracedState);
        Mockito.verify(rayTraceable1, Mockito.times(0)).interactWith(Mockito.any(TracingRayContext.class), Mockito.any(RayTracedState.class));
        Mockito.verify(aabb2, Mockito.times(1)).intersects(tracingRayContext, rayTracedState);
        Mockito.verify(rayTraceable2, Mockito.times(1)).interactWith(tracingRayContext, rayTracedState);
    }

    private static RayTraceable[] rayTraceables(RayTraceable... rayTraceables) {
        return Objects.requireNonNull(rayTraceables);
    }

}
