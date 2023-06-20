package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleTracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractRayTraceablePrimitiveTest {

    @Mock
    protected RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;

    @Test
    public void rayTraceablePrimitiveShouldNotAllowMissingConfiguration() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(null)
        );

        Assertions.assertEquals("Configuration not provided", exception.getMessage());
    }

    @Test
    public void rayTraceablePrimitiveShouldNotAllowMissingTransform() {
        AbstractRayTraceablePrimitive.Configuration configuration = createDefaultConfiguration()
                .withTransform(null);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createInstanceWithConfiguration(configuration)
        );

        Assertions.assertEquals("Transform not provided", exception.getMessage());
    }

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
    public void rayTraceablePrimitiveShouldCompileToItself() {
        AbstractRayTraceablePrimitive rayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);

        CompiledGeometry compilationResult = rayTraceablePrimitive.compile(Optional.of(compilationCache));

        Assertions.assertSame(rayTraceablePrimitive, compilationResult);
        Mockito.verifyNoInteractions(compilationCache);
    }

    @Test
    public void getTransformShouldReturnTheAssignedTransform() {
        CompiledTransform compiledTransform = new CompiledTransform(new ImmutableMatrix4x4(7.3));
        AbstractRayTraceablePrimitive rayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration()
                .withTransform(compiledTransform));

        Assertions.assertSame(compiledTransform, rayTraceablePrimitive.getTransform());
    }

    @Test
    public void getGeometryContextFactoryShouldReturnTheAssignedSharedContextFactory() {
        AbstractRayTraceablePrimitive rayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());

        Assertions.assertSame(geometryContextFactory, rayTraceablePrimitive.getGeometryContextFactory());
    }

    protected RayTracedState rayTracePrimitiveAndReturnRayTracedStateMock(final AbstractRayTraceablePrimitive primitiveToRayTrace, final Ray tracingRay) {
        TracingRayContext tracingRayContext = new SimpleTracingRayContext(tracingRay);
        RayTracedState rayTracedState = Mockito.mock(RayTracedState.class);

        primitiveToRayTrace.interactWith(tracingRayContext, rayTracedState);

        return rayTracedState;
    }

    protected abstract AbstractRayTraceablePrimitive createInstanceWithConfiguration(AbstractRayTraceablePrimitive.Configuration configuration);

    protected AbstractRayTraceablePrimitive.Configuration createDefaultConfiguration() {
        return new AbstractRayTraceablePrimitive.Configuration()
                .withTransform(CompiledTransform.IDENTITY_TRANSFORM)
                .withGeometryContextFactory(geometryContextFactory);
    }

}
