package ee.ristoseene.raytracer.eyebased.rasterization;

import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public final class RasterSpliterator<T> implements Spliterator<T> {

    private static final int CHARACTERISTICS = DISTINCT | IMMUTABLE | NONNULL;

    private final AtomicLong currentIndex = new AtomicLong(0L);
    private final AtomicLong currentDivision = new AtomicLong(1L);

    private final BlockResolver<T> rasterBlockResolver;

    public RasterSpliterator(final BlockResolver<T> rasterBlockResolver) {
        this.rasterBlockResolver = Objects.requireNonNull(rasterBlockResolver, "Raster block resolver not provided");
    }

    @Override
    public int characteristics() {
        return CHARACTERISTICS;
    }

    @Override
    public long estimateSize() {
        return Math.max(rasterBlockResolver.blockCount() - currentIndex.get(), 0L) / currentDivision.get();
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> consumer) {
        final long index = currentIndex.getAndIncrement();

        if (index < rasterBlockResolver.blockCount()) {
            consumer.accept(rasterBlockResolver.resolveBlock(index));
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return (currentDivision.incrementAndGet() > rasterBlockResolver.blockCount()) ? null : this;
    }

    public interface BlockResolver<T> {
        T resolveBlock(long blockIndex);
        long blockCount();
    }

}
