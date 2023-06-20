package ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.io;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.demo.io.Format;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

import java.nio.ByteBuffer;

public class VertexAttributeLoader3 extends FormatBasedVertexAttributeLoader<Vector3.Accessible> {

    private static final Vector4.Factory<Vector3.Accessible> FACTORY = (x, y, z, w) -> Factories.FACTORY_CONST_VECTOR3_xyz.create(x, y, z);

    public VertexAttributeLoader3(final Format format) {
        super(format);
    }

    @Override
    public Vector3.Accessible loadAttribute(final ByteBuffer source, final int offset) {
        return format.load(source, offset, FACTORY);
    }

}
