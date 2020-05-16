package ee.ristoseene.raytracer.eyebased.shading.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface AdjustableHemisphericalSampler extends CompiledObject {

    /**
     * An implementation of this method is expected to return a sample on the surface of
     * the cap of a unit hemisphere which is oriented towards the vector specified by
     * {@code direction} and limited by {@code extent}.
     * Value {@code 0.0} for {@code extent} means no deviation from the original direction,
     * value {@code 1.0} means distribution on a full hemisphere.
     *
     * {@code direction} is expected to be normalized!
     * {@code extent} is expected to be in the range of [0.0, 1.0]!
     *
     * @param direction a vector specifying the orientation of the spherical cap to sample
     * @param extent the extent of the distribution
     * @return sample on the cap of a unit sphere oriented towards {@code direction}
     */
    Vector3.Accessible sample(Vector3.Accessible direction, double extent);

}
