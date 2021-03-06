package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleRay;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.projection.CompiledRayProducer;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public abstract class AbstractTransformingRayProducer implements CompiledRayProducer {

    protected final CompiledTransform transformer;

    protected AbstractTransformingRayProducer(final CompiledTransform transformer) {
        this.transformer = Objects.requireNonNull(transformer, "Ray transformer not provided");
    }

    protected Ray createTransformedRay(final Vector3.Accessible origin, final Vector3.Accessible direction) {
        return new SimpleRay(
                VecMath.transformPosition(transformer, origin, Factories.FACTORY_CONST_VECTOR3_xyz),
                VecMath.transformDirection(transformer, direction, Factories.FACTORY_CONST_VECTOR3_xyz)
        );
    }

}
