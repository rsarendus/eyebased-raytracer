package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.vecmath.VecMath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class DoubleSidedShadeableRayTraceableSphereTest extends AbstractShadeableRayTraceableSphereTest {

    @Override
    protected DoubleSidedShadeableRayTraceableSphere createInstance(final DoubleSidedShadeableRayTraceableSphere.Configuration configuration, final double diameter) {
        return new DoubleSidedShadeableRayTraceableSphere(configuration, diameter);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromOutsideTheSphere(final Axis tracingDirection) {
        DoubleSidedShadeableRayTraceableSphere sphere = createInstance(createDefaultConfiguration(), 2.0);
        Ray tracingRay = new SimpleRay(VecMath.multiply(tracingDirection, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz), tracingDirection);

        TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(sphere, tracingRay);

        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(1.0, 0.000001), Mockito.same(sphere));
        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(3.0, 0.000001), Mockito.same(sphere));
        Mockito.verifyNoMoreInteractions(tracedState);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromInsideTheSphere(final Axis tracingDirection) {
        DoubleSidedShadeableRayTraceableSphere sphere = createInstance(createDefaultConfiguration(), 2.0);
        Ray tracingRay = new SimpleRay(Vectors.VECTOR3_ZERO_ZERO_ZERO, tracingDirection);

        TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(sphere, tracingRay);

        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(1.0, 0.000001), Mockito.same(sphere));
        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(-1.0, 0.000001), Mockito.same(sphere));
        Mockito.verifyNoMoreInteractions(tracedState);
    }

}
