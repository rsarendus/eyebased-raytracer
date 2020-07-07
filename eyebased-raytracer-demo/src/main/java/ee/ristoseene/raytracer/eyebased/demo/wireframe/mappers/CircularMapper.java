package ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public interface CircularMapper {

    void mapToPosition(Vector3.Consumer resultConsumer, double radius, Axis axis, double angle);
    <R> R mapToPosition(double radius, Axis axis, double angle, Vector3.Factory<R> resultFactory);

    void mapToPosition(Vector4.Consumer resultConsumer, double radius, Axis axis, double angle);
    <R> R mapToPosition(double radius, Axis axis, double angle, Vector4.Factory<R> resultFactory);

}
