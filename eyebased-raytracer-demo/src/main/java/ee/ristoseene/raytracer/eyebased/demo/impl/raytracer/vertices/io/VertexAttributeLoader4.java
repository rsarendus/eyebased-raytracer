package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.io;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.demo.io.Format;
import ee.ristoseene.vecmath.Vector4;

import java.nio.ByteBuffer;

public class VertexAttributeLoader4 extends FormatBasedVertexAttributeLoader<Vector4.Accessible> {

    private static final Vector4.Factory<Vector4.Accessible> FACTORY = Factories.FACTORY_CONST_VECTOR4_xyzw;

    public VertexAttributeLoader4(final Format format) {
        super(format);
    }

    @Override
    public Vector4.Accessible loadAttribute(final ByteBuffer source, final int offset) {
        return format.load(source, offset, FACTORY);
    }

}
