package ee.ristoseene.raytracer.eyebased.demo.io;

public final class NormalizedInteger {

    public static float normalizedUInt8ToFloat(final byte uint8) {
        return (float) (uint8 & 0xff) / (float) (0xff);
    }

    public static double normalizedUInt8ToDouble(final byte uint8) {
        return (double) (uint8 & 0xffL) / (double) (0xffL);
    }

    public static float normalizedSInt8ToFloat(final byte sint8) {
        if (sint8 >= 0) {
            return (float) (sint8) / (float) (0x7f);
        } else {
            return (float) Math.max(sint8, -0x7f) / (float) (0x7fF);
        }
    }

    public static double normalizedSInt8ToDouble(final byte sint8) {
        if (sint8 >= 0) {
            return (double) (sint8) / (double) (0x7fL);
        } else {
            return (double) Math.max(sint8, -0x7fL) / (double) (0x7fL);
        }
    }

    public static float normalizedUInt16ToFloat(final short uint16) {
        return (float) (uint16 & 0xffff) / (float) (0xffff);
    }

    public static double normalizedUInt16ToDouble(final short uint16) {
        return (double) (uint16 & 0xffffL) / (double) (0xffffL);
    }

    public static float normalizedSInt16ToFloat(final short sint16) {
        if (sint16 >= 0) {
            return (float) (sint16) / (float) (0x7fff);
        } else {
            return (float) Math.max(sint16, -0x7fff) / (float) (0x7fff);
        }
    }

    public static double normalizedSInt16ToDouble(final short sint16) {
        if (sint16 >= 0) {
            return (double) (sint16) / (double) (0x7fffL);
        } else {
            return (double) Math.max(sint16, -0x7fffL) / (double) (0x7fffL);
        }
    }

    public static float normalizedUInt32ToFloat(final int uint32) {
        return (float) (uint32 & 0xffffffffL) / (float) (0xffffffff);
    }

    public static double normalizedUInt32ToDouble(final int uint32) {
        return (double) (uint32 & 0xffffffffL) / (double) (0xffffffffL);
    }

    public static float normalizedSInt32ToFloat(final int sint32) {
        if (sint32 >= 0) {
            return (float) (sint32) / (float) (0x7fffffff);
        } else {
            return (float) Math.max(sint32, -0x7fffffff) / (float) (0x7fffffff);
        }
    }

    public static double normalizedSInt32ToDouble(final int sint32) {
        if (sint32 >= 0) {
            return (double) (sint32) / (double) (0x7fffffffL);
        } else {
            return (double) Math.max(sint32, -0x7fffffffL) / (double) (0x7fffffffL);
        }
    }

    public static float normalizedPackedUInt2ToFloat(final int packed2) {
        return (float) (packed2 & 0b11) / (float) (0b11);
    }

    public static double normalizedPackedUInt2ToDouble(final int packed2) {
        return (double) (packed2 & 0b11L) / (double) (0b11L);
    }

    public static float normalizedPackedSInt2ToFloat(final int packed2) {
        if ((packed2 & 0b10) == 0) {
            return (float) (packed2 & 0b01) / (float) (0b01);
        } else {
            return (float) Math.max(packed2 | (~0b01), -0b01) / (float) (0b01);
        }
    }

    public static double normalizedPackedSInt2ToDouble(final int packed2) {
        if ((packed2 & 0b10) == 0) {
            return (double) (packed2 & 0b01L) / (double) (0b01L);
        } else {
            return (double) Math.max(packed2 | (~0b01L), -0b01L) / (double) (0b01L);
        }
    }

    public static float normalizedPackedUInt10ToFloat(final int packed10) {
        return (float) (packed10 & 0b1111111111) / (float) (0b1111111111);
    }

    public static double normalizedPackedUInt10ToDouble(final int packed10) {
        return (double) (packed10 & 0b1111111111L) / (double) (0b1111111111L);
    }

    public static float normalizedPackedSInt10ToFloat(final int packed10) {
        if ((packed10 & 0b1000000000) == 0) {
            return (float) (packed10 & 0b0111111111) / (float) (0b0111111111);
        } else {
            return (float) Math.max(packed10 | (~0b0111111111), -0b0111111111) / (float) (0b0111111111);
        }
    }

    public static double normalizedPackedSInt10ToDouble(final int packed10) {
        if ((packed10 & 0b1000000000) == 0) {
            return (double) (packed10 & 0b0111111111L) / (double) (0b0111111111L);
        } else {
            return (double) Math.max(packed10 | (~0b0111111111L), -0b0111111111L) / (double) (0b0111111111L);
        }
    }

    private NormalizedInteger() {}

}
