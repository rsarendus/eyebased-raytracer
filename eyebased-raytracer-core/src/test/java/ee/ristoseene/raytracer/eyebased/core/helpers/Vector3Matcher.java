package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Vector3;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.EqualsWithDelta;

public class Vector3Matcher implements ArgumentMatcher<Vector3.Accessible> {

    private final EqualsWithDelta x, y, z;

    public Vector3Matcher(final Vector3.Accessible vector, final double delta) {
        this(vector.x(), vector.y(), vector.z(), delta);
    }

    public Vector3Matcher(final double x, final double y, final double z, final double delta) {
        this(new EqualsWithDelta(x, delta), new EqualsWithDelta(y, delta), new EqualsWithDelta(z, delta));
    }

    public Vector3Matcher(final EqualsWithDelta x, final EqualsWithDelta y, final EqualsWithDelta z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean matches(final Vector3.Accessible vector) {
        return vector != null
                && x.matches(vector.x())
                && y.matches(vector.y())
                && z.matches(vector.z())
        ;
    }

}
