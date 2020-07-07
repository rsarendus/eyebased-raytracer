package ee.ristoseene.raytracer.eyebased.demo.wireframe;

import ee.ristoseene.vecmath.Matrix4x4;

public interface WireframeRenderer<T> {

    void render(Rasterizer rasterizer, Matrix4x4.Accessible projection, T object);

    Class<T> getType();

}
