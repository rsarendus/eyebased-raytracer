package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.helpers.Matrix4x4Matcher;
import ee.ristoseene.raytracer.eyebased.core.helpers.TestTypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.GeometryContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Shadeable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingProcessor;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class AbstractShadeableRayTraceablePrimitiveTest extends AbstractRayTraceablePrimitiveTest {

    protected static final Ray TEST_INTERSECTING_RAY = new SimpleRay(new ImmutableVector3(10.0, 0.0, 0.0), Axis.NEGATIVE_X);
    protected static final double TEST_RAY_INTERSECTION_DISTANCE = 9.0;

    @Override
    protected abstract AbstractShadeableRayTraceablePrimitive createInstanceWithConfiguration(AbstractShadeableRayTraceablePrimitive.Configuration configuration);

    @Test
    public void shadeShouldRequestGeometryContextCreationAndReturnSampleValueCreatedByShadingProcessor() {
        CompiledTransform transform = new CompiledTransform(new ImmutableMatrix4x4(1.1, 2.2, 3.3, 1.0));
        AbstractShadeableRayTraceablePrimitive.Configuration configuration = createDefaultConfiguration().withTransform(transform);
        AbstractShadeableRayTraceablePrimitive shadeableRayTraceablePrimitive = createInstanceWithConfiguration(configuration);
        RayIntersectionContext rayIntersectionContext = new SimpleRayIntersectionContext(TEST_INTERSECTING_RAY, TEST_RAY_INTERSECTION_DISTANCE);

        GeometryContext geometryContext = Mockito.mock(GeometryContext.class);
        Mockito.doReturn(geometryContext).when(geometryContextFactory).create(Mockito.any(), Mockito.any(), Mockito.any());

        SampleValue shadingValue = Mockito.mock(SampleValue.class);
        ShadingProcessor shadingProcessor = Mockito.mock(ShadingProcessor.class);
        Mockito.doReturn(shadingValue).when(shadingProcessor).processShading(Mockito.any(), Mockito.any(), Mockito.any());

        SampleValue shadingResult = shadeableRayTraceablePrimitive.shade(rayIntersectionContext, shadingProcessor);

        Assertions.assertSame(shadingValue, shadingResult);
        Mockito.verify(geometryContextFactory, Mockito.times(1)).create(Mockito.same(rayIntersectionContext),
                Mockito.any(Vector3.Accessible.class), Mockito.argThat(new Matrix4x4Matcher<>(transform, 0.0)));
        Mockito.verify(shadingProcessor, Mockito.times(1)).processShading(geometryContext, rayIntersectionContext, shadingPipeline);
    }

    @Test
    public void getAttributeValueShouldReturnItselfAsShadeableWhenKeyIsShadeableKey() {
        AbstractShadeableRayTraceablePrimitive shadeableRayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());

        Shadeable resultingShadeable = shadeableRayTraceablePrimitive.getAttributeValue(Shadeable.KEY);

        Assertions.assertSame(shadeableRayTraceablePrimitive, resultingShadeable);
    }

    @Test
    public void getAttributeValueShouldReturnDefaultValueWhenKeyIsUnrelatedToShadeableRayTraceablePrimitive() {
        AbstractShadeableRayTraceablePrimitive shadeableRayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());
        TypedAttribute<String> unrelatedKey = new TestTypedAttribute<>(String.class, "Default value");

        String resultingString = shadeableRayTraceablePrimitive.getAttributeValue(unrelatedKey);

        Assertions.assertNotSame(shadeableRayTraceablePrimitive, resultingString);
        Assertions.assertEquals("Default value", resultingString);
    }

    @Test
    public void getSourceRayTraceableShouldReturnItselfAsRayTraceable() {
        AbstractShadeableRayTraceablePrimitive shadeableRayTraceablePrimitive = createInstanceWithConfiguration(createDefaultConfiguration());

        RayTraceable resultingRayTraceable = shadeableRayTraceablePrimitive.getSourceRayTraceable();

        Assertions.assertSame(shadeableRayTraceablePrimitive, resultingRayTraceable);
    }

}
