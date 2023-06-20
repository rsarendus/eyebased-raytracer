package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.io;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.demo.io.Format;
import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector4;

import java.nio.ByteBuffer;

public class VertexAttributeLoader2 extends FormatBasedVertexAttributeLoader<Vector2.Accessible> {

    private static final Vector4.Factory<Vector2.Accessible> FACTORY = (x, y, z, w) -> Factories.FACTORY_CONST_VECTOR2_xy.create(x, y);

    public VertexAttributeLoader2(final Format format) {
        super(format);
    }

    @Override
    public Vector2.Accessible loadAttribute(final ByteBuffer source, final int offset) {
        return format.load(source, offset, FACTORY);
    }

}
