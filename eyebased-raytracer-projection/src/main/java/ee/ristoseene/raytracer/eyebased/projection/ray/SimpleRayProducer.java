package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.projection.CompiledRayProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;

import java.util.Objects;

public final class SimpleRayProducer implements CompiledRayProducer {

    private final RayOriginProducer originProducer;
    private final RayDirectionProducer directionProducer;

    public SimpleRayProducer(final RayOriginProducer originProducer, final RayDirectionProducer directionProducer) {
        this.originProducer = Objects.requireNonNull(originProducer, "Ray origin producer not provided");
        this.directionProducer = Objects.requireNonNull(directionProducer, "Ray direction producer not provided");
    }

    @Override
    public Ray produceRay(final double x, final double y) {
        return new SimpleRay(
                originProducer.produceOrigin(x, y),
                directionProducer.produceDirection(x, y)
        );
    }

}
