package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class TransformingRayProducerTest {

    protected abstract TracingRayProducer createRayProducerWithTransformer(CompiledTransform transformer);

    @Test
    public void rayProducerShouldNotAllowMissingRayTransformer() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> createRayProducerWithTransformer(null)
        );

        Assertions.assertEquals("Ray transformer not provided", exception.getMessage());
    }

}
