package ee.ristoseene.raytracer.eyebased.image;

import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public interface Image2D {

    int getWidth();
    int getHeight();

    interface Readable extends Image2D {

        <R> R readPixel(int x, int y, Vector4.Factory<R> resultFactory);
        void readPixel(Vector4.Consumer resultConsumer, int x, int y);

    }

    interface Writable extends Image2D {

        void writePixel(int x, int y, double r, double g, double b, double a);

        default void writePixel(int x, int y, Vector3.Accessible rgb, double a) {
            writePixel(x, y, rgb.x(), rgb.y(), rgb.z(), a);
        }

        default void writePixel(int x, int y, Vector4.Accessible rgba) {
            writePixel(x, y, rgba.x(), rgba.y(), rgba.z(), rgba.w());
        }

        default void writePixel(int x, int y, Vector3.Accessible rgb) {
            writePixel(x, y, rgb.x(), rgb.y(), rgb.z(), 1.0);
        }

    }

}
