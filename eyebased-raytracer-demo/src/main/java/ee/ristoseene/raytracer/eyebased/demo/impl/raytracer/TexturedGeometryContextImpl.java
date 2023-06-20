package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;

public class TexturedGeometryContextImpl extends SimpleGeometryContextImpl {

    private final Vector2.Accessible textureCoordinates;

    public TexturedGeometryContextImpl(
            final Vector3.Accessible position,
            final Vector3.Accessible surfaceNormal,
            final Vector2.Accessible textureCoordinates
    ) {
        super(position, surfaceNormal);
        this.textureCoordinates = textureCoordinates;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttributeValue(final TypedAttribute<T> key) {
        if (GeometryAttributes.TEXTURE_COORDINATES.equals(key)) {
            return (T) textureCoordinates;
        }
        return super.getAttributeValue(key);
    }

}
