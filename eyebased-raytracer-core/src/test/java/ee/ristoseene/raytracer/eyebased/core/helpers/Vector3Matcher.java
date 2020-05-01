package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Vector3;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.EqualsWithDelta;

import java.util.Objects;

public class Vector3Matcher<V extends Vector3.Accessible> implements ArgumentMatcher<V> {

    private final EqualsWithDelta x, y, z;

    public Vector3Matcher(final V vector, final double delta) {
        this(vector.x(), vector.y(), vector.z(), delta);
    }

    public Vector3Matcher(final double x, final double y, final double z, final double delta) {
        this(new EqualsWithDelta(x, delta), new EqualsWithDelta(y, delta), new EqualsWithDelta(z, delta));
    }

    public Vector3Matcher(final EqualsWithDelta x, final EqualsWithDelta y, final EqualsWithDelta z) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
        this.z = Objects.requireNonNull(z);
    }

    @Override
    public boolean matches(final V vector) {
        return vector != null
                && x.matches(vector.x())
                && y.matches(vector.y())
                && z.matches(vector.z())
        ;
    }

}
