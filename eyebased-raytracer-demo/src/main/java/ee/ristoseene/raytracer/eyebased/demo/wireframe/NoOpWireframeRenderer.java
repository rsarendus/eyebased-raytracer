package ee.ristoseene.raytracer.eyebased.demo.wireframe;

import ee.ristoseene.vecmath.Matrix4x4;

public final class NoOpWireframeRenderer implements WireframeRenderer<Object> {

    @Override
    public void render(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final Object object) {
    }

    @Override
    public Class<Object> getType() {
        return Object.class;
    }

}
