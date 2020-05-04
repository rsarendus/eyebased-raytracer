package ee.ristoseene.raytracer.eyebased.shading.providers;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

@FunctionalInterface
public interface DoubleValueProvider extends CompiledObject {

    double getDoubleValue(ShadingContext shadingContext);

}
