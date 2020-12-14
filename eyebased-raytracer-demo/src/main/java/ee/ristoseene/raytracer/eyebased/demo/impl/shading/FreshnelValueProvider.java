package ee.ristoseene.raytracer.eyebased.demo.impl.shading;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.providers.CompilableDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.vecmath.VecMath;

import java.util.Optional;

public class FreshnelValueProvider implements CompilableDoubleValueProvider, DoubleValueProvider {

    private final double freshnel;

    public FreshnelValueProvider(final double freshnel) {
        this.freshnel = freshnel;
    }

    @Override
    public DoubleValueProvider compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    @Override
    public double getDoubleValue(final ShadingContext shadingContext) {
        return Math.pow(dot(shadingContext), freshnel);
    }

    private static double dot(final ShadingContext shadingContext) {
        return -VecMath.dot(
                shadingContext.getRayIntersectionContext().getIntersectingRay().getDirection(),
                shadingContext.getGeometryContext().getSurfaceNormal()
        );
    }

}
