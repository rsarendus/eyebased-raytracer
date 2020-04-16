package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.vecmath.Vector3;

public abstract class AbstractScalingPositionTransform extends AbstractPositionTransform {

    protected Vector3.Accessible scale;

    public Vector3.Accessible getScale() {
        return scale;
    }

    public void setScale(final Vector3.Accessible scale) {
        this.scale = scale;
    }

    public AbstractScalingPositionTransform withScale(final Vector3.Accessible scale) {
        setScale(scale);
        return this;
    }

    @Override
    public AbstractScalingPositionTransform withPosition(final Vector3.Accessible position) {
        return (AbstractScalingPositionTransform) super.withPosition(position);
    }

    @Override
    public AbstractScalingPositionTransform withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractScalingPositionTransform) super.withParentTransform(parentTransform);
    }

}
