package ee.ristoseene.raytracer.eyebased.demo.wireframe.rasterization;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public abstract class PerspectiveDividingRasterizer implements Rasterizer {

    protected abstract void rasterizeLine(final Vector3.Accessible p0, final Vector3.Accessible p1);

    @Override
    public void rasterizeLine(final Vector4.Accessible p0, final Vector4.Accessible p1) {
        rasterizeLine(perspectiveDivide(p0), perspectiveDivide(p1));
    }

    protected static Vector3.Accessible perspectiveDivide(final Vector4.Accessible p) {
        final double multiplier = 1.0 / p.w();

        return Factories.FACTORY_CONST_VECTOR3_xyz.create(
                p.x() * multiplier,
                p.y() * multiplier,
                p.z() * multiplier
        );
    }

}
