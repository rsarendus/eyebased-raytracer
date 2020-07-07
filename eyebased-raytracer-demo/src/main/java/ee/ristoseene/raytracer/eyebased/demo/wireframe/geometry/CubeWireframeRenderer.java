package ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.mutable.MutableVector4;

public abstract class CubeWireframeRenderer<T> implements WireframeRenderer<T> {

    protected void renderCube(final Rasterizer rasterizer, final Matrix4x4.Accessible transform, final double scale, final int divisions) {
        final MutableVector4 p0 = new MutableVector4();
        final MutableVector4 p1 = new MutableVector4();

        final int divisions1 = divisions + 1;

        for (int d = 0; d <= divisions1; ++d) {
            final double multiplier = 2.0 * scale * d / divisions1 - scale;

            // Variable X-axis
            p0.xyzw(multiplier, -scale, -scale, 1.0);
            p1.xyzw(multiplier, +scale, -scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(multiplier, -scale, +scale, 1.0);
            p1.xyzw(multiplier, +scale, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(multiplier, -scale, -scale, 1.0);
            p1.xyzw(multiplier, -scale, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(multiplier, +scale, -scale, 1.0);
            p1.xyzw(multiplier, +scale, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            // Variable Y-axis
            p0.xyzw(-scale, multiplier, -scale, 1.0);
            p1.xyzw(+scale, multiplier, -scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(-scale, multiplier, +scale, 1.0);
            p1.xyzw(+scale, multiplier, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(-scale, multiplier, -scale, 1.0);
            p1.xyzw(-scale, multiplier, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(+scale, multiplier, -scale, 1.0);
            p1.xyzw(+scale, multiplier, +scale, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            // Variable Z-axis
            p0.xyzw(-scale, -scale, multiplier, 1.0);
            p1.xyzw(+scale, -scale, multiplier, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(-scale, +scale, multiplier, 1.0);
            p1.xyzw(+scale, +scale, multiplier, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(-scale, -scale, multiplier, 1.0);
            p1.xyzw(-scale, +scale, multiplier, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);

            p0.xyzw(+scale, -scale, multiplier, 1.0);
            p1.xyzw(+scale, +scale, multiplier, 1.0);
            VecMath.multiply(p0, transform, p0);
            VecMath.multiply(p1, transform, p1);
            rasterizer.rasterizeLine(p0, p1);
        }

    }

}
