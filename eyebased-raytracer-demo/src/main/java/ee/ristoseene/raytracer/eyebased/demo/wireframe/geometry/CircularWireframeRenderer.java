package ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.CircularMapper;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.mutable.MutableVector4;

import java.util.Objects;

public abstract class CircularWireframeRenderer<T> implements WireframeRenderer<T> {

    private final CircularMapper circularMapper;
    private final boolean renderSpokes;

    protected CircularWireframeRenderer(final CircularMapper circularMapper, final boolean renderSpokes) {
        this.circularMapper = Objects.requireNonNull(circularMapper);
        this.renderSpokes = renderSpokes;
    }

    public CircularMapper getCircularMapper() {
        return circularMapper;
    }

    protected void renderCircle(final Rasterizer rasterizer, final Matrix4x4.Accessible transform, final double radius, final Axis axis, final int segments) {
        final MutableVector4 p0 = new MutableVector4();
        final MutableVector4 p1 = new MutableVector4();

        final double segmentalMultiplier = 2.0 * Math.PI / segments;

        for (int s = 0; s < segments; ++s) {
            circularMapper.mapToPosition(p0, radius, axis, s * segmentalMultiplier);
            VecMath.multiply(p0, transform, p0);

            circularMapper.mapToPosition(p1, radius, axis, (s + 1) * segmentalMultiplier);
            VecMath.multiply(p1, transform, p1);

            rasterizer.rasterizeLine(p0, p1);

            if (!renderSpokes) continue;

            p1.xyzw(0.0, 0.0, 0.0, 1.0);
            VecMath.multiply(p1, transform, p1);

            rasterizer.rasterizeLine(p0, p1);
        }
    }

}
