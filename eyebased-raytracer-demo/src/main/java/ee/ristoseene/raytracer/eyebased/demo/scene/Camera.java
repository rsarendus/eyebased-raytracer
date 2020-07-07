package ee.ristoseene.raytracer.eyebased.demo.scene;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.projection.CompiledRayProducer;
import ee.ristoseene.raytracer.eyebased.projection.ray.DefaultTransformableEye;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;

import java.util.Objects;
import java.util.Optional;

public abstract class Camera {

    protected final DefaultTransformableEye transformableEye = new DefaultTransformableEye();
    protected Projection projection;

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(final Projection projection) {
        this.projection = projection;
    }

    public Camera withProjection(final Projection projection) {
        setProjection(projection);
        return this;
    }

    public CompiledRayProducer compileRayProducer(final Optional<CompilationCache> compilationCache, final int width, final int height) {
        Objects.requireNonNull(projection, "Projection not provided");
        return transformableEye
                .withRayOriginProducer(projection.createRayOriginProducer(width, height))
                .withRayDirectionProducer(projection.createRayDirectionProducer(width, height))
                .compile(compilationCache);
    }

    public Matrix4x4.Accessible createProjectionMatrix(final int width, final int height) {
        return VecMath.multiply(
                Objects.requireNonNull(projection, "Projection not provided").createProjectionMatrix(width, height),
                transformableEye.getParentTransform().compile(Optional.empty()).getInverseTransform(),
                Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw
        );
    }

    public abstract void rotate(double x, double y);

    public abstract void zoom(double z, boolean alt);

}
