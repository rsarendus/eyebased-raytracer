package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.SphericalMapper;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public class SphericalMapperImpl implements SphericalMapper {

    @Override
    public void mapToPosition(final Vector3.Consumer resultConsumer, final double radius, final double latitude, final double longitude) {
        final double planarRadius = Math.cos(latitude) * radius;

        resultConsumer.xyz(
                Math.cos(longitude) * planarRadius,
                Math.sin(latitude) * radius,
                Math.sin(longitude) * planarRadius
        );
    }

    @Override
    public <R> R mapToPosition(final double radius, final double latitude, final double longitude, final Vector3.Factory<R> resultFactory) {
        final double planarRadius = Math.cos(latitude) * radius;

        return resultFactory.create(
                Math.cos(longitude) * planarRadius,
                Math.sin(latitude) * radius,
                Math.sin(longitude) * planarRadius
        );
    }

    @Override
    public void mapToPosition(final Vector4.Consumer resultConsumer, final double radius, final double latitude, final double longitude) {
        final double planarRadius = Math.cos(latitude) * radius;

        resultConsumer.xyzw(
                Math.cos(longitude) * planarRadius,
                Math.sin(latitude) * radius,
                Math.sin(longitude) * planarRadius,
                1.0
        );
    }

    @Override
    public <R> R mapToPosition(final double radius, final double latitude, final double longitude, final Vector4.Factory<R> resultFactory) {
        final double planarRadius = Math.cos(latitude) * radius;

        return resultFactory.create(
                Math.cos(longitude) * planarRadius,
                Math.sin(latitude) * radius,
                Math.sin(longitude) * planarRadius,
                1.0
        );
    }

}
