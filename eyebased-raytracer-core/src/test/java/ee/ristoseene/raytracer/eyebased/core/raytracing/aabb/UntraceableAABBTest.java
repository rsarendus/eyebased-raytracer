package ee.ristoseene.raytracer.eyebased.core.raytracing.aabb;

import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.Vector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UntraceableAABBTest extends MinimumMaximumAABBTest {

    @Override
    protected UntraceableAABB createInstance(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        return new UntraceableAABB(p0, p1);
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithRay() {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        Ray ray = Mockito.mock(Ray.class);

        boolean result = aabb.intersects(ray);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(ray);
    }

    @Test
    public void intersectsShouldReturnFalseAndNotInteractWithTracingRayContext() {
        AABB aabb = createInstance(Vectors.VECTOR3_minusONE_minusONE_minusONE, Vectors.VECTOR3_plusONE_plusONE_plusONE);
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);

        boolean result = aabb.intersects(tracingRayContext);

        Assertions.assertEquals(false, result);
        Mockito.verifyNoInteractions(tracingRayContext);
    }

}
