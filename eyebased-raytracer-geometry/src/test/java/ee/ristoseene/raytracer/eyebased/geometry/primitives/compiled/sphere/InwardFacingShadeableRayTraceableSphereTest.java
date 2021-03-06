package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.vecmath.VecMath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

public class InwardFacingShadeableRayTraceableSphereTest extends AbstractShadeableRayTraceableSphereTest {

    @Override
    protected InwardFacingShadeableRayTraceableSphere createInstance(final InwardFacingShadeableRayTraceableSphere.Configuration configuration, final double diameter) {
        return new InwardFacingShadeableRayTraceableSphere(configuration, diameter);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromOutsideTheSphere(final Axis tracingDirection) {
        InwardFacingShadeableRayTraceableSphere sphere = createInstance(createDefaultConfiguration(), 2.0);
        Ray tracingRay = new SimpleRay(VecMath.multiply(tracingDirection, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz), tracingDirection);

        RayTracedState rayTracedState = rayTracePrimitiveAndReturnRayTracedStateMock(sphere, tracingRay);

        Mockito.verify(rayTracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(3.0, 0.000001), Mockito.same(sphere));
        Mockito.verifyNoMoreInteractions(rayTracedState);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromInsideTheSphere(final Axis tracingDirection) {
        InwardFacingShadeableRayTraceableSphere sphere = createInstance(createDefaultConfiguration(), 2.0);
        Ray tracingRay = new SimpleRay(Vectors.VECTOR3_ZERO_ZERO_ZERO, tracingDirection);

        RayTracedState rayTracedState = rayTracePrimitiveAndReturnRayTracedStateMock(sphere, tracingRay);

        Mockito.verify(rayTracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(1.0, 0.000001), Mockito.same(sphere));
        Mockito.verifyNoMoreInteractions(rayTracedState);
    }

}
