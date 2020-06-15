package ee.ristoseene.raytracer.eyebased.core.raytracing;

@FunctionalInterface
public interface Shadeable {

    SampleValue shade(RayIntersectionContext rayIntersectionContext, ShadingProcessor shadingProcessor);

    Shadeable NO_OP_INSTANCE = (rayIntersectionContext, shadingProcessor) -> SampleValue.NO_OP_INSTANCE;

    TypedAttribute<Shadeable> KEY = new TypedAttribute<>() {

        @Override
        public Class<Shadeable> getValueType() {
            return Shadeable.class;
        }

        @Override
        public Shadeable getDefaultValue() {
            return NO_OP_INSTANCE;
        }

    };

}
