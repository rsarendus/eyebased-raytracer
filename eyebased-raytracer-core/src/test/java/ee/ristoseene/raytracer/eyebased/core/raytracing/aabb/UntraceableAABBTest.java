package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.DepthTest;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UntraceableAABBTest extends AbstractBoundedAABBTest {

    @Override
    protected UntraceableAABB createInstance(final Vector3.Accessible minimum, final Vector3.Accessible maximum) {
        return new UntraceableAABB(minimum, maximum);
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithRay() {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        Ray ray = Mockito.mock(Ray.class);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        boolean result = aabb.intersects(ray, depthTest);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(ray, depthTest);
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithTracingRayContext() {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        DepthTest depthTest = Mockito.mock(DepthTest.class);

        boolean result = aabb.intersects(tracingRayContext, depthTest);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(tracingRayContext, depthTest);
    }

}
