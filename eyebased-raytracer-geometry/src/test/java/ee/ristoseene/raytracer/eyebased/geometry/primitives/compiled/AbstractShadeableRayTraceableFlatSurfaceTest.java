package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.geometry.helpers.TracingOrientation;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractShadeableRayTraceableFlatSurfaceTest extends AbstractShadeableRayTraceablePrimitiveTest {

    protected static final int TEST_SAMPLE_COUNT_X = 32;
    protected static final int TEST_SAMPLE_COUNT_Y = 32;

    protected abstract AbstractShadeableRayTraceableFlatSurface createInstance(AbstractShadeableRayTraceableFlatSurface.Configuration configuration, Axis normalAxis);

    @Override
    protected AbstractShadeableRayTraceableFlatSurface createInstanceWithConfiguration(AbstractShadeableRayTraceableFlatSurface.Configuration configuration) {
        return createInstance(configuration, Axis.POSITIVE_X);
    }

    @ParameterizedTest
    @MethodSource("tracingConfigurationsForTracingFromSide")
    public void testTracingFlatSurfaceFromSide(final TracingConfiguration tracingConfiguration) {
        AbstractShadeableRayTraceableFlatSurface flatSurface = createInstance(createDefaultConfiguration(), tracingConfiguration.getNormalAxis());

        for (int y = 0; y < TEST_SAMPLE_COUNT_Y; ++y) {
            double verticalPosition = 2.0 * y / (TEST_SAMPLE_COUNT_Y - 1) - 1.0;

            for (int x = 0; x < TEST_SAMPLE_COUNT_X; ++x) {
                double horizontalPosition = 2.0 * x / (TEST_SAMPLE_COUNT_X - 1) - 1.0;
                Vector3.Accessible tracingOrigin = tracingConfiguration.getTracingOrigin(horizontalPosition, verticalPosition);

                TracedState tracedState = tracePrimitiveAndReturnTracedStateMock(flatSurface, new SimpleRay(tracingOrigin, tracingConfiguration.getTracingDirection()));

                Mockito.verifyNoInteractions(tracedState);
            }
        }
    }

    static Stream<TracingConfiguration> tracingConfigurationsForTracingFromSide() {
        return Stream.of(
                new TracingOrientation(2.0, Axis.POSITIVE_X, Axis.POSITIVE_Y, Axis.POSITIVE_Z),
                new TracingOrientation(2.0, Axis.NEGATIVE_X, Axis.NEGATIVE_Y, Axis.NEGATIVE_Z),
                new TracingOrientation(2.0, Axis.POSITIVE_Y, Axis.POSITIVE_Z, Axis.POSITIVE_X),
                new TracingOrientation(2.0, Axis.NEGATIVE_Y, Axis.NEGATIVE_Z, Axis.NEGATIVE_X),
                new TracingOrientation(2.0, Axis.POSITIVE_Z, Axis.POSITIVE_X, Axis.POSITIVE_Y),
                new TracingOrientation(2.0, Axis.NEGATIVE_Z, Axis.NEGATIVE_X, Axis.NEGATIVE_Y)
        ).flatMap(orientation -> Arrays
                .stream(Axis.values())
                .filter(normal -> VecMath.dot(orientation.getTracingDirection(), normal) == 0.0)
                .map(normal -> new TracingConfiguration(normal, orientation))
        );
    }

    static class TracingConfiguration extends TracingOrientation {

        private final Axis normalAxis;

        public TracingConfiguration(final Axis normalAxis, final TracingOrientation tracingOrientation) {
            super(tracingOrientation);
            this.normalAxis = Objects.requireNonNull(normalAxis);
        }

        public TracingConfiguration(final Axis normalAxis, final Axis tracingDirection, final Axis horizontalAxis, final Axis verticalAxis) {
            super(2.0, tracingDirection, horizontalAxis, verticalAxis);
            this.normalAxis = Objects.requireNonNull(normalAxis);
        }

        public Axis getNormalAxis() {
            return normalAxis;
        }

        @Override
        public String toString() {
            return String.format(
                    "Normal axis: %s; Tracing direction: %s",
                    getNormalAxis().name(),
                    getTracingDirection().name()
            );
        }

    }

}
