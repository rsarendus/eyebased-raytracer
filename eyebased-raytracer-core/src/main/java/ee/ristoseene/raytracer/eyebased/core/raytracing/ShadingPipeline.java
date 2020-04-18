package ee.ristoseene.raytracer.eyebased.core.raytracing;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.vecmath.Vector4;

@FunctionalInterface
public interface ShadingPipeline extends CompiledObject {

    Vector4.Accessible shade(ShadingContext shadingContext, BounceContext bounceContext);

}
