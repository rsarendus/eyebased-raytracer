package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.rasterization.helpers.PixelLocationMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.function.Consumer;

public class RasterSpliteratorTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void spliteratorShouldNotAllowWidthLessThanOne(int width) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterSpliterator(width, 1)
        );
        Assertions.assertEquals("Invalid size: " + width + " x 1", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void spliteratorShouldNotAllowHeightLessThanOne(int height) {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RasterSpliterator(1, height)
        );
        Assertions.assertEquals("Invalid size: 1 x " + height, exception.getMessage());
    }

    @Test
    public void spliteratorShouldAllowWidthAndHeightAsOne() {
        Consumer<PixelLocation> pixelLocationConsumer = createPixelLocationConsumerMock();

        RasterSpliterator spliterator = new RasterSpliterator(1, 1);

        Assertions.assertTrue(spliterator.tryAdvance(pixelLocationConsumer));
        Assertions.assertFalse(spliterator.tryAdvance(pixelLocationConsumer));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(0, 0)));
        Mockito.verifyNoMoreInteractions(pixelLocationConsumer);
    }

    @Test
    public void tryAdvanceShouldProducePixelLocationForEachPixelInRaster() {
        Consumer<PixelLocation> pixelLocationConsumer = createPixelLocationConsumerMock();

        RasterSpliterator spliterator = new RasterSpliterator(3, 2);

        for (int i = 6; i > 0; --i) {
            Assertions.assertEquals(i, spliterator.estimateSize());
            Assertions.assertTrue(spliterator.tryAdvance(pixelLocationConsumer));
        }

        Assertions.assertEquals(0, spliterator.estimateSize());
        Assertions.assertFalse(spliterator.tryAdvance(pixelLocationConsumer));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(0, 0)));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(0, 1)));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(1, 0)));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(1, 1)));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(2, 0)));
        Mockito.verify(pixelLocationConsumer, Mockito.times(1)).accept(Mockito.argThat(new PixelLocationMatcher(2, 1)));
        Mockito.verifyNoMoreInteractions(pixelLocationConsumer);
    }

    @Test
    public void trySplitShouldSplitItselfToAsManySpliteratorsAsThereArePixelsInRaster() {
        RasterSpliterator spliterator = new RasterSpliterator(3, 2);

        for (int i = 1; i < 6; ++i) {
            Assertions.assertEquals(6 / i, spliterator.estimateSize());
            Assertions.assertSame(spliterator, spliterator.trySplit());
        }

        Assertions.assertEquals(1, spliterator.estimateSize());
        Assertions.assertNull(spliterator.trySplit());
    }

    @SuppressWarnings("unchecked")
    private static Consumer<PixelLocation> createPixelLocationConsumerMock() {
        return (Consumer<PixelLocation>) Mockito.mock(Consumer.class);
    }

}
