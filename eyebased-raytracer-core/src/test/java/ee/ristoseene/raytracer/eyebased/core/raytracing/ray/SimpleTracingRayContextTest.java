package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.vecmath.VecMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTracingRayContextTest {

    @Test
    public void tracingRayContextFromRayShouldHaveTheSameRayAndInverseOfItsDirection() {
        Ray originalRay = new SimpleRay(
                Factories.FACTORY_CONST_VECTOR3_xyz.create(1.3, -2.2, 3.1),
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(-3.1, 2.2, -1.3)
        );

        TracingRayContext tracingRayContext = new SimpleTracingRayContext(originalRay);

        Assertions.assertNotNull(tracingRayContext.getTracingRay());
        Assertions.assertNotNull(tracingRayContext.getInverseRayDirection());
        VecMathAssertions.assertEquals(originalRay.getOrigin(), tracingRayContext.getTracingRay().getOrigin(), 0.0);
        VecMathAssertions.assertEquals(originalRay.getDirection(), tracingRayContext.getTracingRay().getDirection(), 0.0);
        VecMathAssertions.assertEquals(
                VecMath.divide(1.0, originalRay.getDirection(), Factories.FACTORY_CONST_VECTOR3_xyz),
                tracingRayContext.getInverseRayDirection(), 0.000001
        );
    }

}
