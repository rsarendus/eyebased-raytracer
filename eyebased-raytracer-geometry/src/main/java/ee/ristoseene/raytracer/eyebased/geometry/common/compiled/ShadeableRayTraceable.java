package ee.ristoseene.raytracer.eyebased.geometry.common.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracingResult;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Shadeable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

public interface ShadeableRayTraceable extends RayTraceable, RayTracingResult, Shadeable {

    @Override
    @SuppressWarnings("unchecked")
    default <T> T getAttributeValue(final TypedAttribute<T> key) {
        if (Shadeable.KEY.equals(key) || key.getValueType().isInstance(this)) {
            return (T) this;
        } else {
            return key.getDefaultValue();
        }
    }

    @Override
    default RayTraceable getSourceRayTraceable() {
        return this;
    }

}
