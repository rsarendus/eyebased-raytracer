package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AbstractRayTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SimpleRayTest extends AbstractRayTest {

    @Test
    public void rayFromAnotherRayShouldHaveTheSameOriginAndDirectionValues() {
        Ray rayMock = Mockito.mock(Ray.class);
        Mockito.doReturn(MOCK_ORIGIN).when(rayMock).getOrigin();
        Mockito.doReturn(MOCK_DIRECTION).when(rayMock).getDirection();

        SimpleRay simpleRay = new SimpleRay(rayMock);
        VecMathAssertions.assertEquals(MOCK_ORIGIN, simpleRay.getOrigin(), 0.0);
        VecMathAssertions.assertEquals(MOCK_DIRECTION, simpleRay.getDirection(), 0.0);
    }

    @Test
    public void rayFromOriginAndDirectionShouldHaveTheSameOriginAndDirectionValues() {
        SimpleRay simpleRay = new SimpleRay(MOCK_ORIGIN, MOCK_DIRECTION);
        VecMathAssertions.assertEquals(MOCK_ORIGIN, simpleRay.getOrigin(), 0.0);
        VecMathAssertions.assertEquals(MOCK_DIRECTION, simpleRay.getDirection(), 0.0);
    }

    @Override
    protected Ray createInstance(final Vector3.Accessible origin, final Vector3.Accessible direction) {
        return new SimpleRay(origin, direction);
    }

}
