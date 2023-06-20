package ee.ristoseene.raytracer.eyebased.demo.wireframe.geometry;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.Transformable;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.vecmath.Matrix4x4;
import ee.ristoseene.vecmath.VecMath;

import java.util.Optional;

public interface TransformableWireframeRenderer<T extends Transformable> extends WireframeRenderer<T> {

    static Matrix4x4.Accessible getTransformationMatrix(final Matrix4x4.Accessible projection, final Transformable transformable) {
        final CompilableTransform parentTransform = transformable.getParentTransform();
        if (parentTransform != null) {
            final Matrix4x4.Accessible compiledTransform = parentTransform.compile(Optional.empty());
            return VecMath.multiply(projection, compiledTransform, Factories.FACTORY_CONST_MATRIX4x4_XYZTxyzw);
        } else {
            return projection;
        }
    }

    static CompiledTransform getTransform(final Transformable transformable) {
        final CompilableTransform parentTransform = transformable.getParentTransform();
        if (parentTransform != null) {
            return parentTransform.compile(Optional.empty());
        } else {
            return CompiledTransform.IDENTITY_TRANSFORM;
        }
    }

}
