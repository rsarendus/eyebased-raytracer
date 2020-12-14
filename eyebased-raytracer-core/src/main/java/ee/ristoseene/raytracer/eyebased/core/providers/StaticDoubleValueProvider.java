package ee.ristoseene.raytracer.eyebased.core.providers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;

@FunctionalInterface
public interface StaticDoubleValueProvider extends DoubleValueProvider {

    @Override
    default double getDoubleValue(final ShadingContext shadingContext) {
        return getStaticDoubleValue();
    }

    double getStaticDoubleValue();

}
