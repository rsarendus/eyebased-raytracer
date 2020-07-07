package ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.SphericalMapper;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.mutable.MutableVector4;

import java.util.Objects;

public abstract class UVSphereWireframeRenderer<T> implements WireframeRenderer<T> {

    private static final double HALF_PI = Math.PI * 0.5;

    private final SphericalMapper sphericalMapper;

    protected UVSphereWireframeRenderer(final SphericalMapper sphericalMapper) {
        this.sphericalMapper = Objects.requireNonNull(sphericalMapper);
    }

    public SphericalMapper getSphericalMapper() {
        return sphericalMapper;
    }

    protected void renderSphere(final Rasterizer rasterizer, final Matrix4x4.Accessible transform, final double radius, final int parallels, final int meridians) {
        final MutableVector4 p0 = new MutableVector4();
        final MutableVector4 p1 = new MutableVector4();

        final double meridianicMultiplier = 2.0 * Math.PI / meridians;

        for (int p = 0; p < parallels; ++p) {
            final double latitude = ((1.0 + p) / (parallels + 1.0) - 0.5) * Math.PI;

            for (int m = 0; m < meridians; ++m) {
                sphericalMapper.mapToPosition(p0, radius, latitude, m * meridianicMultiplier);
                VecMath.multiply(p0, transform, p0);

                sphericalMapper.mapToPosition(p1, radius, latitude, (m + 1) * meridianicMultiplier);
                VecMath.multiply(p1, transform, p1);

                rasterizer.rasterizeLine(p0, p1);
            }
        }

        final int parallels1 = parallels + 1;
        final double latitudinalMultiplier = Math.PI / parallels1;

        for (int m = 0; m < meridians; ++m) {
            final double longitude = 2.0 * Math.PI * m / meridians;

            for (int p = 0; p < parallels1; ++p) {
                sphericalMapper.mapToPosition(p0, radius, p * latitudinalMultiplier - HALF_PI, longitude);
                VecMath.multiply(p0, transform, p0);

                sphericalMapper.mapToPosition(p1, radius, (p + 1) * latitudinalMultiplier - HALF_PI, longitude);
                VecMath.multiply(p1, transform, p1);

                rasterizer.rasterizeLine(p0, p1);
            }
        }
    }

}
