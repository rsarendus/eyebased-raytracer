package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimpleRayProducerTest {

    @Mock
    private RayOriginProducer rayOriginProducer;
    @Mock
    private RayDirectionProducer rayDirectionProducer;

    @Test
    public void rayProducerShouldNotAllowMissingOriginProducer() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new SimpleRayProducer(null, rayDirectionProducer)
        );

        Assertions.assertEquals("Ray origin producer not provided", exception.getMessage());
    }

    @Test
    public void rayProducerShouldNotAllowMissingDirectionProducer() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new SimpleRayProducer(rayOriginProducer, null)
        );

        Assertions.assertEquals("Ray direction producer not provided", exception.getMessage());
    }

    @Test
    public void produceRayShouldReturnRayWithProducedOriginAndDirection() {
        Vector3.Accessible rayOrigin = new ImmutableVector3(1.1, 2.2, 3.3);
        Vector3.Accessible rayDirection = new ImmutableVector3(4.4, 5.5, 6.6);
        Mockito.doReturn(rayOrigin).when(rayOriginProducer).produceOrigin(Mockito.anyDouble(), Mockito.anyDouble());
        Mockito.doReturn(rayDirection).when(rayDirectionProducer).produceDirection(Mockito.anyDouble(), Mockito.anyDouble());
        TracingRayProducer rayProducer = new SimpleRayProducer(rayOriginProducer, rayDirectionProducer);

        Ray result = rayProducer.produceRay(3.7, 7.3);

        Assertions.assertNotNull(result);
        VecMathAssertions.assertEquals(rayOrigin, result.getOrigin(), 0.0);
        VecMathAssertions.assertEquals(rayDirection, result.getDirection(), 0.0);
        Mockito.verify(rayOriginProducer, Mockito.times(1)).produceOrigin(3.7, 7.3);
        Mockito.verify(rayDirectionProducer, Mockito.times(1)).produceDirection(3.7, 7.3);
        Mockito.verifyNoMoreInteractions(rayOriginProducer, rayDirectionProducer);
    }

}
