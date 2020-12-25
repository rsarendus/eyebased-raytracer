package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.AbstractShadeableRayTraceableSphere;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.DoubleSidedShadeableRayTraceableSphere;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.InwardFacingShadeableRayTraceableSphere;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.OutwardFacingShadeableRayTraceableSphere;

import java.util.Optional;

public class Sphere extends AbstractClosedSurface {

    private double diameter = 1.0;

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    public Sphere withDiameter(final double diameter) {
        setDiameter(diameter);
        return this;
    }

    @Override
    public Sphere withParentTransform(final CompilableTransform parentTransform) {
        return (Sphere) super.withParentTransform(parentTransform);
    }

    @Override
    public Sphere withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (Sphere) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public Sphere withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        return (Sphere) super.withGeometryContextFactory(geometryContextFactory);
    }

    @Override
    public Sphere withFacing(final Facing facing) {
        return (Sphere) super.withFacing(facing);
    }

    @Override
    public Sphere clone() {
        return (Sphere) super.clone();
    }

    @Override
    protected AbstractShadeableRayTraceableSphere createCompiledGeometry(final Optional<CompilationCache> compilationCache) {
        final AbstractShadeableRayTraceableSphere.Configuration configuration = createConfiguration(compilationCache);

        switch (getFacingOrDefault(Facing.OUTWARD_FACING)) {
            case DOUBLE_SIDED:
                return new DoubleSidedShadeableRayTraceableSphere(configuration, getDiameter());
            case INWARD_FACING:
                return new InwardFacingShadeableRayTraceableSphere(configuration, getDiameter());
            default:
                return new OutwardFacingShadeableRayTraceableSphere(configuration, getDiameter());
        }
    }

}
