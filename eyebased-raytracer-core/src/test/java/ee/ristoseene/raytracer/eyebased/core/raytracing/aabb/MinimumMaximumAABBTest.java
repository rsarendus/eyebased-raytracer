package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.helpers.VerboseVecMath;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public abstract class MinimumMaximumAABBTest {

    protected static final Vector3.Accessible MINIMUMS_FOR_TESTING = new ImmutableVector3(-1.1, -2.2, -3.3);
    protected static final Vector3.Accessible MAXIMUMS_FOR_TESTING = new ImmutableVector3(+4.4, +5.5, +6.6);

    protected abstract MinimumMaximumAABB createInstance(Vector3.Accessible p0, Vector3.Accessible p1);

    @Test
    public void getMinimumXShouldReturnTheMinimumX() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MINIMUMS_FOR_TESTING.x(), aabb.getMinimumX());
    }

    @Test
    public void getMaximumXShouldReturnTheMaximumX() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MAXIMUMS_FOR_TESTING.x(), aabb.getMaximumX());
    }

    @Test
    public void getMinimumYShouldReturnTheMinimumY() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MINIMUMS_FOR_TESTING.y(), aabb.getMinimumY());
    }

    @Test
    public void getMaximumYShouldReturnTheMaximumY() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MAXIMUMS_FOR_TESTING.y(), aabb.getMaximumY());
    }

    @Test
    public void getMinimumZShouldReturnTheMinimumZ() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MINIMUMS_FOR_TESTING.z(), aabb.getMinimumZ());
    }

    @Test
    public void getMaximumZShouldReturnTheMaximumZ() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertEquals(MAXIMUMS_FOR_TESTING.z(), aabb.getMaximumZ());
    }

    @Test
    public void getMinimumShouldReturnVectorOfMinimums() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        VecMathAssertions.assertEquals(MINIMUMS_FOR_TESTING, aabb.getMinimum(), 0.0);
    }

    @Test
    public void getMaximumShouldReturnVectorOfMaximums() {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        VecMathAssertions.assertEquals(MAXIMUMS_FOR_TESTING, aabb.getMaximum(), 0.0);
    }

    @ParameterizedTest
    @MethodSource("positionsBetweenMinimumsAndMaximumsForTesting")
    public void isInBoundsShouldReturnTrueIfPositionIsInsideAABB(final Vector3.Accessible position) {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertTrue(aabb.isInBounds(position));
    }

    protected static Stream<Vector3.Accessible> positionsBetweenMinimumsAndMaximumsForTesting() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return Stream.of(
                Vectors.VECTOR3_ZERO_ZERO_ZERO,
                new ImmutableVector3(random.nextDouble(), 0.0, 0.0),
                new ImmutableVector3(1.0, 0.0, 0.0),
                new ImmutableVector3(1.0, random.nextDouble(), random.nextDouble()),
                new ImmutableVector3(0.0, random.nextDouble(), 0.0),
                new ImmutableVector3(0.0, 1.0, 0.0),
                new ImmutableVector3(random.nextDouble(), 1.0, random.nextDouble()),
                new ImmutableVector3(0.0, 0.0, random.nextDouble()),
                new ImmutableVector3(0.0, 0.0, 1.0),
                new ImmutableVector3(random.nextDouble(), random.nextDouble(), 1.0),
                new ImmutableVector3(random.nextDouble(), random.nextDouble(), 0.0),
                new ImmutableVector3(1.0, 1.0, 0.0),
                new ImmutableVector3(1.0, 1.0, random.nextDouble()),
                new ImmutableVector3(random.nextDouble(), 0.0, random.nextDouble()),
                new ImmutableVector3(1.0, 0.0, 1.0),
                new ImmutableVector3(1.0, random.nextDouble(), 1.0),
                new ImmutableVector3(0.0, random.nextDouble(), random.nextDouble()),
                new ImmutableVector3(0.0, 1.0, 1.0),
                new ImmutableVector3(random.nextDouble(), 1.0, 1.0),
                Vectors.VECTOR3_ONE_ONE_ONE
        )
                .map(t -> VecMath.lerp(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING, t, Factories.FACTORY_CONST_VECTOR3_xyz))
                .map(VerboseVecMath::verbose);
    }

    @ParameterizedTest
    @MethodSource("positionsOutsideOfMinimumsAndMaximumsForTesting")
    public void isInBoundsShouldReturnFalseIfPositionIsOutsideOfAABB(final Vector3.Accessible position) {
        AABB aabb = createInstance(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING);

        Assertions.assertFalse(aabb.isInBounds(position));
    }

    protected static Stream<Vector3.Accessible> positionsOutsideOfMinimumsAndMaximumsForTesting() {
        return Stream.of(
                new ImmutableVector3(MINIMUMS_FOR_TESTING.x() - 0.000001, 0.0, 0.0),
                new ImmutableVector3(0.0, MINIMUMS_FOR_TESTING.y() - 0.000001, 0.0),
                new ImmutableVector3(0.0, 0.0, MINIMUMS_FOR_TESTING.z() - 0.000001),
                new ImmutableVector3(MAXIMUMS_FOR_TESTING.x() + 0.000001, 0.0, 0.0),
                new ImmutableVector3(0.0, MAXIMUMS_FOR_TESTING.y() + 0.000001, 0.0),
                new ImmutableVector3(0.0, 0.0, MAXIMUMS_FOR_TESTING.z() + 0.000001)
        ).map(VerboseVecMath::verbose);
    }

    @ParameterizedTest
    @MethodSource("mixedMinimumsAndMaximums")
    public void minimumsAndMaximumsShouldBeCorrectRegardlessOfTheirOrderDuringTheConstructionOfAABB(final List<Vector3.Accessible> p0p1) {
        AABB aabb = createInstance(p0p1.get(0), p0p1.get(1));

        VecMathAssertions.assertEquals(VecMath.min(p0p1.get(0), p0p1.get(1), Factories.FACTORY_CONST_VECTOR3_xyz), aabb.getMinimum(), 0.0);
        VecMathAssertions.assertEquals(VecMath.max(p0p1.get(0), p0p1.get(1), Factories.FACTORY_CONST_VECTOR3_xyz), aabb.getMaximum(), 0.0);
        Assertions.assertEquals(VecMath.min(p0p1.get(0).x(), p0p1.get(1).x()), aabb.getMinimumX());
        Assertions.assertEquals(VecMath.max(p0p1.get(0).x(), p0p1.get(1).x()), aabb.getMaximumX());
        Assertions.assertEquals(VecMath.min(p0p1.get(0).y(), p0p1.get(1).y()), aabb.getMinimumY());
        Assertions.assertEquals(VecMath.max(p0p1.get(0).y(), p0p1.get(1).y()), aabb.getMaximumY());
        Assertions.assertEquals(VecMath.min(p0p1.get(0).z(), p0p1.get(1).z()), aabb.getMinimumZ());
        Assertions.assertEquals(VecMath.max(p0p1.get(0).z(), p0p1.get(1).z()), aabb.getMaximumZ());
    }

    protected static Stream<List<Vector3.Accessible>> mixedMinimumsAndMaximums() {
        final Vector3.Accessible min = MINIMUMS_FOR_TESTING;
        final Vector3.Accessible max = MAXIMUMS_FOR_TESTING;
        return Stream.of(
                List.of(MINIMUMS_FOR_TESTING, MAXIMUMS_FOR_TESTING),
                List.of(new ImmutableVector3(max.x(), min.y(), min.z()), new ImmutableVector3(min.x(), max.y(), max.z())),
                List.of(new ImmutableVector3(min.x(), max.y(), min.z()), new ImmutableVector3(max.x(), min.y(), max.z())),
                List.of(new ImmutableVector3(min.x(), min.y(), max.z()), new ImmutableVector3(max.x(), max.y(), min.z())),
                List.of(new ImmutableVector3(max.x(), max.y(), min.z()), new ImmutableVector3(min.x(), min.y(), max.z())),
                List.of(new ImmutableVector3(max.x(), min.y(), max.z()), new ImmutableVector3(min.x(), max.y(), min.z())),
                List.of(new ImmutableVector3(min.x(), max.y(), max.z()), new ImmutableVector3(max.x(), min.y(), min.z())),
                List.of(MAXIMUMS_FOR_TESTING, MINIMUMS_FOR_TESTING)
        )
                .map(l -> List.of(VerboseVecMath.verbose(l.get(0)), VerboseVecMath.verbose(l.get(1))));
    }

}
