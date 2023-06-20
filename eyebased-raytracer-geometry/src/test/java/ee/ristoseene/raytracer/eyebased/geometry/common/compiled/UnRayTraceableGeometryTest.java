package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.BoundlessAABB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnRayTraceableGeometryTest {

    @Test
    public void interactWithShouldNotDoAnything() {
        TracingRayContext tracingRayContext = Mockito.mock(TracingRayContext.class);
        RayTracedState rayTracedState = Mockito.mock(RayTracedState.class);

        UnRayTraceableGeometry.INSTANCE.interactWith(tracingRayContext, rayTracedState);

        Mockito.verifyNoInteractions(tracingRayContext, rayTracedState);
    }

    @Test
    public void getAABBShouldReturnBoundlessAABB() {
        AABB aabb = UnRayTraceableGeometry.INSTANCE.getAABB();

        Assertions.assertSame(BoundlessAABB.INSTANCE, aabb);
    }

}
