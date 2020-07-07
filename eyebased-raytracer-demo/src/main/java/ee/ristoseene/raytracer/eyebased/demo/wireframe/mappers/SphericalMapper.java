package ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers;

import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public interface SphericalMapper {

    void mapToPosition(Vector3.Consumer resultConsumer, double radius, double latitude, double longitude);
    <R> R mapToPosition(double radius, double latitude, double longitude, Vector3.Factory<R> resultFactory);

    void mapToPosition(Vector4.Consumer resultConsumer, double radius, double latitude, double longitude);
    <R> R mapToPosition(double radius, double latitude, double longitude, Vector4.Factory<R> resultFactory);

}
