package ee.ristoseene.raytracer.eyebased.geometry.primitives.utilities;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.core.raytracing.aabb.TraceableAABB;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

public final class AABBFactory {

    public static AABB createUnitSphereAABB(final Matrix4x4.Accessible transform) {
        final Vector3.Accessible extent = new ImmutableVector3(
                VecMath.length(transform.const$xXYZ()),
                VecMath.length(transform.const$yXYZ()),
                VecMath.length(transform.const$zXYZ())
        );
        return new TraceableAABB(
                VecMath.subtract(transform.const$Txyz(), extent, Factories.FACTORY_CONST_VECTOR3_xyz),
                VecMath.add(transform.const$Txyz(), extent, Factories.FACTORY_CONST_VECTOR3_xyz)
        );
    }

    private AABBFactory() {}

}
