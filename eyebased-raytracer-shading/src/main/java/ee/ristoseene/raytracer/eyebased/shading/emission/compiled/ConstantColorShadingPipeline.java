package ee.ristoseene.raytracer.eyebased.shading.emission.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

public class ConstantColorShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    public static final ConstantColorShadingPipeline BLACK_INSTANCE = new ConstantColorShadingPipeline(Vectors.VECTOR3_ZERO_ZERO_ZERO);

    private final Vector3.Accessible color;

    public ConstantColorShadingPipeline(final Vector3.Accessible color) {
        this.color = Objects.requireNonNull(color, "Color not provided");
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(shadingContext, color);
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
