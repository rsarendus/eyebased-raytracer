package ee.ristoseene.raytracer.eyebased.core.image;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public interface Image2D {

    int getWidth();
    int getHeight();

    interface Readable extends Image2D {

        void readPixel(Vector4.Mutable destinationRGBA, int x, int y);

    }

    interface Writable extends Image2D {

        void writePixel(Vector4.Accessible sourceRGBA, int x, int y);

    }

}
