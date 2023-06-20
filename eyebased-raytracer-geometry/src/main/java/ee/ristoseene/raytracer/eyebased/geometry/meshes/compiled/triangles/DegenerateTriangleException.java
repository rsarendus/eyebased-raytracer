package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles;

public class DegenerateTriangleException extends IllegalStateException {

    public DegenerateTriangleException() {}

    public DegenerateTriangleException(final String message) {
        super(message);
    }

    public DegenerateTriangleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DegenerateTriangleException(final Throwable cause) {
        super(cause);
    }

}
