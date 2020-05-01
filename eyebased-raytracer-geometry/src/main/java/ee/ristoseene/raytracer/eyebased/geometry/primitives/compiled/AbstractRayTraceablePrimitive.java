package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.AbstractRayTraceableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractRayTraceablePrimitive extends AbstractRayTraceableGeometry {

    protected final Matrix4x4.Accessible globalToUnitLocalSpaceTransform;
    protected final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;
    protected final AABB aabb;

    protected AbstractRayTraceablePrimitive(
            final Configuration configuration, final double scale,
            final Function<Matrix4x4.Accessible, AABB> aabbResolver
    ) {
        super(configuration);

        Matrix4x4.Accessible unitLocalToGlobalSpaceTransform;
        geometryContextFactory = Objects.requireNonNull(configuration.getGeometryContextFactory(), "Geometry context factory not provided");
        Objects.requireNonNull(aabbResolver, "AABB resolver not provided");

        if (scale == 1.0) {
            unitLocalToGlobalSpaceTransform = configuration.getTransform();
            globalToUnitLocalSpaceTransform = configuration.getTransform().getInverseTransform();
        } else {
            final Matrix4x4.Accessible scalingMatrix = new ImmutableMatrix4x4(scale, scale, scale, 1.0);
            unitLocalToGlobalSpaceTransform = VecMath.multiply(configuration.getTransform(), scalingMatrix, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw);
            globalToUnitLocalSpaceTransform = VecMath.inverse(unitLocalToGlobalSpaceTransform, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw);
        }

        aabb = Objects.requireNonNull(aabbResolver.apply(unitLocalToGlobalSpaceTransform), "AABB not provided");
    }

    protected Vector3.Accessible localizeRayOrigin(final Ray ray) {
        return VecMath.transformPosition(globalToUnitLocalSpaceTransform, ray.getOrigin(), Factories.FACTORY_CONST_VECTOR3_xyz);
    }

    protected Vector3.Accessible localizeRayDirection(final Ray ray) {
        return VecMath.transformDirection(globalToUnitLocalSpaceTransform, ray.getDirection(), Factories.FACTORY_CONST_VECTOR3_xyz);
    }

    public RaySurfaceIntersectionGeometryContextFactory getGeometryContextFactory() {
        return geometryContextFactory;
    }

    @Override
    public AABB getAABB() {
        return aabb;
    }

    public static class Configuration extends AbstractRayTraceableGeometry.Configuration {

        private RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;

        @Override
        public Configuration withTransform(final CompiledTransform transform) {
            return (Configuration) super.withTransform(transform);
        }

        @Override
        public Configuration withShadingPipeline(final ShadingPipeline shadingPipeline) {
            return (Configuration) super.withShadingPipeline(shadingPipeline);
        }

        public RaySurfaceIntersectionGeometryContextFactory getGeometryContextFactory() {
            return geometryContextFactory;
        }

        public void setGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
            this.geometryContextFactory = geometryContextFactory;
        }

        public Configuration withGeometryContextFactory(final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory) {
            setGeometryContextFactory(geometryContextFactory);
            return this;
        }

        @Override
        public Configuration withConfiguration(final AbstractRayTraceableGeometry.Configuration configuration) {
            if (configuration instanceof Configuration) {
                setGeometryContextFactory(((Configuration) configuration).getGeometryContextFactory());
            }
            return (Configuration) super.withConfiguration(configuration);
        }

    }

}
