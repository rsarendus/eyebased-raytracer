package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.AbstractShadeableRayTraceablePrimitiveTest;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector2;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractShadeableRayTraceableSphereTest extends AbstractShadeableRayTraceablePrimitiveTest {

    protected static final int TEST_SAMPLE_COUNT_X = 32;
    protected static final int TEST_SAMPLE_COUNT_Y = 32;

    protected abstract AbstractShadeableRayTraceableSphere createInstance(AbstractShadeableRayTraceableSphere.Configuration configuration, double diameter);

    @Override
    protected AbstractShadeableRayTraceableSphere createInstanceWithConfiguration(AbstractShadeableRayTraceableSphere.Configuration configuration) {
        return createInstance(configuration, 1.0);
    }

    @ParameterizedTest
    @MethodSource("tracingOrientations")
    public void testTracingSphereProfile(final TracingOrientation tracingOrientation) {
        AbstractShadeableRayTraceableSphere sphere = createInstance(createDefaultConfiguration(), 2.0);

        for (int y = 0; y < TEST_SAMPLE_COUNT_Y; ++y) {
            double verticalPosition = 2.0 * y / (TEST_SAMPLE_COUNT_Y - 1) - 1.0;

            for (int x = 0; x < TEST_SAMPLE_COUNT_X; ++x) {
                double horizontalPosition = 2.0 * x / (TEST_SAMPLE_COUNT_X - 1) - 1.0;
                Vector3.Accessible tracingOrigin = tracingOrientation.getOrigin(horizontalPosition, verticalPosition);

                TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(sphere, new SimpleRay(tracingOrigin, tracingOrientation.tracingDirection));

                if (VecMath.length(new ImmutableVector2(horizontalPosition, verticalPosition)) < 1.0) {
                    Mockito.verify(tracedState, Mockito.atLeastOnce()).registerRayInteraction(Mockito.anyDouble(), Mockito.same(sphere));
                    Mockito.verifyNoMoreInteractions(tracedState);
                } else {
                    Mockito.verifyNoInteractions(tracedState);
                }
            }
        }
    }

    static Stream<TracingOrientation> tracingOrientations() {
        return Stream.of(
                new TracingOrientation(Axis.POSITIVE_X, Axis.POSITIVE_Y, Axis.POSITIVE_Z),
                new TracingOrientation(Axis.NEGATIVE_X, Axis.NEGATIVE_Y, Axis.NEGATIVE_Z),
                new TracingOrientation(Axis.POSITIVE_Y, Axis.POSITIVE_Z, Axis.POSITIVE_X),
                new TracingOrientation(Axis.NEGATIVE_Y, Axis.NEGATIVE_Z, Axis.NEGATIVE_X),
                new TracingOrientation(Axis.POSITIVE_Z, Axis.POSITIVE_X, Axis.POSITIVE_Y),
                new TracingOrientation(Axis.NEGATIVE_Z, Axis.NEGATIVE_X, Axis.NEGATIVE_Y)
        );
    }

    static class TracingOrientation {

        public final Axis tracingDirection, horizontalAxis, verticalAxis;

        public TracingOrientation(final Axis tracingDirection, final Axis horizontalAxis, final Axis verticalAxis) {
            this.tracingDirection = Objects.requireNonNull(tracingDirection);
            this.horizontalAxis = Objects.requireNonNull(horizontalAxis);
            this.verticalAxis = Objects.requireNonNull(verticalAxis);
        }

        public Vector3.Accessible getOrigin(final double x, final double y) {
            return new ImmutableVector3(
                    -2.0 * tracingDirection.x() + horizontalAxis.x() * x + verticalAxis.x() * y,
                    -2.0 * tracingDirection.y() + horizontalAxis.y() * x + verticalAxis.y() * y,
                    -2.0 * tracingDirection.z() + horizontalAxis.z() * x + verticalAxis.z() * y
            );
        }

        @Override
        public String toString() {
            return tracingDirection.name();
        }

    }

}
