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

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public abstract class AbstractBoundedAABBTest {

    protected static final Vector3.Accessible MINIMUMS_FOR_TESTING = new ImmutableVector3(-1.1, -2.2, -3.3);
    protected static final Vector3.Accessible MAXIMUMS_FOR_TESTING = new ImmutableVector3(+4.4, +5.5, +6.6);

    protected abstract AbstractBoundedAABB createInstance(Vector3.Accessible minimum, Vector3.Accessible maximum);

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

}
