package ee.ristoseene.raytracer.eyebased.core.raytracing.group;

public class EmptyGroupException extends IllegalStateException {

    public EmptyGroupException() {}

    public EmptyGroupException(final String message) {
        super(message);
    }

    public EmptyGroupException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmptyGroupException(final Throwable cause) {
        super(cause);
    }

}
