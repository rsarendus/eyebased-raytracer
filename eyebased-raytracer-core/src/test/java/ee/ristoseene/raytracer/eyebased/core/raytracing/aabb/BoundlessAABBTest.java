package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB.INSTANCE;

public class BoundlessAABBTest {

    @Test
    public void getMinimumXShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMinimumX()));
    }

    @Test
    public void getMaximumXShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMaximumX()));
    }

    @Test
    public void getMinimumYShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMinimumY()));
    }

    @Test
    public void getMaximumYShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMaximumY()));
    }

    @Test
    public void getMinimumZShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMinimumZ()));
    }

    @Test
    public void getMaximumZShouldReturnNaN() {
        Assertions.assertTrue(Double.isNaN(INSTANCE.getMaximumZ()));
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithRay() {
        Ray ray = Mockito.mock(Ray.class);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        boolean result = INSTANCE.intersects(ray, depthTest);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(ray, depthTest);
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithTracingRayContext() {
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        boolean result = INSTANCE.intersects(tracingRayContext, depthTest);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(tracingRayContext, depthTest);
    }

    @Test
    public void isInBoundsShouldReturnFalseAndNotInteractWithPosition() {
        Vector3.Accessible position = Mockito.mock(Vector3.Accessible.class);

        boolean result = INSTANCE.isInBounds(position);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(position);
    }

    @Test
    public void getMinimumShouldReturnVectorOfNaNs() {
        VecMathAssertions.assertMatches(Double::isNaN, INSTANCE.getMinimum(), "not NaN");
    }

    @Test
    public void getMaximumShouldReturnVectorOfNaNs() {
        VecMathAssertions.assertMatches(Double::isNaN, INSTANCE.getMaximum(), "not NaN");
    }

}
