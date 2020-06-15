package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;

import java.util.Objects;

public class TestTypedAttribute<T> implements TypedAttribute<T> {

    private final Class<T> valueType;
    private final T defaultValue;

    public TestTypedAttribute(final Class<T> valueType, final T defaultValue) {
        this.valueType = Objects.requireNonNull(valueType, "Value type not provided");
        this.defaultValue = Objects.requireNonNull(defaultValue, "Default value not provided");
    }

    @Override
    public Class<T> getValueType() {
        return valueType;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

}
