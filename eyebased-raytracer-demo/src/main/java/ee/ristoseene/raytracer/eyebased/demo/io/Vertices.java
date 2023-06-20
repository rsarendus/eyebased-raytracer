package ee.ristoseene.raytracer.eyebased.demo.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public final class Vertices {

    private static final String DEFLATED_EXTENSION = ".deflate";

    public static ByteBuffer loadVertexBuffer(final String path, final ByteOrder endianness) {
        return ByteBuffer.wrap(loadDirectOrDeflated(path)).order(endianness).asReadOnlyBuffer();
    }

    public static int[] loadIndices8(final String path, final ByteOrder endianness) {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(loadDirectOrDeflated(path)).order(endianness);
        final int[] indices = new int[byteBuffer.capacity()];

        for (int i = 0; i < indices.length; ++i) {
            indices[i] = byteBuffer.get() & 0xff;
        }

        return indices;
    }

    public static int[] loadIndices16(final String path, final ByteOrder endianness) {
        final ShortBuffer shortBuffer = ByteBuffer.wrap(loadDirectOrDeflated(path)).order(endianness).asShortBuffer();
        final int[] indices = new int[shortBuffer.capacity()];

        for (int i = 0; i < indices.length; ++i) {
            indices[i] = shortBuffer.get() & 0xffff;
        }

        return indices;
    }

    public static int[] loadIndices32(final String path, final ByteOrder endianness) {
        final IntBuffer intBuffer = ByteBuffer.wrap(loadDirectOrDeflated(path)).order(endianness).asIntBuffer();
        final int[] indices = new int[intBuffer.capacity()];
        intBuffer.get(indices);
        return indices;
    }

    private static byte[] loadDirectOrDeflated(final String path) {
        if (path.endsWith(DEFLATED_EXTENSION)) {
            return Bytes.loadFromResourceAndInflate(path);
        } else {
            return Bytes.loadFromResource(path);
        }
    }

    private Vertices() {}

}
