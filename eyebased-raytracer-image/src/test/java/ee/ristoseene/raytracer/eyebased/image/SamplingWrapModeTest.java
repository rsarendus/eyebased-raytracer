package ee.ristoseene.raytracer.eyebased.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode.CLAMP_TO_EDGE;
import static ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode.REPEAT;

public class SamplingWrapModeTest {

    private static final int DEFAULT_SIZE = 3;


    @Test
    public void wrapWithValueBetweenLimitsForClampShouldReturnThatValue() {
        Assertions.assertEquals(
                1,
                CLAMP_TO_EDGE.wrap(1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithZeroForClampShouldReturnZero() {
        Assertions.assertEquals(
                0,
                CLAMP_TO_EDGE.wrap(0L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithNegativeForClampShouldReturnZero() {
        Assertions.assertEquals(
                0,
                CLAMP_TO_EDGE.wrap(-1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithOneBelowSizeForClampShouldReturnOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                CLAMP_TO_EDGE.wrap(DEFAULT_SIZE - 1, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithSizeForClampShouldReturnOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                CLAMP_TO_EDGE.wrap(DEFAULT_SIZE, DEFAULT_SIZE)
        );
    }


    @Test
    public void wrapWithValueBetweenLimitsForRepeatShouldReturnThatValue() {
        Assertions.assertEquals(
                1,
                REPEAT.wrap(1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithZeroForRepeatShouldReturnZero() {
        Assertions.assertEquals(
                0,
                REPEAT.wrap(0L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithOneBelowZeroForRepeatShouldWrapAroundToOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                REPEAT.wrap(-1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithSizeBelowZeroForRepeatShouldWrapAroundOnceToZero() {
        Assertions.assertEquals(
                0,
                REPEAT.wrap(-DEFAULT_SIZE, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithOneMoreThanSizeBelowZeroForRepeatShouldWrapAroundTwiceToOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                REPEAT.wrap(-DEFAULT_SIZE - 1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithOneBelowSizeForRepeatShouldReturnOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                REPEAT.wrap(DEFAULT_SIZE - 1, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithSizeForRepeatShouldWrapAroundToZero() {
        Assertions.assertEquals(
                0,
                REPEAT.wrap(DEFAULT_SIZE, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithOneBelowSizeOverSizeForRepeatShouldWrapOnceToOneBelowSize() {
        Assertions.assertEquals(
                DEFAULT_SIZE - 1,
                REPEAT.wrap(DEFAULT_SIZE + DEFAULT_SIZE - 1L, DEFAULT_SIZE)
        );
    }

    @Test
    public void wrapWithSizeOverSizeForRepeatShouldWrapAroundTwiceToZero() {
        Assertions.assertEquals(
                0,
                REPEAT.wrap(DEFAULT_SIZE + DEFAULT_SIZE, DEFAULT_SIZE)
        );
    }

}
