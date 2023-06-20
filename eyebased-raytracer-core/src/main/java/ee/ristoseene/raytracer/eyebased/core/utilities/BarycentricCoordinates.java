package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.vecmath.Vector2;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

public final class BarycentricCoordinates {

    /**
     * Calculates barycentric coordinates based on a point P and a triangle ABC.
     *
     * @param p point P
     * @param a triangle's vertex A
     * @param b triangle's vertex B
     * @param c triangle's vertex C
     * @param resultFactory factory for packaging the resulting barycentric coordinates U, V and W into a single object
     * @param <R> return type of the {@code resultFactory}
     *
     * @return resulting barycentric coordinates packaged into an object of type {@code <R>}
     */
    public static <R> R calculateCoordinatesFromPointInTriangle(
            final Vector3.Accessible p, final Vector3.Accessible a, final Vector3.Accessible b, final Vector3.Accessible c,
            final Vector3.Factory<R> resultFactory
    ) {
        final double aX = a.x();
        final double aY = a.y();
        final double aZ = a.z();

        final double abX = b.x() - aX;
        final double abY = b.y() - aY;
        final double abZ = b.z() - aZ;

        final double acX = c.x() - aX;
        final double acY = c.y() - aY;
        final double acZ = c.z() - aZ;

        final double apX = p.x() - aX;
        final double apY = p.y() - aY;
        final double apZ = p.z() - aZ;

        final double dotAbAb = abX * abX + abY * abY + abZ * abZ;
        final double dotAbAc = abX * acX + abY * acY + abZ * acZ;
        final double dotAcAc = acX * acX + acY * acY + acZ * acZ;
        final double dotApAb = apX * abX + apY * abY + apZ * abZ;
        final double dotApAc = apX * acX + apY * acY + apZ * acZ;

        final double invDenom = 1.0 / (dotAbAb * dotAcAc - dotAbAc * dotAbAc);

        final double v = (dotAcAc * dotApAb - dotAbAc * dotApAc) * invDenom;
        final double w = (dotAbAb * dotApAc - dotAbAc * dotApAb) * invDenom;
        final double u = 1.0f - v - w;

        return resultFactory.create(u, v, w);
    }

    /**
     * Calculates barycentric coordinates based on a point P and a triangle ABC.
     *
     * @param resultConsumer consumer for the resulting barycentric coordinates U, V and W
     * @param p point P
     * @param a triangle's vertex A
     * @param b triangle's vertex B
     * @param c triangle's vertex C
     */
    public static void calculateCoordinatesFromPointInTriangle(
            final Vector3.Consumer resultConsumer,
            final Vector3.Accessible p, final Vector3.Accessible a, final Vector3.Accessible b, final Vector3.Accessible c
    ) {
        final double aX = a.x();
        final double aY = a.y();
        final double aZ = a.z();

        final double abX = b.x() - aX;
        final double abY = b.y() - aY;
        final double abZ = b.z() - aZ;

        final double acX = c.x() - aX;
        final double acY = c.y() - aY;
        final double acZ = c.z() - aZ;

        final double apX = p.x() - aX;
        final double apY = p.y() - aY;
        final double apZ = p.z() - aZ;

        final double dotAbAb = abX * abX + abY * abY + abZ * abZ;
        final double dotAbAc = abX * acX + abY * acY + abZ * acZ;
        final double dotAcAc = acX * acX + acY * acY + acZ * acZ;
        final double dotApAb = apX * abX + apY * abY + apZ * abZ;
        final double dotApAc = apX * acX + apY * acY + apZ * acZ;

        final double invDenom = 1.0 / (dotAbAb * dotAcAc - dotAbAc * dotAbAc);

        final double v = (dotAcAc * dotApAb - dotAbAc * dotApAc) * invDenom;
        final double w = (dotAbAb * dotApAc - dotAbAc * dotApAb) * invDenom;
        final double u = 1.0f - v - w;

        resultConsumer.xyz(u, v, w);
    }

    public static <R> R resolve(
            final Vector2.Accessible a, final Vector2.Accessible b, final Vector2.Accessible c, final Vector3.Accessible coordinates,
            final Vector2.Factory<R> resultFactory
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        return resultFactory.create(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w
        );
    }

    public static void resolve(
            final Vector2.Consumer resultConsumer,
            final Vector2.Accessible a, final Vector2.Accessible b, final Vector2.Accessible c, final Vector3.Accessible coordinates
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        resultConsumer.xy(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w
        );
    }

    public static <R> R resolve(
            final Vector3.Accessible a, final Vector3.Accessible b, final Vector3.Accessible c, final Vector3.Accessible coordinates,
            final Vector3.Factory<R> resultFactory
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        return resultFactory.create(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w,
                a.z() * u + b.z() * v + c.z() * w
        );
    }

    public static void resolve(
            final Vector3.Consumer resultConsumer,
            final Vector3.Accessible a, final Vector3.Accessible b, final Vector3.Accessible c, final Vector3.Accessible coordinates
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        resultConsumer.xyz(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w,
                a.z() * u + b.z() * v + c.z() * w
        );
    }

    public static <R> R resolve(
            final Vector4.Accessible a, final Vector4.Accessible b, final Vector4.Accessible c, final Vector3.Accessible coordinates,
            final Vector4.Factory<R> resultFactory
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        return resultFactory.create(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w,
                a.z() * u + b.z() * v + c.z() * w,
                a.w() * u + b.w() * v + c.w() * w
        );
    }

    public static void resolve(
            final Vector4.Consumer resultConsumer,
            final Vector4.Accessible a, final Vector4.Accessible b, final Vector4.Accessible c, final Vector3.Accessible coordinates
    ) {
        final double u = coordinates.x();
        final double v = coordinates.y();
        final double w = coordinates.z();

        resultConsumer.xyzw(
                a.x() * u + b.x() * v + c.x() * w,
                a.y() * u + b.y() * v + c.y() * w,
                a.z() * u + b.z() * v + c.z() * w,
                a.w() * u + b.w() * v + c.w() * w
        );
    }

    private BarycentricCoordinates() {}

}
