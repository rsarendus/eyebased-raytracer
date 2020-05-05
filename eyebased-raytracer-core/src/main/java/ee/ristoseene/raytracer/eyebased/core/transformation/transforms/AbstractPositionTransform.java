package ee.ristoseene.raytracer.eyebased.core.transformation.transforms;

import ee.ristoseene.raytracer.eyebased.core.transformation.ChainableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.vecmath.Vector3;

public abstract class AbstractPositionTransform extends ChainableTransform {

    protected Vector3.Accessible position;

    public Vector3.Accessible getPosition() {
        return position;
    }

    public void setPosition(final Vector3.Accessible position) {
        this.position = position;
    }

    public AbstractPositionTransform withPosition(final Vector3.Accessible position) {
        setPosition(position);
        return this;
    }

    @Override
    public AbstractPositionTransform withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractPositionTransform) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractPositionTransform clone() {
        return (AbstractPositionTransform) super.clone();
    }

}
