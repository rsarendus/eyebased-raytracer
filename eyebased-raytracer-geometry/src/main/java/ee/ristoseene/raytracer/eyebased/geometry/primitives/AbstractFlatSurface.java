package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractFlatSurface extends AbstractPrimitive {

    public enum Facing {
        DOUBLE_SIDED, FRONT_FACING;
    }

    private Axis normal;
    private Facing facing;

    public Axis getNormal() {
        return normal;
    }

    public void setNormal(final Axis normal) {
        this.normal = normal;
    }

    public AbstractFlatSurface withNormal(final Axis normal) {
        setNormal(normal);
        return this;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(final Facing facing) {
        this.facing = facing;
    }

    public AbstractFlatSurface withFacing(final Facing facing) {
        setFacing(facing);
        return this;
    }

    @Override
    public AbstractFlatSurface withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractFlatSurface) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractFlatSurface withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (AbstractFlatSurface) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public AbstractFlatSurface withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        return (AbstractFlatSurface) super.withGeometryContextFactory(geometryContextFactory);
    }

    @Override
    public AbstractFlatSurface clone() {
        return (AbstractFlatSurface) super.clone();
    }

    @Override
    public CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        Objects.requireNonNull(getNormal(), "Surface normal not provided");
        return super.compile(compilationCache);
    }

    protected Facing getFacingOrDefault(final Facing defaultFacing) {
        return (getFacing() != null) ? getFacing() : defaultFacing;
    }

}
