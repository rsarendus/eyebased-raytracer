package ee.ristoseene.raytracer.eyebased.geometry.primitives.utilities;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.mutable.MutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AABBFactoryTest {

    private static final double DELTA = 0.000001;

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.5, 1.0, 2.0, 11.37})
    public void createUnitSphereAABBShouldCreateCorrectlyScaledAABB(final double scale) {
        CompiledTransform transform = new CompiledTransform(new ImmutableMatrix4x4(scale, scale, scale, 1.0));

        AABB aabb = AABBFactory.createUnitSphereAABB(transform);

        Assertions.assertTrue(aabb instanceof TraceableAABB);
        VecMathAssertions.assertEquals(new ImmutableVector3(-scale, -scale, -scale), aabb.getMinimum(), DELTA);
        VecMathAssertions.assertEquals(new ImmutableVector3(+scale, +scale, +scale), aabb.getMaximum(), DELTA);
    }

    @Test
    public void createUnitSphereAABBShouldCreateCorrectlyScaledAABB() {
        CompiledTransform transform = createScaledTransform(1.3, 2.2, 3.1);

        AABB aabb = AABBFactory.createUnitSphereAABB(transform);

        Assertions.assertTrue(aabb instanceof TraceableAABB);
        VecMathAssertions.assertEquals(new ImmutableVector3(-1.3, -2.2, -3.1), aabb.getMinimum(), DELTA);
        VecMathAssertions.assertEquals(new ImmutableVector3(+1.3, +2.2, +3.1), aabb.getMaximum(), DELTA);
    }

    @Test
    public void createUnitSphereAABBShouldCreateCorrectlyPositionedAABB() {
        CompiledTransform transform = createPositionedTransform(1.3, -2.2, 3.1);

        AABB aabb = AABBFactory.createUnitSphereAABB(transform);

        Assertions.assertTrue(aabb instanceof TraceableAABB);
        VecMathAssertions.assertEquals(new ImmutableVector3(0.3, -3.2, 2.1), aabb.getMinimum(), DELTA);
        VecMathAssertions.assertEquals(new ImmutableVector3(2.3, -1.2, 4.1), aabb.getMaximum(), DELTA);
    }

    private static CompiledTransform createScaledTransform(double scaleX, double scaleY, double scaleZ) {
        return new CompiledTransform(new ImmutableMatrix4x4(scaleX, scaleY, scaleZ, 1.0));
    }

    private static CompiledTransform createPositionedTransform(double positionX, double positionY, double positionZ) {
        Matrix4x4.AccessibleAndMutable matrix = new MutableMatrix4x4(1.0, 1.0, 1.0, 1.0);
        matrix.Txyz(positionX, positionY, positionZ);
        return  new CompiledTransform(matrix);
    }

}
