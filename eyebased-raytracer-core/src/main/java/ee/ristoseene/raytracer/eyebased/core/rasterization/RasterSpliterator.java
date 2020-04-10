package ee.ristoseene.raytracer.eyebased.core.rasterization;

import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public final class RasterSpliterator implements Spliterator<PixelLocation> {

    private static final int CHARACTERISTICS = DISTINCT | IMMUTABLE | NONNULL;

    private final int pixelsPerScanLine;
    private final long totalPixelCount;

    private final AtomicLong currentIndex;
    private final AtomicLong currentDivision;

    public RasterSpliterator(final int width, final int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Invalid size: " + width + " x " + height);
        }

        this.pixelsPerScanLine = width;
        this.totalPixelCount = (long) width * (long) height;

        this.currentIndex = new AtomicLong(0L);
        this.currentDivision = new AtomicLong(1L);
    }

    @Override
    public int characteristics() {
        return CHARACTERISTICS;
    }

    @Override
    public long estimateSize() {
        return Math.max(totalPixelCount - currentIndex.get(), 0L) / currentDivision.get();
    }

    @Override
    public boolean tryAdvance(final Consumer<? super PixelLocation> consumer) {
        final long index = currentIndex.getAndIncrement();

        if (index < totalPixelCount) {
            consumer.accept(new PixelLocation(
                    (int) (index % pixelsPerScanLine),
                    (int) (index / pixelsPerScanLine)
            ));
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<PixelLocation> trySplit() {
        return (currentDivision.incrementAndGet() > totalPixelCount) ? null : this;
    }

}
