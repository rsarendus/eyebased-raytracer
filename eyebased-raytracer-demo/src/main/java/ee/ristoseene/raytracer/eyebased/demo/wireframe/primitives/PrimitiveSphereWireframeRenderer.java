package ee.ristoseene.raytracer.eyebased.demo.wireframe.primitives;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.TransformableWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.UVSphereWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.SphericalMapper;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.Sphere;
import ee.ristoseene.vecmath.Matrix4x4;

public final class PrimitiveSphereWireframeRenderer extends UVSphereWireframeRenderer<Sphere> implements TransformableWireframeRenderer<Sphere> {

    public PrimitiveSphereWireframeRenderer(final SphericalMapper sphericalMapper) {
        super(sphericalMapper);
    }

    @Override
    public Class<Sphere> getType() {
        return Sphere.class;
    }

    @Override
    public void render(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final Sphere sphere) {
        renderSphere(rasterizer,
                TransformableWireframeRenderer.getTransformationMatrix(projection, sphere),
                sphere.getDiameter() * 0.5,
                9, 16
        );
    }

}
