package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.geometry.helpers.TracingOrientation;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.AbstractShadeableRayTraceableFlatSurfaceTest;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class AbstractShadeableRayTraceableDiskTest extends AbstractShadeableRayTraceableFlatSurfaceTest {

    protected abstract AbstractShadeableRayTraceableDisk createInstance(AbstractShadeableRayTraceableDisk.Configuration configuration, Axis normalAxis, double diameter);

    @Override
    protected AbstractShadeableRayTraceableDisk createInstance(AbstractShadeableRayTraceableDisk.Configuration configuration, Axis normalAxis) {
        return createInstance(configuration, normalAxis, 1.0);
    }

    @Test
    public void testTracingDiskProfile() {
        TracingOrientation tracingOrientation = new TracingOrientation(2.0, Axis.POSITIVE_X, Axis.POSITIVE_Y, Axis.POSITIVE_Z);
        AbstractShadeableRayTraceableDisk disk = createInstance(createDefaultConfiguration(), Axis.NEGATIVE_X, 2.0);

        for (int y = 0; y < TEST_SAMPLE_COUNT_Y; ++y) {
            double verticalPosition = 2.0 * y / (TEST_SAMPLE_COUNT_Y - 1) - 1.0;

            for (int x = 0; x < TEST_SAMPLE_COUNT_X; ++x) {
                double horizontalPosition = 2.0 * x / (TEST_SAMPLE_COUNT_X - 1) - 1.0;
                Vector3.Accessible tracingOrigin = tracingOrientation.getTracingOrigin(horizontalPosition, verticalPosition);

                RayTracedState rayTracedState = rayTracePrimitiveAndReturnRayTracedStateMock(disk, new SimpleRay(tracingOrigin, tracingOrientation.getTracingDirection()));

                if (VecMath.length(new ImmutableVector2(horizontalPosition, verticalPosition)) < 1.0) {
                    Mockito.verify(rayTracedState, Mockito.atLeastOnce()).registerRayInteraction(Mockito.anyDouble(), Mockito.same(disk));
                    Mockito.verifyNoMoreInteractions(rayTracedState);
                } else {
                    Mockito.verifyNoInteractions(rayTracedState);
                }
            }
        }
    }

}
