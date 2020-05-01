package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Matrix4x4;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.EqualsWithDelta;

import java.util.Objects;

public class Matrix4x4Matcher<M extends Matrix4x4.Accessible> implements ArgumentMatcher<M> {

    private final EqualsWithDelta Xx, Xy, Xz, Xw;
    private final EqualsWithDelta Yx, Yy, Yz, Yw;
    private final EqualsWithDelta Zx, Zy, Zz, Zw;
    private final EqualsWithDelta Tx, Ty, Tz, Tw;

    public Matrix4x4Matcher(final M matrix, final double delta) {
        this(
                matrix.Xx(), matrix.Xy(), matrix.Xz(), matrix.Xw(),
                matrix.Yx(), matrix.Yy(), matrix.Yz(), matrix.Yw(),
                matrix.Zx(), matrix.Zy(), matrix.Zz(), matrix.Zw(),
                matrix.Tx(), matrix.Ty(), matrix.Tz(), matrix.Tw(),
                delta
        );
    }

    public Matrix4x4Matcher(
            final double Xx, final double Xy, final double Xz, final double Xw,
            final double Yx, final double Yy, final double Yz, final double Yw,
            final double Zx, final double Zy, final double Zz, final double Zw,
            final double Tx, final double Ty, final double Tz, final double Tw,
            final double delta
    ) {
        this(
                new EqualsWithDelta(Xx, delta),
                new EqualsWithDelta(Xy, delta),
                new EqualsWithDelta(Xz, delta),
                new EqualsWithDelta(Xw, delta),
                new EqualsWithDelta(Yx, delta),
                new EqualsWithDelta(Yy, delta),
                new EqualsWithDelta(Yz, delta),
                new EqualsWithDelta(Yw, delta),
                new EqualsWithDelta(Zx, delta),
                new EqualsWithDelta(Zy, delta),
                new EqualsWithDelta(Zz, delta),
                new EqualsWithDelta(Zw, delta),
                new EqualsWithDelta(Tx, delta),
                new EqualsWithDelta(Ty, delta),
                new EqualsWithDelta(Tz, delta),
                new EqualsWithDelta(Tw, delta)
        );
    }

    public Matrix4x4Matcher(
            final EqualsWithDelta Xx, final EqualsWithDelta Xy, final EqualsWithDelta Xz, final EqualsWithDelta Xw,
            final EqualsWithDelta Yx, final EqualsWithDelta Yy, final EqualsWithDelta Yz, final EqualsWithDelta Yw,
            final EqualsWithDelta Zx, final EqualsWithDelta Zy, final EqualsWithDelta Zz, final EqualsWithDelta Zw,
            final EqualsWithDelta Tx, final EqualsWithDelta Ty, final EqualsWithDelta Tz, final EqualsWithDelta Tw
    ) {
        this.Xx = Objects.requireNonNull(Xx);
        this.Xy = Objects.requireNonNull(Xy);
        this.Xz = Objects.requireNonNull(Xz);
        this.Xw = Objects.requireNonNull(Xw);
        this.Yx = Objects.requireNonNull(Yx);
        this.Yy = Objects.requireNonNull(Yy);
        this.Yz = Objects.requireNonNull(Yz);
        this.Yw = Objects.requireNonNull(Yw);
        this.Zx = Objects.requireNonNull(Zx);
        this.Zy = Objects.requireNonNull(Zy);
        this.Zz = Objects.requireNonNull(Zz);
        this.Zw = Objects.requireNonNull(Zw);
        this.Tx = Objects.requireNonNull(Tx);
        this.Ty = Objects.requireNonNull(Ty);
        this.Tz = Objects.requireNonNull(Tz);
        this.Tw = Objects.requireNonNull(Tw);
    }

    @Override
    public boolean matches(final M matrix) {
        return matrix != null
                && Xx.matches(matrix.Xx())
                && Xy.matches(matrix.Xy())
                && Xz.matches(matrix.Xz())
                && Xw.matches(matrix.Xw())
                && Yx.matches(matrix.Yx())
                && Yy.matches(matrix.Yy())
                && Yz.matches(matrix.Yz())
                && Yw.matches(matrix.Yw())
                && Zx.matches(matrix.Zx())
                && Zy.matches(matrix.Zy())
                && Zz.matches(matrix.Zz())
                && Zw.matches(matrix.Zw())
                && Tx.matches(matrix.Tx())
                && Ty.matches(matrix.Ty())
                && Tz.matches(matrix.Tz())
                && Tw.matches(matrix.Tw())
        ;
    }

}
