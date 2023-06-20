package ee.ristoseene.raytracer.eyebased.demo.impl.shading;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.vecmath.Vector3;

import java.util.Optional;

public class SurfaceNormalToColorShadingPipeline implements ShadingPipeline, ShadingConfiguration {

    public static final SurfaceNormalToColorShadingPipeline INSTANCE = new SurfaceNormalToColorShadingPipeline();

    @Override
    public SampleValue shade(final ShadingContext shadingContext, final BounceContext bounceContext) {
        final Vector3.Accessible normal = shadingContext.getGeometryContext().getSurfaceNormal();
        return shadingContext.getAttributeValue(SampleValueFactory.KEY).create(
                shadingContext, normal.x() * 0.5 + 0.5, normal.y() * 0.5 + 0.5, normal.z() * 0.5 + 0.5
        );
    }

    @Override
    public ShadingPipeline compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

}
