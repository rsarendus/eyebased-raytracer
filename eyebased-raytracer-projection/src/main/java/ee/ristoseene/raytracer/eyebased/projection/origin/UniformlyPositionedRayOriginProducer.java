package ee.ristoseene.raytracer.eyebased.projection.origin;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public final class UniformlyPositionedRayOriginProducer implements RayOriginProducer {

    private final Vector3.Accessible rightAxis;
    private final Vector3.Accessible upAxis;

    public UniformlyPositionedRayOriginProducer(final Orientation orientation, final double horizontalExtent, final double verticalExtent) {
        Objects.requireNonNull(orientation, "Orientation not provided");

        this.rightAxis = VecMath.multiply(orientation.getRightAxis(), horizontalExtent * 0.5, Factories.FACTORY_CONST_VECTOR3_xyz);
        this.upAxis = VecMath.multiply(orientation.getUpAxis(), verticalExtent * 0.5, Factories.FACTORY_CONST_VECTOR3_xyz);
    }

    @Override
    public Vector3.Accessible produceOrigin(final double x, final double y) {
        return VecMathExtended.addProducts(rightAxis, x, upAxis, y, Factories.FACTORY_CONST_VECTOR3_xyz);
    }

}
