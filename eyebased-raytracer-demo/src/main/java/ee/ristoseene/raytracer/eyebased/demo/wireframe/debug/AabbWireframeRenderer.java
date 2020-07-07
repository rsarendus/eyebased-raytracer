package ee.ristoseene.raytracer.eyebased.demo.wireframe.debug;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.raytracing.AABB;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.Rasterizer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry.CubeWireframeRenderer;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

public final class AabbWireframeRenderer extends CubeWireframeRenderer<AABB> implements WireframeRenderer<AABB> {

    @Override
    public Class<AABB> getType() {
        return AABB.class;
    }

    @Override
    public void render(final Rasterizer rasterizer, final Matrix4x4.Accessible projection, final AABB aabb) {
        renderCube(rasterizer,
                VecMath.multiply(projection, aabbToMatrix(aabb), Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw),
                1.0,
                0
        );
    }

    private static Matrix4x4.Accessible aabbToMatrix(final AABB aabb) {
        final Vector3.Accessible minimum = aabb.getMinimum();
        final Vector3.Accessible maximum = aabb.getMaximum();

        final double halfExtentX = (maximum.x() - minimum.x()) * 0.5;
        final double halfExtentY = (maximum.y() - minimum.y()) * 0.5;
        final double halfExtentZ = (maximum.z() - minimum.z()) * 0.5;

        final double centerX = (minimum.x() + maximum.x()) * 0.5;
        final double centerY = (minimum.y() + maximum.y()) * 0.5;
        final double centerZ = (minimum.z() + maximum.z()) * 0.5;

        return Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw.create(
                halfExtentX, 0.0, 0.0, 0.0,
                0.0, halfExtentY, 0.0, 0.0,
                0.0, 0.0, halfExtentZ, 0.0,
                centerX, centerY, centerZ, 1.0
        );
    }

}
