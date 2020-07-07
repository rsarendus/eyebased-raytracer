package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.CircularMapper;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public class CircularMapperImpl implements CircularMapper {

    @Override
    public void mapToPosition(final Vector3.Consumer resultConsumer, final double radius, final Axis axis, final double angle) {
        switch (axis) {
            case POSITIVE_X:
            case NEGATIVE_X:
                resultConsumer.xyz(0.0, Math.cos(angle) * radius, Math.sin(angle) * radius);
                break;
            case POSITIVE_Y:
            case NEGATIVE_Y:
                resultConsumer.xyz(Math.cos(angle) * radius, 0.0, Math.sin(angle) * radius);
                break;
            case POSITIVE_Z:
            case NEGATIVE_Z:
                resultConsumer.xyz(Math.cos(angle) * radius, Math.sin(angle) * radius, 0.0);
                break;
        }
    }

    @Override
    public <R> R mapToPosition(final double radius, final Axis axis, final double angle, final Vector3.Factory<R> resultFactory) {
        switch (axis) {
            case POSITIVE_X:
            case NEGATIVE_X:
                return resultFactory.create(0.0, Math.cos(angle) * radius, Math.sin(angle) * radius);
            case POSITIVE_Y:
            case NEGATIVE_Y:
                return resultFactory.create(Math.cos(angle) * radius, 0.0, Math.sin(angle) * radius);
            case POSITIVE_Z:
            case NEGATIVE_Z:
                return resultFactory.create(Math.cos(angle) * radius, Math.sin(angle) * radius, 0.0);
        }
        throw new IllegalStateException("Unexpected axis");
    }

    @Override
    public void mapToPosition(final Vector4.Consumer resultConsumer, final double radius, final Axis axis, final double angle) {
        switch (axis) {
            case POSITIVE_X:
            case NEGATIVE_X:
                resultConsumer.xyzw(0.0, Math.cos(angle) * radius, Math.sin(angle) * radius, 1.0);
                break;
            case POSITIVE_Y:
            case NEGATIVE_Y:
                resultConsumer.xyzw(Math.cos(angle) * radius, 0.0, Math.sin(angle) * radius, 1.0);
                break;
            case POSITIVE_Z:
            case NEGATIVE_Z:
                resultConsumer.xyzw(Math.cos(angle) * radius, Math.sin(angle) * radius, 0.0, 1.0);
                break;
        }
    }

    @Override
    public <R> R mapToPosition(final double radius, final Axis axis, final double angle, final Vector4.Factory<R> resultFactory) {
        switch (axis) {
            case POSITIVE_X:
            case NEGATIVE_X:
                return resultFactory.create(0.0, Math.cos(angle) * radius, Math.sin(angle) * radius, 1.0);
            case POSITIVE_Y:
            case NEGATIVE_Y:
                return resultFactory.create(Math.cos(angle) * radius, 0.0, Math.sin(angle) * radius, 1.0);
            case POSITIVE_Z:
            case NEGATIVE_Z:
                return resultFactory.create(Math.cos(angle) * radius, Math.sin(angle) * radius, 0.0, 1.0);
        }
        throw new IllegalStateException("Unexpected axis");
    }

}
