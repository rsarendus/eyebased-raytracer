package ee.ristoseene.raytracer.eyebased.rasterization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Spliterator;
import java.util.function.Consumer;

public class RasterSpliteratorTest {

    @Test
    public void rasterSpliteratorShouldNotAllowMissingRasterBlockResolver() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new RasterSpliterator<>(null)
        );

        Assertions.assertEquals("Raster block resolver not provided", exception.getMessage());
    }

    @Test
    public void rasterSpliteratorShouldAdvertiseDistinctAndImmutableAndNonNullCharacteristics() {
        Assertions.assertEquals(
                Spliterator.DISTINCT | Spliterator.IMMUTABLE | Spliterator.NONNULL,
                new RasterSpliterator<>(createRasterBlockResolverMock()).characteristics()
        );
    }

    @Test
    public void tryAdvanceShouldProducePixelLocationForEachPixelInRaster() {
        Consumer<Object> rasterBlockConsumer = createRasterBlockConsumerMock();
        RasterSpliterator.BlockResolver<Object> rasterBlockResolver = createRasterBlockResolverMock();
        RasterSpliterator<Object> rasterSpliterator = new RasterSpliterator<>(rasterBlockResolver);
        Mockito.doReturn(3L).when(rasterBlockResolver).blockCount();

        Object object0 = Mockito.mock(Object.class);
        Mockito.doReturn(object0).when(rasterBlockResolver).resolveBlock(0L);
        Object object1 = Mockito.mock(Object.class);
        Mockito.doReturn(object1).when(rasterBlockResolver).resolveBlock(1L);
        Object object2 = Mockito.mock(Object.class);
        Mockito.doReturn(object2).when(rasterBlockResolver).resolveBlock(2L);

        for (int i = 3; i > 0; --i) {
            Assertions.assertEquals(i, rasterSpliterator.estimateSize());
            Assertions.assertTrue(rasterSpliterator.tryAdvance(rasterBlockConsumer));
        }

        Assertions.assertEquals(0, rasterSpliterator.estimateSize());
        Assertions.assertFalse(rasterSpliterator.tryAdvance(rasterBlockConsumer));
        Mockito.verify(rasterBlockResolver, Mockito.times(8)).blockCount();
        Mockito.verify(rasterBlockResolver, Mockito.times(1)).resolveBlock(0L);
        Mockito.verify(rasterBlockResolver, Mockito.times(1)).resolveBlock(1L);
        Mockito.verify(rasterBlockResolver, Mockito.times(1)).resolveBlock(2L);
        Mockito.verify(rasterBlockConsumer, Mockito.times(1)).accept(object0);
        Mockito.verify(rasterBlockConsumer, Mockito.times(1)).accept(object1);
        Mockito.verify(rasterBlockConsumer, Mockito.times(1)).accept(object2);
        Mockito.verifyNoMoreInteractions(rasterBlockResolver, rasterBlockConsumer);
    }

    @Test
    public void trySplitShouldSplitItselfToAsManySpliteratorsAsThereAreBlocksAdvertised() {
        RasterSpliterator.BlockResolver<Object> rasterBlockResolver = createRasterBlockResolverMock();
        RasterSpliterator<Object> rasterSpliterator = new RasterSpliterator<>(rasterBlockResolver);
        Mockito.doReturn(7L).when(rasterBlockResolver).blockCount();

        for (int i = 1; i < 7; ++i) {
            Assertions.assertEquals(7 / i, rasterSpliterator.estimateSize());
            Assertions.assertSame(rasterSpliterator, rasterSpliterator.trySplit());
        }

        Assertions.assertEquals(1, rasterSpliterator.estimateSize());
        Assertions.assertNull(rasterSpliterator.trySplit());
    }

    @SuppressWarnings("unchecked")
    private static RasterSpliterator.BlockResolver<Object> createRasterBlockResolverMock() {
        return (RasterSpliterator.BlockResolver<Object>) Mockito.mock(RasterSpliterator.BlockResolver.class);
    }

    @SuppressWarnings("unchecked")
    private static Consumer<Object> createRasterBlockConsumerMock() {
        return (Consumer<Object>) Mockito.mock(Consumer.class);
    }

}
