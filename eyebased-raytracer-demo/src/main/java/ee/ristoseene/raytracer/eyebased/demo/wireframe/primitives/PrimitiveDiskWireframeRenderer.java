package ee.ristoseene.raytracer.eyebased.demo.wireframe.primitives;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.CircularWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.TransformableWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.CircularMapper;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.Disk;
import ee.ristoseene.vecmath.Matrix4x4;

public final class PrimitiveDiskWireframeRenderer extends CircularWireframeRenderer<Disk> implements TransformableWireframeRenderer<Disk> {

    public PrimitiveDiskWireframeRenderer(final CircularMapper circularMapper) {
        super(circularMapper, true);
    }

    @Override
    public Class<Disk> getType() {
        return Disk.class;
    }

    @Override
    public void render(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final Disk disk) {
        renderCircle(rasterizer,
                TransformableWireframeRenderer.getTransformationMatrix(projection, disk),
                disk.getDiameter() * 0.5,
                disk.getNormal(),
                16
        );
    }

}
