package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import org.mockito.Mockito;

public interface VerboseRaytracing {

    static <T extends Ray> String toString(final T ray) {
        return String.format(
                "<%s, %s>",
                VerboseVecMath.toString(ray.getOrigin()),
                VerboseVecMath.toString(ray.getDirection())
        );
    }

    static <T extends Ray> T verbose(final T ray) {
        T verboseRay = Mockito.spy(ray);
        Mockito.doAnswer(invocationOnMock -> toString(ray)).when(verboseRay).toString();
        return verboseRay;
    }

}
