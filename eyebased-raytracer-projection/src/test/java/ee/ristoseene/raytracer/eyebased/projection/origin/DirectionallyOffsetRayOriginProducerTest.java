package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class DirectionallyOffsetRayOriginProducerTest {

    @Test
    public void originProducerShouldNotAllowMissingOffset() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new DirectionallyOffsetRayOriginProducer(null, 0.0)
        );

        Assertions.assertEquals("Direction resolver not provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.1, 0.0, 0.1, 1.0, 8.8})
    public void produceOriginShouldReturnDirectionallyOffsetPosition(final double offset) {
        Vector3.Accessible direction = Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(1.0, 2.0, 3.0);
        RayDirectionProducer directionResolver = Mockito.mock(RayDirectionProducer.class);
        Mockito.doReturn(direction).when(directionResolver).produceDirection(Mockito.anyDouble(), Mockito.anyDouble());
        RayOriginProducer originProducer = new DirectionallyOffsetRayOriginProducer(directionResolver, offset);

        Vector3.Accessible result = originProducer.produceOrigin(3.2, 2.3);

        VecMathAssertions.assertEquals(
                VecMath.multiply(direction, offset, Factories.FACTORY_CONST_VECTOR3_xyz),
                result, 0.000001
        );
        Mockito.verify(directionResolver, Mockito.times(1)).produceDirection(3.2, 2.3);
    }

}
