package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

/**
 * Efficient slab testing.
 * Derived from the listing 1 by Majercik et al. http://jcgt.org/published/0007/03/04/
 * <pre>
 * {@code
 * bool slabs(vec3 p0, vec3 p1, vec3 rayOrigin, vec3 invRaydir) {
 *
 *   vec3 t0 = (p0 - rayOrigin) * invRaydir;
 *   vec3 t1 = (p1 - rayOrigin) * invRaydir;
 *   vec3 tmin = min(t0,t1), tmax = max(t0,t1);
 *
 *   return max_component(tmin) <= min_component(tmax);
 * }
 * }
 * </pre>
 */
public final class EfficientSlabTesting {

    /**
     * Tests whether the specified ray intersects the AABB with corners <code>p0</code> and <code>p1</code>.
     * This is the optimized version of the method - it uses pre-calculated inverse ray direction.
     *
     * @param p0 first corner of the AABB to test
     * @param p1 second corner of the AABB to test
     * @param rayOrigin origin of the ray to test
     * @param inverseRayDirection inverse direction of the ray to test
     *
     * @return <code>true</code> if intersection occurs, <code>false</code> otherwise
     */
    public static boolean testIntersectionOptimized(
            final Vector3.Accessible p0, final Vector3.Accessible p1,
            final Vector3.Accessible rayOrigin, final Vector3.Accessible inverseRayDirection
    ) {
        final double t0X = (p0.x() - rayOrigin.x()) * inverseRayDirection.x();
        final double t0Y = (p0.y() - rayOrigin.y()) * inverseRayDirection.y();
        final double t0Z = (p0.z() - rayOrigin.z()) * inverseRayDirection.z();

        final double t1X = (p1.x() - rayOrigin.x()) * inverseRayDirection.x();
        final double t1Y = (p1.y() - rayOrigin.y()) * inverseRayDirection.y();
        final double t1Z = (p1.z() - rayOrigin.z()) * inverseRayDirection.z();

        final double tMin = VecMath.max(VecMath.min(t0X, t1X), VecMath.min(t0Y, t1Y), VecMath.min(t0Z, t1Z));
        final double tMax = VecMath.min(VecMath.max(t0X, t1X), VecMath.max(t0Y, t1Y), VecMath.max(t0Z, t1Z));

        return (tMin <= tMax & tMax >= 0.0);
    }

    /**
     * Tests whether the specified ray intersects the AABB with corners <code>p0</code> and <code>p1</code>.
     * This version of the method calculates the inverse of the ray direction on site, requiring 3 divisions.
     *
     * @param p0 first corner of the AABB to test
     * @param p1 second corner of the AABB to test
     * @param rayOrigin origin of the ray to test
     * @param rayDirection direction of the ray to test
     *
     * @return <code>true</code> if intersection occurs, <code>false</code> otherwise
     */
    public static boolean testIntersectionWithInversion(
            final Vector3.Accessible p0, final Vector3.Accessible p1,
            final Vector3.Accessible rayOrigin, final Vector3.Accessible rayDirection
    ) {
        final double inverseRayDirectionX = 1.0 / rayDirection.x();
        final double inverseRayDirectionY = 1.0 / rayDirection.y();
        final double inverseRayDirectionZ = 1.0 / rayDirection.z();

        final double t0X = (p0.x() - rayOrigin.x()) * inverseRayDirectionX;
        final double t0Y = (p0.y() - rayOrigin.y()) * inverseRayDirectionY;
        final double t0Z = (p0.z() - rayOrigin.z()) * inverseRayDirectionZ;

        final double t1X = (p1.x() - rayOrigin.x()) * inverseRayDirectionX;
        final double t1Y = (p1.y() - rayOrigin.y()) * inverseRayDirectionY;
        final double t1Z = (p1.z() - rayOrigin.z()) * inverseRayDirectionZ;

        final double tMin = VecMath.max(VecMath.min(t0X, t1X), VecMath.min(t0Y, t1Y), VecMath.min(t0Z, t1Z));
        final double tMax = VecMath.min(VecMath.max(t0X, t1X), VecMath.max(t0Y, t1Y), VecMath.max(t0Z, t1Z));

        return (tMin <= tMax & tMax >= 0.0);
    }

    private EfficientSlabTesting() {}

}
