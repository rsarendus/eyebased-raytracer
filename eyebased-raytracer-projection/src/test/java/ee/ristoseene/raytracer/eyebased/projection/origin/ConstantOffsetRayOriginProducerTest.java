package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstantOffsetRayOriginProducerTest {

    @Test
    public void originProducerShouldNotAllowMissingOffset() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new ConstantOffsetRayOriginProducer(null)
        );

        Assertions.assertEquals("Offset not provided", exception.getMessage());
    }

    @Test
    public void produceOriginShouldReturnConstantlyOffsetOrigin() {
        Vector3.Accessible offset = new ImmutableVector3(1.3, 2.2, 3.1);
        RayOriginProducer originProducer = new ConstantOffsetRayOriginProducer(offset);

        Vector3.Accessible result = originProducer.produceOrigin(7.3, 3.7);

        VecMathAssertions.assertEquals(offset, result, 0.0);
    }

}
