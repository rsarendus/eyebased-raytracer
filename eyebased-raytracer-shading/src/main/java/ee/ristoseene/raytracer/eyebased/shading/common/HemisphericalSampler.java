package ee.ristoseene.raytracer.eyebased.shading.common;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector3;

@FunctionalInterface
public interface HemisphericalSampler extends CompiledObject {

    /**
     * An implementation of this method is expected to return a sample on the surface of
     * a unit hemisphere which is oriented towards the vector specified by {@code direction}.
     *
     * {@code direction} is expected to be normalized!
     *
     * @param direction a vector specifying the orientation of the hemisphere to sample
     * @return sample on a unit hemisphere oriented towards {@code direction}
     */
    Vector3.Accessible sample(Vector3.Accessible direction);

}
