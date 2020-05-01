package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.vecmath.Vector3;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class RayMatcher implements ArgumentMatcher<Ray> {

    private final Vector3Matcher<Vector3.Accessible> origin, direction;

    public RayMatcher(final Ray ray, final double delta) {
        this(ray.getOrigin(), ray.getDirection(), delta);
    }

    public RayMatcher(final Vector3.Accessible origin, final Vector3.Accessible direction, final double delta) {
        this(new Vector3Matcher<>(origin, delta), new Vector3Matcher<>(direction, delta));
    }

    public RayMatcher(final Vector3Matcher<Vector3.Accessible> origin, final Vector3Matcher<Vector3.Accessible> direction) {
        this.origin = Objects.requireNonNull(origin);
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public boolean matches(final Ray ray) {
        return ray != null
                && origin.matches(ray.getOrigin())
                && direction.matches(ray.getDirection())
        ;
    }

}
