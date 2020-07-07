package ee.ristoseene.raytracer.eyebased.demo.wireframe;

import ee.ristoseene.vecmath.Vector4;

public interface Rasterizer {

    void rasterizeLine(Vector4.Accessible p0, Vector4.Accessible p1);

}
