package ee.ristoseene.raytracer.eyebased.demo.io;

import ee.ristoseene.vecmath.Vector4;

import java.nio.ByteBuffer;

public enum Format {

    R8G8_UNORM(Byte.BYTES * 2) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    0.0,
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    0.0,
                    1.0
            );
        }

    },

    R8G8_SNORM(Byte.BYTES * 2) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    0.0,
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    0.0,
                    1.0
            );
        }

    },

    R16G16_UNORM(Short.BYTES * 2) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    0.0,
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    0.0,
                    1.0
            );
        }

    },

    R16G16_SNORM(Short.BYTES * 2) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    0.0,
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    0.0,
                    1.0
            );
        }

    },

    R32G32_SFLOAT(Float.BYTES * 2) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    0.0,
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    0.0,
                    1.0
            );
        }

    },

    R8G8B8_UNORM(Byte.BYTES * 3) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    1.0
            );
        }

    },

    R8G8B8_SNORM(Byte.BYTES * 3) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    1.0
            );
        }

    },

    R16G16B16_UNORM(Short.BYTES * 3) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    1.0
            );
        }

    },

    R16G16B16_SNORM(Short.BYTES * 3) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    1.0
            );
        }

    },

    R32G32B32_SFLOAT(Float.BYTES * 3) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES),
                    1.0
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES),
                    1.0
            );
        }

    },

    R8G8B8A8_UNORM(Byte.BYTES * 4) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES))
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedUInt8ToDouble(source.get(offset += Byte.BYTES))
            );
        }

    },

    R8G8B8A8_SNORM(Byte.BYTES * 4) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES))
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES)),
                    NormalizedInteger.normalizedSInt8ToDouble(source.get(offset += Byte.BYTES))
            );
        }

    },

    A2R10G10B10_UNORM_PACK32(Integer.BYTES) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            final int packed32 = source.getInt(offset);
            return resultFactory.create(
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedUInt2ToDouble(packed32 >> 30)
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            final int packed32 = source.getInt(offset);
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedUInt2ToDouble(packed32 >> 30)
            );
        }

    },

    A2R10G10B10_SNORM_PACK32(Integer.BYTES) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            final int packed32 = source.getInt(offset);
            return resultFactory.create(
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedSInt2ToDouble(packed32 >> 30)
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            final int packed32 = source.getInt(offset);
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedSInt2ToDouble(packed32 >> 30)
            );
        }

    },

    A2B10G10R10_UNORM_PACK32(Integer.BYTES) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            final int packed32 = source.getInt(offset);
            return resultFactory.create(
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedUInt2ToDouble(packed32 >> 30)
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            final int packed32 = source.getInt(offset);
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedUInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedUInt2ToDouble(packed32 >> 30)
            );
        }

    },

    A2B10G10R10_SNORM_PACK32(Integer.BYTES) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            final int packed32 = source.getInt(offset);
            return resultFactory.create(
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedSInt2ToDouble(packed32 >> 30)
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            final int packed32 = source.getInt(offset);
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 10),
                    NormalizedInteger.normalizedPackedSInt10ToDouble(packed32 >> 20),
                    NormalizedInteger.normalizedPackedSInt2ToDouble(packed32 >> 30)
            );
        }

    },

    R16G16B16A16_UNORM(Short.BYTES * 4) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES))
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedUInt16ToDouble(source.getShort(offset += Short.BYTES))
            );
        }

    },

    R16G16B16A16_SNORM(Short.BYTES * 4) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES))
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES)),
                    NormalizedInteger.normalizedSInt16ToDouble(source.getShort(offset += Short.BYTES))
            );
        }

    },

    R32G32B32A32_SFLOAT(Float.BYTES * 4) {

        @Override
        public <R> R load(final ByteBuffer source, int offset, final Vector4.Factory<R> resultFactory) {
            return resultFactory.create(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES)
            );
        }

        @Override
        public void load(final Vector4.Consumer resultConsumer, final ByteBuffer source, int offset) {
            resultConsumer.xyzw(
                    source.getFloat(offset),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES),
                    source.getFloat(offset += Float.BYTES)
            );
        }

    };

    private final int sizeInBytes;

    Format(final int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public abstract <R> R load(ByteBuffer source, int offset, Vector4.Factory<R> resultFactory);
    public abstract void load(Vector4.Consumer resultConsumer, ByteBuffer source, int offset);

    public int getSizeInBytes() {
        return sizeInBytes;
    }

}
