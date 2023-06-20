package ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.CompilableRayTraceableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractRayTraceablePrimitive implements CompilableRayTraceableGeometry {

    protected final CompiledTransform transform;
    protected final Matrix4x4.Accessible globalToUnitLocalSpaceTransform;
    protected final RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;
    protected final AABB aabb;

    protected AbstractRayTraceablePrimitive(
            final Configuration configuration, final double scale,
            final Function<Matrix4x4.Accessible, AABB> aabbResolver
    ) {
        Objects.requireNonNull(configuration, "Configuration not provided");

        Matrix4x4.Accessible unitLocalToGlobalSpaceTransform;
        transform = Objects.requireNonNull(configuration.getTransform(), "Transform not provided");
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

    public CompiledTransform getTransform() {
        return transform;
    }

    public RaySurfaceIntersectionGeometryContextFactory getGeometryContextFactory() {
        return geometryContextFactory;
    }

    @Override
    public AABB getAABB() {
        return aabb;
    }

    public static class Configuration {

        private CompiledTransform transform;
        private RaySurfaceIntersectionGeometryContextFactory geometryContextFactory;

        public CompiledTransform getTransform() {
            return transform;
        }

        public void setTransform(final CompiledTransform transform) {
            this.transform = transform;
        }

        public Configuration withTransform(final CompiledTransform transform) {
            setTransform(transform);
            return this;
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

        public Configuration withConfiguration(final Configuration configuration) {
            setTransform(configuration.getTransform());
            setGeometryContextFactory(configuration.getGeometryContextFactory());
            return this;
        }

    }

}
