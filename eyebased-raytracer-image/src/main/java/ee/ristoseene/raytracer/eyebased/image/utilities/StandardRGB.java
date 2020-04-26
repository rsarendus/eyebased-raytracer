package ee.ristoseene.raytracer.eyebased.image.utilities;

import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.Vector4;

/**
 * Utility class for conversions between linear and sRGB color spaces.
 *
 * Linear to sRGB:
 * | Condition                       | Value                                 |
 * | ------------------------------- | ------------------------------------- |
 * | 0.0 <= L <= 0.00313066844250063 | S = L * 12.92                         |
 * | 0.00313066844250063 < L <= 1.0  | S = (1.055 * L) ^ (1.0 / 2.4) − 0.055 |
 *
 * sRGB to Linear:
 * | Condition                     | Value                           |
 * | ----------------------------- | ------------------------------- |
 * | 0 <= S <= 0.0404482362771082  | L = S / 12.92                   |
 * | 0.0404482362771082 < S <= 1.0 | L = ((S + 0.055) / 1.055) ^ 2.4 |
 */
public final class StandardRGB {

    public static double linearToSRGB(final double linearComponent) {
        if (linearComponent > 0.00313066844250063) {
            return 1.055 * Math.pow(linearComponent, 1.0 / 2.4) - 0.055;
        } else {
            return linearComponent * 12.92;
        }
    }

    public static double sRGBToLinear(final double sRGBComponent) {
        if (sRGBComponent > 0.0404482362771082) {
            return Math.pow((sRGBComponent + 0.055) / 1.055, 2.4);
        } else {
            return sRGBComponent / 12.92;
        }
    }

    public static <R> R linearToSRGB(final double lR, final double lG, final double lB, final Vector3.Factory<R> resultFactory) {
        return resultFactory.create(linearToSRGB(lR), linearToSRGB(lG), linearToSRGB(lB));
    }

    public static <R> R linearToSRGB(final Vector3.Accessible linearRGB, final Vector3.Factory<R> resultFactory) {
        return linearToSRGB(linearRGB.x(), linearRGB.y(), linearRGB.z(), resultFactory);
    }

    public static void linearToSRGB(final Vector3.Consumer resultConsumer, final double lR, final double lG, final double lB) {
        resultConsumer.xyz(linearToSRGB(lR), linearToSRGB(lG), linearToSRGB(lB));
    }

    public static void linearToSRGB(final Vector3.Consumer resultConsumer, final Vector3.Accessible linearRGB) {
        linearToSRGB(resultConsumer, linearRGB.x(), linearRGB.y(), linearRGB.z());
    }

    public static <R> R sRGBToLinear(final double sR, final double sG, final double sB, final Vector3.Factory<R> resultFactory) {
        return resultFactory.create(sRGBToLinear(sR), sRGBToLinear(sG), sRGBToLinear(sB));
    }

    public static <R> R sRGBToLinear(final Vector3.Accessible sRGB, final Vector3.Factory<R> resultFactory) {
        return sRGBToLinear(sRGB.x(), sRGB.y(), sRGB.z(), resultFactory);
    }

    public static void sRGBToLinear(final Vector3.Consumer resultConsumer, final double sR, final double sG, final double sB) {
        resultConsumer.xyz(sRGBToLinear(sR), sRGBToLinear(sG), sRGBToLinear(sB));
    }

    public static void sRGBToLinear(final Vector3.Consumer resultConsumer, final Vector3.Accessible sRGB) {
        sRGBToLinear(resultConsumer, sRGB.x(), sRGB.y(), sRGB.z());
    }

    public static <R> R linearToSRGB(final double lR, final double lG, final double lB, final double a, final Vector4.Factory<R> resultFactory) {
        return resultFactory.create(linearToSRGB(lR), linearToSRGB(lG), linearToSRGB(lB), a);
    }

    public static <R> R linearToSRGB(final Vector4.Accessible linearRGBA, final Vector4.Factory<R> resultFactory) {
        return linearToSRGB(linearRGBA.x(), linearRGBA.y(), linearRGBA.z(), linearRGBA.w(), resultFactory);
    }

    public static void linearToSRGB(final Vector4.Consumer resultConsumer, final double lR, final double lG, final double lB, final double a) {
        resultConsumer.xyzw(linearToSRGB(lR), linearToSRGB(lG), linearToSRGB(lB), a);
    }

    public static void linearToSRGB(final Vector4.Consumer resultConsumer, final Vector4.Accessible linearRGBA) {
        linearToSRGB(resultConsumer, linearRGBA.x(), linearRGBA.y(), linearRGBA.z(), linearRGBA.w());
    }

    public static <R> R sRGBToLinear(final double sR, final double sG, final double sB, final double a, final Vector4.Factory<R> resultFactory) {
        return resultFactory.create(sRGBToLinear(sR), sRGBToLinear(sG), sRGBToLinear(sB), a);
    }

    public static <R> R sRGBToLinear(final Vector4.Accessible sRGB_A, final Vector4.Factory<R> resultFactory) {
        return sRGBToLinear(sRGB_A.x(), sRGB_A.y(), sRGB_A.z(), sRGB_A.w(), resultFactory);
    }

    public static void sRGBToLinear(final Vector4.Consumer resultConsumer, final double sR, final double sG, final double sB, final double a) {
        resultConsumer.xyzw(sRGBToLinear(sR), sRGBToLinear(sG), sRGBToLinear(sB), a);
    }

    public static void sRGBToLinear(final Vector4.Consumer resultConsumer, final Vector4.Accessible sRGB_A) {
        sRGBToLinear(resultConsumer, sRGB_A.x(), sRGB_A.y(), sRGB_A.z(), sRGB_A.w());
    }

    private StandardRGB() {}

}
