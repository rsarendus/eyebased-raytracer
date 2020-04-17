package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import ee.ristoseene.vecmath.mutable.MutableVector3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public abstract class AbstractRayTest {

    protected static final Vector3.Accessible MOCK_ORIGIN = new ImmutableVector3(1.1, 2.2, 3.3);
    protected static final Vector3.Accessible MOCK_DIRECTION = new ImmutableVector3(4.4, 5.5, 6.6);

    protected abstract Ray createInstance(Vector3.Accessible origin, Vector3.Accessible direction);

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.5, 1.0, 1.8, 5.5, 11.1})
    public void getPointOnRayWithFactoryShouldReturnValidPointOnRay(final double distance) {
        Ray ray = createInstance(MOCK_ORIGIN, MOCK_DIRECTION);

        Vector3.Accessible result = ray.getPointOnRay(distance, Factories.FACTORY_CONST_VECTOR3_xyz);

        VecMathAssertions.assertEquals(
                VecMath.multiplyAdd(MOCK_DIRECTION, distance, MOCK_ORIGIN, Factories.FACTORY_CONST_VECTOR3_xyz),
                result, 0.000001
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.5, 1.0, 1.8, 5.5, 11.1})
    public void getPointOnRayWithConsumerShouldReturnValidPointOnRay(final double distance) {
        Vector3.AccessibleAndMutable resultConsumer = new MutableVector3();
        Ray ray = createInstance(MOCK_ORIGIN, MOCK_DIRECTION);

        ray.getPointOnRay(resultConsumer, distance);

        VecMathAssertions.assertEquals(
                VecMath.multiplyAdd(MOCK_DIRECTION, distance, MOCK_ORIGIN, Factories.FACTORY_CONST_VECTOR3_xyz),
                resultConsumer, 0.000001
        );
    }

}
