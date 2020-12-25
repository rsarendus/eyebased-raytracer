package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk.AbstractShadeableRayTraceableDisk;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk.DoubleSidedShadeableRayTraceableDisk;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk.FrontFacingShadeableRayTraceableDisk;

import java.util.Optional;

public class Disk extends AbstractFlatSurface {

    private double diameter = 1.0;

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    public Disk withDiameter(final double diameter) {
        setDiameter(diameter);
        return this;
    }

    @Override
    public Disk withParentTransform(final CompilableTransform parentTransform) {
        return (Disk) super.withParentTransform(parentTransform);
    }

    @Override
    public Disk withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (Disk) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public Disk withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        return (Disk) super.withGeometryContextFactory(geometryContextFactory);
    }

    @Override
    public Disk withNormal(final Axis normal) {
        return (Disk) super.withNormal(normal);
    }

    @Override
    public Disk withFacing(final Facing facing) {
        return (Disk) super.withFacing(facing);
    }

    @Override
    public Disk clone() {
        return (Disk) super.clone();
    }

    @Override
    protected AbstractShadeableRayTraceableDisk createCompiledGeometry(final Optional<CompilationCache> compilationCache) {
        final AbstractShadeableRayTraceableDisk.Configuration configuration = createConfiguration(compilationCache);

        switch (getFacingOrDefault(Facing.FRONT_FACING)) {
            case DOUBLE_SIDED:
                return new DoubleSidedShadeableRayTraceableDisk(getNormal(), configuration, getDiameter());
            default:
                return new FrontFacingShadeableRayTraceableDisk(getNormal(), configuration, getDiameter());
        }
    }

}
