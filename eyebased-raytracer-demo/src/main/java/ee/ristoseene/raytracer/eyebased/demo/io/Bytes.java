package ee.ristoseene.raytracer.eyebased.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class Bytes {

    public static byte[] loadFromResource(final String path) {
        try (InputStream in = Objects
                .requireNonNull(Bytes.class.getResourceAsStream(path), () -> "Resource not found: " + path)
        ) {
            return in.readAllBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load resource: " + path);
        }
    }

    public static byte[] loadFromResourceAndInflate(final String path) {
        final byte[] deflatedBytes = loadFromResource(path);
        final Inflater inflater = new Inflater(true);

        try {
            inflater.setInput(deflatedBytes);

            byte[] inflatedBytes = new byte[Math.max(2 * deflatedBytes.length, 1024)];
            int inflatedLength = 0;

            while (true) {
                if (inflater.needsDictionary() || inflater.needsInput()) {
                    throw new IllegalStateException("Un-inflatable input data");
                }

                inflatedLength += inflater.inflate(inflatedBytes, inflatedLength, inflatedBytes.length - inflatedLength);

                if (inflater.finished()) {
                    break;
                }

                inflatedBytes = Arrays.copyOf(inflatedBytes, inflatedBytes.length * 2);
            }

            return Arrays.copyOf(inflatedBytes, inflatedLength);
        } catch (DataFormatException e) {
            throw new IllegalStateException("Failed to inflate data", e);
        } finally {
            inflater.end();
        }
    }

    private Bytes() {}

}
