package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultTransformingRayProducerTest extends TransformingRayProducerTest {

    @Mock
    private RayOriginProducer rayOriginProducer;
    @Mock
    private RayDirectionProducer rayDirectionProducer;

    @Override
    protected DefaultTransformingRayProducer createRayProducerWithTransformer(final CompiledTransform transformer) {
        return new DefaultTransformingRayProducer(transformer, rayOriginProducer, rayDirectionProducer);
    }

    @Test
    public void rayProducerShouldNotAllowMissingRayOriginProducer() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultTransformingRayProducer(CompiledTransform.IDENTITY_TRANSFORM, null, rayDirectionProducer)
        );

        Assertions.assertEquals("Ray origin producer not provided", exception.getMessage());
    }

    @Test
    public void rayProducerShouldNotAllowMissingRayDirectionProducer() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DefaultTransformingRayProducer(CompiledTransform.IDENTITY_TRANSFORM, rayOriginProducer, null)
        );

        Assertions.assertEquals("Ray direction producer not provided", exception.getMessage());
    }

    @Test
    public void produceRayShouldReturnRayWithUnchangedOriginAndDirection() {
        Vector3.Accessible rayOrigin = new ImmutableVector3(1.1, 2.2, 3.3);
        Vector3.Accessible rayDirection = new ImmutableVector3(4.4, 5.5, 6.6);
        Mockito.doReturn(rayOrigin).when(rayOriginProducer).produceOrigin(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(rayDirection).when(rayDirectionProducer).produceDirection(Mockito.anyDouble(), Mockito.anyDouble());
        TracingRayProducer rayProducer = createRayProducerWithTransformer(CompiledTransform.IDENTITY_TRANSFORM);

        Ray result = rayProducer.produceRay(3.7, 7.3);

        Assertions.assertNotNull(result);
        VecMathAssertions.assertEquals(rayOrigin, result.getOrigin(), 0.0000001);
        VecMathAssertions.assertEquals(rayDirection, result.getDirection(), 0.0000001);
        Mockito.verify(rayOriginProducer, Mockito.times(1)).produceOrigin(3.7, 7.3);
        Mockito.verify(rayDirectionProducer, Mockito.times(1)).produceDirection(3.7, 7.3);
        Mockito.verifyNoMoreInteractions(rayOriginProducer, rayDirectionProducer);
    }

    @Test
    public void produceRayShouldReturnRayWithTransformedOriginAndDirection() {
        Vector3.Accessible rayOrigin = new ImmutableVector3(1.1, 2.2, 3.3);
        Vector3.Accessible rayDirection = new ImmutableVector3(4.4, 5.5, 6.6);
        Mockito.doReturn(rayOrigin).when(rayOriginProducer).produceOrigin(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(rayDirection).when(rayDirectionProducer).produceDirection(Mockito.anyDouble(), Mockito.anyDouble());
        CompiledTransform transformer = new CompiledTransform(new ImmutableMatrix4x4(4.3));
        TracingRayProducer rayProducer = createRayProducerWithTransformer(transformer);

        Ray result = rayProducer.produceRay(3.7, 7.3);

        Assertions.assertNotNull(result);
        VecMathAssertions.assertEquals(
                VecMath.transformPosition(transformer, rayOrigin, Factories.FACTORY_CONST_VECTOR3_xyz),
                result.getOrigin(), 0.000001
        );
        VecMathAssertions.assertEquals(
                VecMath.transformDirection(transformer, rayDirection, Factories.FACTORY_CONST_VECTOR3_xyz),
                result.getDirection(), 0.0000001
        );
        Mockito.verify(rayOriginProducer, Mockito.times(1)).produceOrigin(3.7, 7.3);
        Mockito.verify(rayDirectionProducer, Mockito.times(1)).produceDirection(3.7, 7.3);
        Mockito.verifyNoMoreInteractions(rayOriginProducer, rayDirectionProducer);
    }

}
