package ee.ristoseene.raytracer.eyebased.shading.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.utilities.SamplingOnUnitSphere;
import ee.ristoseene.raytracer.eyebased.shading.common.HemisphericalSampler;
import ee.ristoseene.vecmath.Vector3;

import java.util.concurrent.ThreadLocalRandom;

public class CosineWeightedHemisphericalSampler implements HemisphericalSampler {

    public static final CosineWeightedHemisphericalSampler INSTANCE = new CosineWeightedHemisphericalSampler();

    @Override
    public Vector3.Accessible sample(final Vector3.Accessible direction) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        
        return SamplingOnUnitSphere.mapToPointOnUnitSphere(
                direction,
                Math.acos(random.nextDouble()),
                random.nextDouble() * SamplingOnUnitSphere.PIx2,
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

    protected CosineWeightedHemisphericalSampler() {}

}
