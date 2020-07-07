package ee.ristoseene.raytracer.eyebased.demo.scene;

import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.Matrix4x4;

public interface Projection {

    double getFov();
    void setFov(double fov);

    RayOriginProducer createRayOriginProducer(int width, int height);
    RayDirectionProducer createRayDirectionProducer(int width, int height);

    Matrix4x4.Accessible createProjectionMatrix(int width, int height);

}
