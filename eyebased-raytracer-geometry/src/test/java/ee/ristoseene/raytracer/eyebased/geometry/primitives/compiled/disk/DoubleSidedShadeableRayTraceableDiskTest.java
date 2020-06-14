package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.vecmath.VecMath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

import java.util.Arrays;

public class DoubleSidedShadeableRayTraceableDiskTest extends AbstractShadeableRayTraceableDiskTest {

    @Override
    protected DoubleSidedShadeableRayTraceableDisk createInstance(final DoubleSidedShadeableRayTraceableDisk.Configuration configuration, final Axis normalAxis, final double diameter) {
        return new DoubleSidedShadeableRayTraceableDisk(normalAxis, configuration, diameter);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromFrontOfTheDisk(final Axis tracingDirection) {
        Axis normalAxis = Arrays.stream(Axis.values()).filter(axis -> VecMath.dot(tracingDirection, axis) == -1.0).findAny().get();
        DoubleSidedShadeableRayTraceableDisk disk = createInstance(createDefaultConfiguration(), normalAxis, 2.0);
        Ray tracingRay = new SimpleRay(VecMath.multiply(tracingDirection, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz), tracingDirection);

        TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(disk, tracingRay);

        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(2.0, 0.000001), Mockito.same(disk));
        Mockito.verifyNoMoreInteractions(tracedState);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void testTracingFromBackOfTheDisk(final Axis tracingDirection) {
        DoubleSidedShadeableRayTraceableDisk disk = createInstance(createDefaultConfiguration(), tracingDirection, 2.0);
        Ray tracingRay = new SimpleRay(VecMath.multiply(tracingDirection, -2.0, Factories.FACTORY_CONST_VECTOR3_xyz), tracingDirection);

        TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(disk, tracingRay);

        Mockito.verify(tracedState, Mockito.times(1)).registerRayInteraction(AdditionalMatchers.eq(2.0, 0.000001), Mockito.same(disk));
        Mockito.verifyNoMoreInteractions(tracedState);
    }

}
