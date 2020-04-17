package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;

import java.util.Objects;

public final class DefaultTransformingRayProducer extends TransformingRayProducer {

    private final RayOriginProducer originProducer;
    private final RayDirectionProducer directionProducer;

    public DefaultTransformingRayProducer(final CompiledTransform transformer, final RayOriginProducer originProducer, final RayDirectionProducer directionProducer) {
        super(transformer);

        this.originProducer = Objects.requireNonNull(originProducer, "Ray origin producer not provided");
        this.directionProducer = Objects.requireNonNull(directionProducer, "Ray direction producer not provided");
    }

    @Override
    public Ray produceRay(final double x, final double y) {
        return createTransformedRay(
                originProducer.produceOrigin(x, y),
                directionProducer.produceDirection(x, y)
        );
    }

}
