package ee.ristoseene.raytracer.eyebased.core.image;

public enum SamplingWrapMode {

    CLAMP_TO_EDGE {
        @Override
        public int wrap(long value, int size) {
            final long maximum = (long) (size - 1);
            return (int) (value > 0L ? (value < maximum ? value : maximum) : 0L);
        }
    },

    REPEAT {
        @Override
        public int wrap(long value, int size) {
            return (int) ((value % size + size) % size);
        }
    };

    public abstract int wrap(long value, int size);

}
