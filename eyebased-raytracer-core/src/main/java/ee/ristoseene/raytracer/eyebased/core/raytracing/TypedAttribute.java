package ee.ristoseene.raytracer.eyebased.core.raytracing;

public interface TypedAttribute<T> {

    Class<T> getValueType();
    T getDefaultValue();

}
