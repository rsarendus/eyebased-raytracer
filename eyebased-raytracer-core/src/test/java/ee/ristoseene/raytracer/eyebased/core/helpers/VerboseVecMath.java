package ee.ristoseene.raytracer.eyebased.core.helpers;

import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;
import org.mockito.Mockito;

public interface VerboseVecMath {

    static <T extends Vector2.Accessible> String toString(final T vector) {
        return String.format(
                "(%f, %f)",
                vector.x(),
                vector.y()
        );
    }

    static <T extends Vector3.Accessible> String toString(final T vector) {
        return String.format(
                "(%f, %f, %f)",
                vector.x(),
                vector.y(),
                vector.z()
        );
    }

    static <T extends Vector4.Accessible> String toString(final T vector) {
        return String.format(
                "(%f, %f, %f, %f)",
                vector.x(),
                vector.y(),
                vector.z(),
                vector.w()
        );
    }

    static <T extends Vector2.Accessible> T verbose(final T vector) {
        final T verboseVector = Mockito.spy(vector);
        Mockito.doAnswer(invocationOnMock -> toString(vector)).when(verboseVector).toString();
        return verboseVector;
    }

    static <T extends Vector3.Accessible> T verbose(final T vector) {
        final T verboseVector = Mockito.spy(vector);
        Mockito.doAnswer(invocationOnMock -> toString(vector)).when(verboseVector).toString();
        return verboseVector;
    }

    static <T extends Vector4.Accessible> T verbose(final T vector) {
        final T verboseVector = Mockito.spy(vector);
        Mockito.doAnswer(invocationOnMock -> toString(vector)).when(verboseVector).toString();
        return verboseVector;
    }

}
