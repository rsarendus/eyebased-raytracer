package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.rasterization.ClippingPerspectiveDividingRasterizer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.awt.Graphics;
import java.util.Objects;

import static ee.ristoseene.raytracer.eyebased.demo.impl.Constants.ORIENTATION;

public class RasterizerImpl extends ClippingPerspectiveDividingRasterizer {

    private final Graphics graphics;
    private final double halfHorizontal;
    private final double halfVertical;

    public RasterizerImpl(final Graphics graphics, final int width, final int height) {
        this.graphics = Objects.requireNonNull(graphics);
        this.halfHorizontal = width * 0.5;
        this.halfVertical = height * 0.5;
    }

    @Override
    protected void rasterizeLine(final Vector3.Accessible p0, final Vector3.Accessible p1) {
        final int x0 = (int) (VecMath.dot(ORIENTATION.getRightAxis(), p0) * halfHorizontal + halfHorizontal);
        final int y0 = (int) (halfVertical - VecMath.dot(ORIENTATION.getUpAxis(), p0) * halfVertical);

        final int x1 = (int) (VecMath.dot(ORIENTATION.getRightAxis(), p1) * halfHorizontal + halfHorizontal);
        final int y1 = (int) (halfVertical - VecMath.dot(ORIENTATION.getUpAxis(), p1) * halfVertical);

        graphics.drawLine(x0, y0, x1, y1);
    }

}
