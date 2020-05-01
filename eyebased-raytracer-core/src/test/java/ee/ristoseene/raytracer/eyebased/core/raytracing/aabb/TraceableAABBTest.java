package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.helpers.VerboseRaytracing;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
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

import java.util.Arrays;
import java.util.stream.Stream;

public class TraceableAABBTest extends AbstractBoundedAABBTest {

    @Override
    protected TraceableAABB createInstance(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        return new TraceableAABB(p0, p1);
    }

    @ParameterizedTest
    @MethodSource("intersectingRays")
    public void intersectsShouldReturnTrueIfRayIntersectsWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);

        Assertions.assertTrue(aabb.intersects(ray));
    }

    @ParameterizedTest
    @MethodSource("nonIntersectingRays")
    public void intersectsShouldReturnFalseIfRayDoesNotIntersectWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);

        Assertions.assertFalse(aabb.intersects(ray));
    }

    @ParameterizedTest
    @MethodSource("intersectingRays")
    public void intersectsShouldReturnTrueIfTracingRayContextIntersectsWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);

        Assertions.assertTrue(aabb.intersects(tracingRayContext));
    }

    @ParameterizedTest
    @MethodSource("nonIntersectingRays")
    public void intersectsShouldReturnFalseIfTracingRayContextDoesNotIntersectWithAABB(final Ray ray) {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(ray);

        Assertions.assertFalse(aabb.intersects(tracingRayContext));
    }

    private static Stream<Ray> intersectingRays() {
        return Stream.concat(
                Arrays.stream(Axis.values()).map(axis -> new SimpleRay(VecMath.multiply(
                        axis, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz
                ), axis)),
                Arrays.stream(Axis.values()).map(axis -> new SimpleRay(Vectors.VECTOR3_ZERO_ZERO_ZERO, axis))
        ).map(VerboseRaytracing::verbose);
    }

    private static Stream<Ray> nonIntersectingRays() {
        return Stream.concat(
                Arrays.stream(Axis.values()).map(axis -> new SimpleRay(VecMath.multiply(
                        axis, 2.0, Factories.FACTORY_CONST_VECTOR3_xyz
                ), axis)),
                Arrays.stream(Axis.values()).flatMap(axis -> Arrays.stream(Axis.values())
                        .filter(a -> VecMath.dot(axis, a) == 0.0).map(a -> new SimpleRay(
                                VecMathExtended.addProducts(axis, -2.0, a, 1.000001, Factories.FACTORY_CONST_VECTOR3_xyz),
                                axis
                        )))
        ).map(VerboseRaytracing::verbose);
    }

}
