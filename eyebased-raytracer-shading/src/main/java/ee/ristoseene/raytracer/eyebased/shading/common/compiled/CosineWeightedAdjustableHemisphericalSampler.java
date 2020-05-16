package ee.ristoseene.raytracer.eyebased.shading.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.utilities.SamplingOnUnitSphere;
import ee.ristoseene.raytracer.eyebased.shading.common.AdjustableHemisphericalSampler;
import ee.ristoseene.vecmath.Vector3;

import java.util.concurrent.ThreadLocalRandom;

public class CosineWeightedAdjustableHemisphericalSampler implements AdjustableHemisphericalSampler {

    @Override
    public Vector3.Accessible sample(final Vector3.Accessible direction, final double extent) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        // TODO: find the correct exponent, pow(extent, 3) was chosen just by visual assessment
        final double exponent = extent * extent * extent;

        return SamplingOnUnitSphere.mapToPointOnUnitSphere(
                direction,
                Math.acos(Math.pow(random.nextDouble(), exponent)),
                random.nextDouble() * SamplingOnUnitSphere.PIx2,
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

}
