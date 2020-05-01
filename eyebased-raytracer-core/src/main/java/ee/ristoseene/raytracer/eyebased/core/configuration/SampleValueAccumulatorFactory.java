package ee.ristoseene.raytracer.eyebased.core.configuration;

import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

@FunctionalInterface
public interface SampleValueAccumulatorFactory {

    SampleValueAccumulator create();

    SampleValueAccumulatorFactory NO_OP_INSTANCE = () -> SampleValueAccumulator.NO_OP_INSTANCE;

    TypedAttribute<SampleValueAccumulatorFactory> KEY = new TypedAttribute<>() {

        @Override
        public Class<SampleValueAccumulatorFactory> getValueType() {
            return SampleValueAccumulatorFactory.class;
        }

        @Override
        public SampleValueAccumulatorFactory getDefaultValue() {
            return NO_OP_INSTANCE;
        }

    };

}
