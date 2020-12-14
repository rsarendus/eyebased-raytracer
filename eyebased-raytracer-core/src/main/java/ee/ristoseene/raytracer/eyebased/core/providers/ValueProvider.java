package ee.ristoseene.raytracer.eyebased.core.providers;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

@FunctionalInterface
public interface ValueProvider<T> extends CompiledObject {

    T getValue(ShadingContext shadingContext);

}
