package ee.ristoseene.raytracer.eyebased.shading.simple.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.DoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

public class DynamicColorShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    private final ValueProvider<Vector3.Accessible> colorProvider;
    private final DoubleValueProvider alphaProvider;

    public DynamicColorShadingPipeline(final ValueProvider<Vector3.Accessible> colorProvider, final DoubleValueProvider alphaProvider) {
        this.colorProvider = Objects.requireNonNull(colorProvider, "Color not provided");
        this.alphaProvider = Objects.requireNonNull(alphaProvider, "Alpha not provided");
    }

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(shadingContext,
                colorProvider.getValue(shadingContext), alphaProvider.getDoubleValue(shadingContext)
        );
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
