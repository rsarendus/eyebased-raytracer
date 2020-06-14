package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.helpers.Vector3Matcher;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class AABBBuilderTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("allGetterMethodCalls")
    public void getShouldFailWhenNothingAddedToBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );
    }

    static Stream<Arguments> allGetterMethodCalls() {
        return Stream.of(
                Arguments.of("getMinimumX", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumX())),
                Arguments.of("getMaximumX", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumX())),
                Arguments.of("getMinimumY", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumY())),
                Arguments.of("getMaximumY", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumY())),
                Arguments.of("getMinimumZ", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumZ())),
                Arguments.of("getMaximumZ", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumZ())),
                Arguments.of("getMinimum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimum())),
                Arguments.of("getMaximum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximum()))
        );
    }

    @Test
    public void buildShouldFailWhenNothingAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        AABBBuilder.Factory<AABB> aabbFactory = createFactoryMock();

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> aabbBuilder.build(aabbFactory)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMinimumMethodCallsX")
    public void getMinimumShouldFailWhenXNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(Double.NaN, 0.0, 0.0));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Minimum X not set", exception.getMessage());
    }

    static Stream<Arguments> getMinimumMethodCallsX() {
        return Stream.of(
                Arguments.of("getMinimumX", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumX())),
                Arguments.of("getMinimum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimum()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMaximumMethodCallsX")
    public void getMaximumShouldFailWhenXNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(Double.NaN, 0.0, 0.0));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Maximum X not set", exception.getMessage());
    }

    static Stream<Arguments> getMaximumMethodCallsX() {
        return Stream.of(
                Arguments.of("getMaximumX", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumX())),
                Arguments.of("getMaximum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximum()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMinimumMethodCallsY")
    public void getMinimumShouldFailWhenYNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(0.0, Double.NaN, 0.0));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Minimum Y not set", exception.getMessage());
    }

    static Stream<Arguments> getMinimumMethodCallsY() {
        return Stream.of(
                Arguments.of("getMinimumY", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumY())),
                Arguments.of("getMinimum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimum()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMaximumMethodCallsY")
    public void getMaximumShouldFailWhenYNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(0.0, Double.NaN, 0.0));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Maximum Y not set", exception.getMessage());
    }

    static Stream<Arguments> getMaximumMethodCallsY() {
        return Stream.of(
                Arguments.of("getMaximumY", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumY())),
                Arguments.of("getMaximum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximum()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMinimumMethodCallsZ")
    public void getMinimumShouldFailWhenZNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(0.0, 0.0, Double.NaN));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Minimum Z not set", exception.getMessage());
    }

    static Stream<Arguments> getMinimumMethodCallsZ() {
        return Stream.of(
                Arguments.of("getMinimumZ", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimumZ())),
                Arguments.of("getMinimum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMinimum()))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getMaximumMethodCallsZ")
    public void getMaximumShouldFailWhenZNotSetInBuilder(String methodName, Consumer<AABBBuilder> methodCall) {
        AABBBuilder aabbBuilder = new AABBBuilder();
        aabbBuilder.addPoint(new ImmutableVector3(0.0, 0.0, Double.NaN));

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> methodCall.accept(aabbBuilder)
        );

        Assertions.assertEquals("Maximum Z not set", exception.getMessage());
    }

    static Stream<Arguments> getMaximumMethodCallsZ() {
        return Stream.of(
                Arguments.of("getMaximumZ", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximumZ())),
                Arguments.of("getMaximum", (Consumer<AABBBuilder>) ((AABBBuilder aabbBuilder) -> aabbBuilder.getMaximum()))
        );
    }

    @Test
    public void gettersShouldReturnCoordinatesOfThePointAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        Vector3.Accessible point = new ImmutableVector3(1.3, -2.2, 3.1);
        aabbBuilder.addPoint(point);

        Assertions.assertEquals(point.x(), aabbBuilder.getMinimumX());
        Assertions.assertEquals(point.x(), aabbBuilder.getMaximumX());
        Assertions.assertEquals(point.y(), aabbBuilder.getMinimumY());
        Assertions.assertEquals(point.y(), aabbBuilder.getMaximumY());
        Assertions.assertEquals(point.z(), aabbBuilder.getMinimumZ());
        Assertions.assertEquals(point.z(), aabbBuilder.getMaximumZ());
        VecMathAssertions.assertEquals(point, aabbBuilder.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(point, aabbBuilder.getMaximum(), 0.0);
    }

    @Test
    public void buildShouldCreateAABBBasedOnThePointAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        AABB aabbCreationResult = Mockito.mock(AABB.class);
        AABBBuilder.Factory<AABB> aabbFactory = createFactoryMock(aabbCreationResult);
        Vector3.Accessible point = new ImmutableVector3(1.3, -2.2, 3.1);
        aabbBuilder.addPoint(point);

        AABB result = aabbBuilder.build(aabbFactory);

        Assertions.assertSame(aabbCreationResult, result);
        Mockito.verify(aabbFactory, Mockito.times(1)).create(
                Mockito.argThat(new Vector3Matcher<>(point, 0.0)),
                Mockito.argThat(new Vector3Matcher<>(point, 0.0))
        );
        Mockito.verifyNoMoreInteractions(aabbFactory);
    }

    @Test
    public void gettersShouldReturnCorrectCoordinatesBasedOnThePointsAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        Vector3.Accessible point1 = new ImmutableVector3(1.3, -2.2, 3.1);
        Vector3.Accessible point2 = new ImmutableVector3(-4.6, 5.5, -6.4);
        aabbBuilder.addPoint(point1);
        aabbBuilder.addPoint(point2);

        Assertions.assertEquals(VecMath.min(point1.x(), point2.x()), aabbBuilder.getMinimumX());
        Assertions.assertEquals(VecMath.max(point1.x(), point2.x()), aabbBuilder.getMaximumX());
        Assertions.assertEquals(VecMath.min(point1.y(), point2.y()), aabbBuilder.getMinimumY());
        Assertions.assertEquals(VecMath.max(point1.y(), point2.y()), aabbBuilder.getMaximumY());
        Assertions.assertEquals(VecMath.min(point1.z(), point2.z()), aabbBuilder.getMinimumZ());
        Assertions.assertEquals(VecMath.max(point1.z(), point2.z()), aabbBuilder.getMaximumZ());
        VecMathAssertions.assertEquals(VecMath.min(point1, point2, Factories.FACTORY_CONST_VECTOR3_xyz), aabbBuilder.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(VecMath.max(point1, point2, Factories.FACTORY_CONST_VECTOR3_xyz), aabbBuilder.getMaximum(), 0.0);
    }

    @Test
    public void buildShouldCreateAABBBasedOnThePointsAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        AABB aabbCreationResult = Mockito.mock(AABB.class);
        AABBBuilder.Factory<AABB> aabbFactory = createFactoryMock(aabbCreationResult);
        Vector3.Accessible point1 = new ImmutableVector3(1.3, -2.2, 3.1);
        Vector3.Accessible point2 = new ImmutableVector3(-4.6, 5.5, -6.4);
        aabbBuilder.addPoint(point1);
        aabbBuilder.addPoint(point2);

        AABB result = aabbBuilder.build(aabbFactory);

        Assertions.assertSame(aabbCreationResult, result);
        Mockito.verify(aabbFactory, Mockito.times(1)).create(
                Mockito.argThat(new Vector3Matcher<>(VecMath.min(point1, point2, Factories.FACTORY_CONST_VECTOR3_xyz), 0.0)),
                Mockito.argThat(new Vector3Matcher<>(VecMath.max(point1, point2, Factories.FACTORY_CONST_VECTOR3_xyz), 0.0))
        );
        Mockito.verifyNoMoreInteractions(aabbFactory);
    }

    @Test
    public void gettersShouldReturnCorrectCoordinatesBasedOnTheAABBAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        AABB aabb = new TraceableAABB(
                new ImmutableVector3(-4.6, -2.2, -6.4),
                new ImmutableVector3(1.3, 5.5, 3.1)
        );
        aabbBuilder.addAABB(aabb);

        Assertions.assertEquals(aabb.getMinimumX(), aabbBuilder.getMinimumX());
        Assertions.assertEquals(aabb.getMaximumX(), aabbBuilder.getMaximumX());
        Assertions.assertEquals(aabb.getMinimumY(), aabbBuilder.getMinimumY());
        Assertions.assertEquals(aabb.getMaximumY(), aabbBuilder.getMaximumY());
        Assertions.assertEquals(aabb.getMinimumZ(), aabbBuilder.getMinimumZ());
        Assertions.assertEquals(aabb.getMaximumZ(), aabbBuilder.getMaximumZ());
        VecMathAssertions.assertEquals(aabb.getMinimum(), aabbBuilder.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(aabb.getMaximum(), aabbBuilder.getMaximum(), 0.0);
    }

    @Test
    public void buildShouldCreateAABBBasedOnTheAABBAddedToBuilder() {
        AABBBuilder aabbBuilder = new AABBBuilder();
        AABB aabbCreationResult = Mockito.mock(AABB.class);
        AABBBuilder.Factory<AABB> aabbFactory = createFactoryMock(aabbCreationResult);
        AABB aabb = new TraceableAABB(
                new ImmutableVector3(-4.6, -2.2, -6.4),
                new ImmutableVector3(1.3, 5.5, 3.1)
        );
        aabbBuilder.addAABB(aabb);

        AABB result = aabbBuilder.build(aabbFactory);

        Assertions.assertSame(aabbCreationResult, result);
        Mockito.verify(aabbFactory, Mockito.times(1)).create(
                Mockito.argThat(new Vector3Matcher<>(aabb.getMinimum(), 0.0)),
                Mockito.argThat(new Vector3Matcher<>(aabb.getMaximum(), 0.0))
        );
        Mockito.verifyNoMoreInteractions(aabbFactory);
    }

    @SuppressWarnings("unchecked")
    private static AABBBuilder.Factory<AABB> createFactoryMock(AABB creationResult) {
        AABBBuilder.Factory<AABB> aabbFactory = (AABBBuilder.Factory<AABB>) Mockito.mock(AABBBuilder.Factory.class);
        Mockito.doReturn(creationResult).when(aabbFactory).create(Mockito.any(Vector3.Accessible.class), Mockito.any(Vector3.Accessible.class));
        return aabbFactory;
    }

    @SuppressWarnings("unchecked")
    private static AABBBuilder.Factory<AABB> createFactoryMock() {
        return (AABBBuilder.Factory<AABB>) Mockito.mock(AABBBuilder.Factory.class);
    }

}
