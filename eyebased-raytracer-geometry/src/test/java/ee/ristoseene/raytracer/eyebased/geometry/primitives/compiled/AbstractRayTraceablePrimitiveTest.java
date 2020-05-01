package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleTracingRayContext;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.AbstractRayTraceableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.AbstractRayTraceableGeometryTest;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractRayTraceablePrimitiveTest extends AbstractRayTraceableGeometryTest {

    @Mock
    protected RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;

    @Test
    public void rayTraceablePrimitiveShouldNotAllowMissingGeometryContextFactory() {
        AbstractRayTraceablePrimitive.Configuration configuration = createDefaultConfiguration()
                .withGeometryContextFactory(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(configuration)
        );

        Assertions.assertEquals("Geometry context factory not provided", exception.getMessage());
    }

    @Test
    public void getGeometryContextFactoryShouldReturnTheAssignedSharedContextFactory() {
        AbstractRayTraceablePrimitive rayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());

        Assertions.assertSame(geometryContextFactory, rayTraceablePrimitive.getGeometryContextFactory());
    }

    protected TracedState tracePrimitiveAndReturnTracedStateMock(final AbstractRayTraceablePrimitive primitiveToTrace, final Ray tracingRay) {
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(tracingRay);
        TracedState tracedState = Mockito.mock(TracedState.class);

        primitiveToTrace.interactWith(tracingRayContext, tracedState);

        return tracedState;
    }

    protected abstract AbstractRayTraceablePrimitive createInstanceWithConfiguration(AbstractRayTraceablePrimitive.Configuration configuration);

    @Override
    protected AbstractRayTraceableGeometry createInstanceWithConfiguration(AbstractRayTraceableGeometry.Configuration configuration) {
        return createInstanceWithConfiguration((AbstractRayTraceablePrimitive.Configuration) configuration);
    }

    protected AbstractRayTraceablePrimitive.Configuration createDefaultConfiguration() {
        return new AbstractRayTraceablePrimitive.Configuration()
                .withConfiguration(super.createDefaultConfiguration())
                .withGeometryContextFactory(geometryContextFactory);
    }

}
