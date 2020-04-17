package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationUtils;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.Transformable;
import ee.ristoseene.raytracer.eyebased.core.utilities.VecMathExtended;
import ee.ristoseene.raytracer.eyebased.projection.CompilableEye;
import ee.ristoseene.raytracer.eyebased.projection.CompiledRayProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.raytracer.eyebased.projection.origin.ConstantOffsetRayOriginProducer;

import java.util.Objects;
import java.util.Optional;

public class DefaultTransformableEye extends Transformable implements CompilableEye {

    private RayDirectionProducer rayDirectionProducer;
    private RayOriginProducer rayOriginProducer;

    public RayDirectionProducer getRayDirectionProducer() {
        return rayDirectionProducer;
    }

    public void setRayDirectionProducer(final RayDirectionProducer rayDirectionProducer) {
        this.rayDirectionProducer = rayDirectionProducer;
    }

    public DefaultTransformableEye withRayDirectionProducer(final RayDirectionProducer rayDirectionProducer) {
        setRayDirectionProducer(rayDirectionProducer);
        return this;
    }

    public RayOriginProducer getRayOriginProducer() {
        return rayOriginProducer;
    }

    public void setRayOriginProducer(final RayOriginProducer rayOriginProducer) {
        this.rayOriginProducer = rayOriginProducer;
    }

    public DefaultTransformableEye withRayOriginProducer(final RayOriginProducer rayOriginProducer) {
        setRayOriginProducer(rayOriginProducer);
        return this;
    }

    @Override
    public DefaultTransformableEye withParentTransform(CompilableTransform parentTransform) {
        return (DefaultTransformableEye) super.withParentTransform(parentTransform);
    }

    @Override
    public CompiledRayProducer compile(final Optional<CompilationCache> compilationCache) {
        Objects.requireNonNull(rayDirectionProducer, "Ray direction producer not provided");

        return CompilationUtils.getCachedOrCompileAndCache(
                compilationCache,
                this,
                CompiledRayProducer.class,
                () -> createDefaultRayProducer(compilationCache)
        );
    }

    private CompiledRayProducer createDefaultRayProducer(final Optional<CompilationCache> compilationCache) {
        final CompiledTransform parentTransform = getCompiledParentTransform(compilationCache);
        final RayOriginProducer originProducer = (rayOriginProducer == null)
                ? new ConstantOffsetRayOriginProducer(Vectors.VECTOR3_ZERO_ZERO_ZERO)
                : rayOriginProducer;

        if (VecMathExtended.equal(parentTransform, CompiledTransform.IDENTITY_TRANSFORM)) {
            return new SimpleRayProducer(originProducer, rayDirectionProducer);
        } else {
            return new DefaultTransformingRayProducer(parentTransform, originProducer, rayDirectionProducer);
        }
    }

}
