package ee.ristoseene.raytracer.eyebased.core.raytracing.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.helpers.VecMathAssertions;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleRayIntersectionContextTest {

    @Test
    public void rayIntersectionContextFromRayAndDistanceShouldHaveTheSameRayAndDistanceAndCorrectIntersectionPoint() {
        Ray originalRay = new SimpleRay(
                Factories.FACTORY_CONST_VECTOR3_xyz.create(1.3, -2.2, 3.1),
                Factories.FACTORY_CONST_VECTOR3_NORMALIZED_xyz.create(-3.1, 2.2, -1.3)
        );

        RayIntersectionContext rayIntersectionContext = new SimpleRayIntersectionContext(originalRay, 7.35);

        Assertions.assertNotNull(rayIntersectionContext.getIntersectingRay());
        Assertions.assertNotNull(rayIntersectionContext.getRayIntersectionPoint());
        VecMathAssertions.assertEquals(originalRay.getOrigin(), rayIntersectionContext.getIntersectingRay().getOrigin(), 0.0);
        VecMathAssertions.assertEquals(originalRay.getDirection(), rayIntersectionContext.getIntersectingRay().getDirection(), 0.0);
        Assertions.assertEquals(7.35, rayIntersectionContext.getRayIntersectionDistance());
        VecMathAssertions.assertEquals(
                originalRay.getPointOnRay(7.35, Factories.FACTORY_CONST_VECTOR3_xyz),
                rayIntersectionContext.getRayIntersectionPoint(),
                0.000001
        );
    }

}
