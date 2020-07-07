package ee.ristoseene.raytracer.eyebased.demo.wireframe;

import ee.ristoseene.vecmath.Matrix4x4;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiTypeWireframeRenderer<T> implements WireframeRenderer<T> {

    private final Class<T> type;
    private final Map<Class<? extends T>, WireframeRenderer<? extends T>> renderers;

    public MultiTypeWireframeRenderer(final Class<T> type, final Set<WireframeRenderer<? extends T>> renderers) {
        this.type = Objects.requireNonNull(type);
        this.renderers = Objects.requireNonNull(renderers).stream()
                .collect(Collectors.toMap(r -> r.getType(), r -> r));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void render(Rasterizer rasterizer, Matrix4x4.Accessible projection, T object) {
        Class type = object.getClass();

        while (type != null) {
            final WireframeRenderer renderer = renderers.get(type);

            if (renderer != null) {
                renderer.render(rasterizer, projection, object);
                return;
            }

            type = type.getSuperclass();
        }
    }

    @Override
    public Class<T> getType() {
        return type;
    }

}
