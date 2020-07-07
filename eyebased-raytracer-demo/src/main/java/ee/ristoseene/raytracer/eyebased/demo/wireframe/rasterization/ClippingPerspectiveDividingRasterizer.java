package ee.ristoseene.raytracer.eyebased.demo.wireframe.rasterization;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector4;
import ee.ristoseene.vecmath.mutable.MutableVector4;

public abstract class ClippingPerspectiveDividingRasterizer extends PerspectiveDividingRasterizer {

    private static final Vector4.Accessible[] CLIPPING_PLANES = {
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(-1.0, 0.0, 0.0, 1.0),
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(+1.0, 0.0, 0.0, 1.0),
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(0.0, -1.0, 0.0, 1.0),
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(0.0, +1.0, 0.0, 1.0),
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(0.0, 0.0, -1.0, 1.0),
            Factories.FACTORY_CONST_VECTOR4_xyzw.create(0.0, 0.0, +1.0, 1.0),
    };

    @Override
    public void rasterizeLine(final Vector4.Accessible p0, final Vector4.Accessible p1) {
        final boolean p0inClipSpace = isInClipSpace(p0);
        final boolean p1inClipSpace = isInClipSpace(p1);

        if (p0inClipSpace & p1inClipSpace) {
            super.rasterizeLine(p0, p1);
        } else if (p0inClipSpace) {
            super.rasterizeLine(p0, calculateClampedPoint(p0, p1));
        } else if (p1inClipSpace) {
            super.rasterizeLine(p1, calculateClampedPoint(p1, p0));
        } else {
            rasterizeClampedLineIfIntersectsWithClipSpace(p0, p1);
        }
    }

    private void rasterizeClampedLineIfIntersectsWithClipSpace(final Vector4.Accessible p0, final Vector4.Accessible p1) {
        final Vector4.Accessible p0prim = calculateClampedPoint(p1, p0);
        if (!isInClipSpace(p0prim)) return; // TODO: find a more reliable way to counter the imprecision of clamping and to check if the line is really outside of clipping bounds

        final Vector4.Accessible p1prim = calculateClampedPoint(p0, p1);
        if (!isInClipSpace(p1prim)) return; // TODO: find a more reliable way to counter the imprecision of clamping and to check if the line is really outside of clipping bounds

        super.rasterizeLine(p0prim, p1prim);
    }

    private static Vector4.Accessible calculateClampedPoint(final Vector4.Accessible origin, final Vector4.Accessible pointToClamp) {
        Vector4.AccessibleAndMutable point = new MutableVector4(pointToClamp);

        for (final Vector4.Accessible clippingPlane : CLIPPING_PLANES) {
            final double d0 = VecMath.dot(origin, clippingPlane);
            final double d1 = VecMath.dot(point, clippingPlane);

            if (d0 * d1 >= 0.0) continue;

            final double a = d0 / (d0 - d1);
            VecMath.lerp(point, origin, point, a);
        }

        return point;
    }

    private static boolean isInClipSpace(final Vector4.Accessible point) {
        final double x = point.x();
        final double y = point.y();
        final double z = point.z();

        final double maxClip = point.w();
        final double minClip = -maxClip;

        return x >= minClip & x <= maxClip
             & y >= minClip & y <= maxClip
             & z >= minClip & z <= maxClip
        ;
    }

}
