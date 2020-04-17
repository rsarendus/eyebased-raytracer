package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class ConstantOffsetRayOriginProducer implements RayOriginProducer {

    private final Vector3.Accessible originOffset;

    public ConstantOffsetRayOriginProducer(final Vector3.Accessible offset) {
        this.originOffset = Objects.requireNonNull(offset, "Offset not provided");
    }

    @Override
    public Vector3.Accessible produceOrigin(final double x, final double y) {
        return originOffset;
    }

}
