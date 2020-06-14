package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.helpers.VerboseRaytracing;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleTracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.stream.Stream;

public class TraceableAABBTest extends AbstractBoundedAABBTest {

    protected static final double DELTA = 0.000001;

    @Override
    protected TraceableAABB createInstance(final Vector3.Accessible minimum, final Vector3.Accessible maximum) {
        return new TraceableAABB(minimum, maximum);
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromOutsideOfAABB")
    public void intersectsShouldReturnTrueIfRayFormOutsideOfAABBIntersectsWithAABBAndPassesDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(true).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertTrue(aabb.intersects(ray, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(
                AdditionalMatchers.eq(1.0, DELTA),
                AdditionalMatchers.eq(3.0, DELTA)
        );
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromInsideOfAABB")
    public void intersectsShouldReturnTrueIfRayFormInsideOfAABBIntersectsWithAABBAndPassesDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(true).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertTrue(aabb.intersects(ray, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(
                AdditionalMatchers.eq(-1.0, DELTA),
                AdditionalMatchers.eq(+1.0, DELTA)
        );
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromOutsideOfAABB")
    public void intersectsShouldReturnFalseIfRayIntersectsWithAABBButDoesNotPassDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(false).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertFalse(aabb.intersects(ray, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());
    }

    @ParameterizedTest
    @MethodSource("nonIntersectingRays")
    public void intersectsShouldReturnFalseIfRayDoesNotIntersectWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        Assertions.assertFalse(aabb.intersects(ray, depthTest));
        Mockito.verifyNoInteractions(depthTest);
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromOutsideOfAABB")
    public void intersectsShouldReturnTrueIfTracingRayContextFromOutsideOfAABBIntersectsWithAABBAndPassesDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(true).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertTrue(aabb.intersects(tracingRayContext, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(
                AdditionalMatchers.eq(1.0, DELTA),
                AdditionalMatchers.eq(3.0, DELTA)
        );
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromInsideOfAABB")
    public void intersectsShouldReturnTrueIfTracingRayContextFromInsideOfAABBIntersectsWithAABBAndPassesDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(true).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertTrue(aabb.intersects(tracingRayContext, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(
                AdditionalMatchers.eq(-1.0, DELTA),
                AdditionalMatchers.eq(+1.0, DELTA)
        );
    }

    @ParameterizedTest
    @MethodSource("intersectingRaysFromOutsideOfAABB")
    public void intersectsShouldReturnFalseIfTracingRayContextIntersectsWithAABBButDoesNotPassDepthTest(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);
        DepthTest depthTest = Mockito.mock(DepthTest.class);
        Mockito.doReturn(false).when(depthTest).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());

        Assertions.assertFalse(aabb.intersects(tracingRayContext, depthTest));
        Mockito.verify(depthTest, Mockito.times(1)).testDepthRange(Mockito.anyDouble(), Mockito.anyDouble());
    }

    @ParameterizedTest
    @MethodSource("nonIntersectingRays")
    public void intersectsShouldReturnFalseIfTracingRayContextDoesNotIntersectWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        Assertions.assertFalse(aabb.intersects(tracingRayContext, depthTest));
        Mockito.verifyNoInteractions(depthTest);
    }

    private static Stream<Ray> intersectingRaysFromOutsideOfAABB() {
        return Arrays.stream(Axis.values()).map(axis -> new SimpleRay(VecMath.multiply(
                axis, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz
        ), axis))
                .map(VerboseRaytracing::verbose);
    }

    private static Stream<Ray> intersectingRaysFromInsideOfAABB() {
        return Arrays.stream(Axis.values()).map(axis -> new SimpleRay(Vectors.VECTOR3_ZERO_ZERO_ZERO, axis))
                .map(VerboseRaytracing::verbose);
    }

    private static Stream<Ray> nonIntersectingRays() {
        return Arrays.stream(Axis.values()).flatMap(axis -> Arrays.stream(Axis.values())
                .filter(a -> VecMath.dot(axis, a) == 0.0).map(a -> new SimpleRay(
                        VecMathExtended.addProducts(axis, -2.0, a, 1.000001, Factories.FACTORY_CONST_VECTOR3_xyz),
                        axis
                ))
        ).map(VerboseRaytracing::verbose);
    }

}
