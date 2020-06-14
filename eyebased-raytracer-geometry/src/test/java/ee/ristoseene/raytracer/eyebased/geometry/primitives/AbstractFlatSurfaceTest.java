package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Optional;

public abstract class AbstractFlatSurfaceTest extends AbstractPrimitiveTest {

    protected abstract AbstractFlatSurface createEmptyInstance();

    @Override
    protected AbstractFlatSurface createDefaultInstance() {
        return createEmptyInstance().withNormal(Axis.POSITIVE_X);
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void setNormalShouldSetTheNormal(final Axis normal) {
        AbstractFlatSurface flatSurfacePrimitive = createEmptyInstance();

        flatSurfacePrimitive.setNormal(normal);

        Assertions.assertSame(normal, flatSurfacePrimitive.getNormal());
    }

    @ParameterizedTest
    @EnumSource(Axis.class)
    public void withNormalShouldSetTheNormalAndReturnItself(final Axis normal) {
        AbstractFlatSurface flatSurfacePrimitive = createEmptyInstance();

        Assertions.assertSame(flatSurfacePrimitive, flatSurfacePrimitive.withNormal(normal));
        Assertions.assertSame(normal, flatSurfacePrimitive.getNormal());
    }

    @ParameterizedTest
    @EnumSource(AbstractFlatSurface.Facing.class)
    public void setFacingShouldSetTheFacing(final AbstractFlatSurface.Facing facing) {
        AbstractFlatSurface flatSurfacePrimitive = createEmptyInstance();

        flatSurfacePrimitive.setFacing(facing);

        Assertions.assertSame(facing, flatSurfacePrimitive.getFacing());
    }

    @ParameterizedTest
    @EnumSource(AbstractFlatSurface.Facing.class)
    public void withFacingShouldSetTheFacingAndReturnItself(final AbstractFlatSurface.Facing facing) {
        AbstractFlatSurface flatSurfacePrimitive = createEmptyInstance();

        Assertions.assertSame(flatSurfacePrimitive, flatSurfacePrimitive.withFacing(facing));
        Assertions.assertSame(facing, flatSurfacePrimitive.getFacing());
    }

    @Test
    public void compileForUnsetSurfaceNormalShouldFail() {
        AbstractFlatSurface flatSurfacePrimitive = createEmptyInstance();

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> flatSurfacePrimitive.compile(Optional.empty())
        );

        Assertions.assertEquals("Surface normal not provided", exception.getMessage());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T cloneShouldCreateValidCopyOfItself(T original) {
        AbstractFlatSurface originalFlatSurface = (AbstractFlatSurface) original;
        originalFlatSurface.setFacing(AbstractFlatSurface.Facing.DOUBLE_SIDED);
        originalFlatSurface.setNormal(Axis.NEGATIVE_Z);

        AbstractFlatSurface clonedFlatSurface = super.cloneShouldCreateValidCopyOfItself(originalFlatSurface);

        Assertions.assertNotSame(originalFlatSurface, clonedFlatSurface);
        Assertions.assertSame(AbstractFlatSurface.Facing.DOUBLE_SIDED, clonedFlatSurface.getFacing());
        Assertions.assertSame(Axis.NEGATIVE_Z, clonedFlatSurface.getNormal());

        return (T) clonedFlatSurface;
    }

}
