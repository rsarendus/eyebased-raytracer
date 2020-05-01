package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.geometry.common.AbstractGeometryTest;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class AbstractPrimitiveTest extends AbstractGeometryTest {

    @Override
    protected abstract AbstractPrimitive createDefaultInstance();

    @Test
    public void setGeometryContextFactoryShouldSetTheGeometryContextFactory() {
        RaySurfaceIntersectionGeometryContextFactory factory = Mockito.mock(RaySurfaceIntersectionGeometryContextFactory.class);
        AbstractPrimitive primitive = createDefaultInstance();

        primitive.setGeometryContextFactory(factory);

        Assertions.assertSame(factory, primitive.getGeometryContextFactory());
    }

    @Test
    public void withGeometryContextFactoryShouldSetTheGeometryContextFactoryAndReturnItself() {
        RaySurfaceIntersectionGeometryContextFactory factory = Mockito.mock(RaySurfaceIntersectionGeometryContextFactory.class);
        AbstractPrimitive primitive = createDefaultInstance();

        Assertions.assertSame(primitive, primitive.withGeometryContextFactory(factory));
        Assertions.assertSame(factory, primitive.getGeometryContextFactory());
    }

}
