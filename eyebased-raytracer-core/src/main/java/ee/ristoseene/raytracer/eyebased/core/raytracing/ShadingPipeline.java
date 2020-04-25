package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;

@FunctionalInterface
public interface ShadingPipeline extends CompiledObject {

    ShadingPipeline NO_OP_INSTANCE = (shadingContext, bounceContext) -> SampleValue.NO_OP_INSTANCE;

    SampleValue shade(ShadingContext shadingContext, BounceContext bounceContext);

}
