package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AbstractRayTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DefaultRayTest extends AbstractRayTest {

    @Test
    public void rayFromAnotherRayShouldHaveTheSameOriginAndDirectionValues() {
        Ray rayMock = Mockito.mock(Ray.class);
        Mockito.doReturn(MOCK_ORIGIN).when(rayMock).getOrigin();
        Mockito.doReturn(MOCK_DIRECTION).when(rayMock).getDirection();

        DefaultRay defaultRay = new DefaultRay(rayMock);
        VecMathAssertions.assertEquals(MOCK_ORIGIN, defaultRay.getOrigin(), 0.0);
        VecMathAssertions.assertEquals(MOCK_DIRECTION, defaultRay.getDirection(), 0.0);
    }

    @Test
    public void rayFromOriginAndDirectionShouldHaveTheSameOriginAndDirectionValues() {
        DefaultRay defaultRay = new DefaultRay(MOCK_ORIGIN, MOCK_DIRECTION);
        VecMathAssertions.assertEquals(MOCK_ORIGIN, defaultRay.getOrigin(), 0.0);
        VecMathAssertions.assertEquals(MOCK_DIRECTION, defaultRay.getDirection(), 0.0);
    }

    @Override
    protected Ray createInstance(Vector3.Accessible origin, Vector3.Accessible direction) {
        return new DefaultRay(origin, direction);
    }

}
