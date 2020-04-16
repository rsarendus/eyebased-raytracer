package ee.ristoseene.raytracer.eyebased.core.transformation;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;

public final class CompiledTransform extends ImmutableMatrix4x4 implements CompiledObject {

    public static final CompiledTransform IDENTITY_TRANSFORM = new CompiledTransform();

    private final CompiledTransform inverseTransform;

    public CompiledTransform(final Matrix4x4.Accessible transformationMatrix) {
        super(transformationMatrix);
        inverseTransform = createInverseTransform();
    }

    public CompiledTransform getInverseTransform() {
        return inverseTransform;
    }

    private CompiledTransform createInverseTransform() {
        return VecMath.inverse(this, (Xx, Xy, Xz, Xw, Yx, Yy, Yz, Yw, Zx, Zy, Zz, Zw, Tx, Ty, Tz, Tw) ->
                new CompiledTransform(Xx, Xy, Xz, Xw, Yx, Yy, Yz, Yw, Zx, Zy, Zz, Zw, Tx, Ty, Tz, Tw, this));
    }

    private CompiledTransform(
            final double Xx, final double Xy, final double Xz, final double Xw,
            final double Yx, final double Yy, final double Yz, final double Yw,
            final double Zx, final double Zy, final double Zz, final double Zw,
            final double Tx, final double Ty, final double Tz, final double Tw,
            final CompiledTransform inverse
    ) {
        super(
                Xx, Xy, Xz, Xw,
                Yx, Yy, Yz, Yw,
                Zx, Zy, Zz, Zw,
                Tx, Ty, Tz, Tw
        );
        inverseTransform = inverse;
    }

    private CompiledTransform() {
        super(1.0, 1.0, 1.0, 1.0);
        inverseTransform = this;
    }

}
