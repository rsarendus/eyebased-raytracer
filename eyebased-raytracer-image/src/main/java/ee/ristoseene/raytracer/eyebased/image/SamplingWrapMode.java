package ee.ristoseene.raytracer.eyebased.image;

public enum SamplingWrapMode {

    CLAMP_TO_EDGE {
        @Override
        public int wrap(final long value, final int size) {
            long result = size - 1L;

            if (result > value) result = value;
            if (result < 0L) result = 0L;

            return (int) result;
        }
    },

    REPEAT {
        @Override
        public int wrap(final long value, final int size) {
            return (int) ((value % size + size) % size);
        }
    };

    public abstract int wrap(long value, int size);

}
