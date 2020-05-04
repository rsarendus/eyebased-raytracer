package ee.ristoseene.raytracer.eyebased.shading.providers.constant;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.Vector3;

public interface VectorProviders {

    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_NaN_NaN_NaN = new ConstantValueProvider<>(Vectors.VECTOR3_NaN_NaN_NaN);
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_ZERO_ZERO_ZERO = new ConstantValueProvider<>(Vectors.VECTOR3_ZERO_ZERO_ZERO);
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_HALF_HALF_HALF = new ConstantValueProvider<>(Vectors.VECTOR3_HALF_HALF_HALF);
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_ONE_ONE_ONE = new ConstantValueProvider<>(Vectors.VECTOR3_ONE_ONE_ONE);

    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_minusONE_minusONE_minusONE = new ConstantValueProvider<>(Vectors.VECTOR3_minusONE_minusONE_minusONE);
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_minusHALF_minusHALF_minusHALF = new ConstantValueProvider<>(Vectors.VECTOR3_minusHALF_minusHALF_minusHALF);
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_plusHALF_plusHALF_plusHALF = PROVIDER_VECTOR3_HALF_HALF_HALF;
    ValueProvider<Vector3.Accessible> PROVIDER_VECTOR3_plusONE_plusONE_plusONE = PROVIDER_VECTOR3_ONE_ONE_ONE;

}
