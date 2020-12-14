package ee.ristoseene.raytracer.eyebased.shading.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSampleResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

/**
 * Instances of this class rely on {@link BounceShadingFilter} for deciding whether to bounce rays further or consider
 * the surface as black.
 *
 * Instances of this class rely on {@link BounceSampleResolver} for resolving the bounce sampling result into the
 * {@link SampleValue} emitted by the current surface.
 *
 * Instances of this class rely on {@link SampleValueFactory} for producing shading results.
 *
 * @see BounceShadingFilter
 * @see BounceShadingFilter#KEY
 *
 * @see BounceSampleResolver
 * @see BounceSampleResolver#KEY
 *
 * @see SampleValueFactory
 * @see SampleValueFactory#KEY
 *
 * @see ShadingContext#getAttributeValue(TypedAttribute)
 */
public abstract class AbstractColorMultiplyingBounceShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    protected final ValueProvider<Vector3.Accessible> colorMultiplierProvider;

    protected AbstractColorMultiplyingBounceShadingPipeline(final ValueProvider<Vector3.Accessible> colorMultiplierProvider) {
        this.colorMultiplierProvider = Objects.requireNonNull(colorMultiplierProvider, "Color multiplier not provided");
    }

    protected abstract SampleValue shadeBouncing(final ShadingContext shadingContext, final BounceContext bounceContext);

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final Vector3.Accessible colorMultiplier = colorMultiplierProvider.getValue(shadingContext);

        if (shadingContext.getAttributeValue(BounceShadingFilter.KEY).test(bounceContext, colorMultiplier)) {
            final SampleValue bounceShadingResult = shadeBouncing(shadingContext, bounceContext);
            return shadingContext.getAttributeValue(BounceSampleResolver.KEY)
                    .resolveBounceSample(shadingContext, bounceShadingResult, colorMultiplier);
        } else {
            return shadingContext.getAttributeValue(SampleValueFactory.KEY)
                    .create(shadingContext, Vectors.VECTOR3_ZERO_ZERO_ZERO);
        }
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
