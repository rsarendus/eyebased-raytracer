package ee.ristoseene.raytracer.eyebased.image;

import ee.ristoseene.vecmath.Vector4;

public interface Image2D {

    int getWidth();
    int getHeight();

    interface Readable extends Image2D {

        void readPixel(Vector4.Consumer destinationRGBA, int x, int y);

    }

    interface Writable extends Image2D {

        void writePixel(Vector4.Accessible sourceRGBA, int x, int y);

    }

}
