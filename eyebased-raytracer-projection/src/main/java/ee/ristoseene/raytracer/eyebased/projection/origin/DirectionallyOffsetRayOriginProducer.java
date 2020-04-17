package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class DirectionallyOffsetRayOriginProducer implements RayOriginProducer {

    private final RayDirectionProducer directionResolver;
    private final double directionalOffset;

    public DirectionallyOffsetRayOriginProducer(final RayDirectionProducer directionResolver, final double offset) {
        this.directionResolver = Objects.requireNonNull(directionResolver, "Direction resolver not provided");
        this.directionalOffset = offset;
    }

    @Override
    public Vector3.Accessible produceOrigin(final double x, final double y) {
        return VecMath.multiply(
                directionResolver.produceDirection(x, y),
                directionalOffset,
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

}
