package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.geometry.common.AbstractGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.AbstractRayTraceablePrimitive;

import java.util.Optional;

public abstract class AbstractPrimitive extends AbstractGeometry {

    private RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;

    public RaySurfaceIntersectionGeometryContextFactory getGeometryContextFactory() {
        return geometryContextFactory;
    }

    public void setGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        this.geometryContextFactory = geometryContextFactory;
    }

    public AbstractPrimitive withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
        setGeometryContextFactory(geometryContextFactory);
        return this;
    }

    @Override
    public AbstractPrimitive withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractPrimitive) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractPrimitive withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (AbstractPrimitive) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public AbstractPrimitive clone() {
        return (AbstractPrimitive) super.clone();
    }

    @Override
    protected AbstractRayTraceablePrimitive.Configuration createConfiguration(Optional<CompilationCache> compilationCache) {
        return new AbstractRayTraceablePrimitive.Configuration()
                .withConfiguration(super.createConfiguration(compilationCache))
                .withGeometryContextFactory(getGeometryContextFactoryOrNoOp());
    }

    protected RaySurfaceIntersectionGeometryContextFactory getGeometryContextFactoryOrNoOp() {
        return (geometryContextFactory == null)
                ? RaySurfaceIntersectionGeometryContextFactory.NO_OP_INSTANCE
                : geometryContextFactory;
    }

}
