package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;

public abstract class AbstractClosedSurface extends AbstractPrimitive {

    public enum Facing {
        DOUBLE_SIDED, INWARD_FACING, OUTWARD_FACING;
    }

    private Facing facing;

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(final Facing facing) {
        this.facing = facing;
    }

    public AbstractClosedSurface withFacing(final Facing facing) {
        setFacing(facing);
        return this;
    }

    @Override
    public AbstractClosedSurface withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractClosedSurface) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractClosedSurface withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (AbstractClosedSurface) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public AbstractClosedSurface withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        return (AbstractClosedSurface) super.withGeometryContextFactory(geometryContextFactory);
    }

    @Override
    public AbstractClosedSurface clone() {
        return (AbstractClosedSurface) super.clone();
    }

    protected Facing getFacingOrDefault(final Facing defaultFacing) {
        return (facing != null) ? facing : defaultFacing;
    }

}
