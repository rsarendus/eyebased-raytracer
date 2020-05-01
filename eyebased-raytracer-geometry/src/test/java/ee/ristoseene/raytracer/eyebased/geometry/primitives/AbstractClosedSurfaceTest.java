package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public abstract class AbstractClosedSurfaceTest extends AbstractPrimitiveTest {

    @Override
    protected abstract AbstractClosedSurface createDefaultInstance();

    @ParameterizedTest
    @EnumSource(AbstractClosedSurface.Facing.class)
    public void setFacingShouldSetTheFacing(final AbstractClosedSurface.Facing facing) {
        AbstractClosedSurface closedSurfacePrimitive = createDefaultInstance();

        closedSurfacePrimitive.setFacing(facing);

        Assertions.assertSame(facing, closedSurfacePrimitive.getFacing());
    }

    @ParameterizedTest
    @EnumSource(AbstractClosedSurface.Facing.class)
    public void withFacingShouldSetTheFacingAndReturnItself(final AbstractClosedSurface.Facing facing) {
        AbstractClosedSurface closedSurfacePrimitive = createDefaultInstance();

        Assertions.assertSame(closedSurfacePrimitive, closedSurfacePrimitive.withFacing(facing));
        Assertions.assertSame(facing, closedSurfacePrimitive.getFacing());
    }

}
