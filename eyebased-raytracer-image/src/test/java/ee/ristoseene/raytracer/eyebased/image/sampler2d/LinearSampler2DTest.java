package ee.ristoseene.raytracer.eyebased.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.image.Image2D;
import ee.ristoseene.raytracer.eyebased.image.SamplingWrapMode;
import ee.ristoseene.raytracer.eyebased.image.helpers.ImmutableVector2i;
import ee.ristoseene.vecmath.Vector4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class LinearSampler2DTest {

    private static final int DEFAULT_MOCK_WIDTH = 4;
    private static final int DEFAULT_MOCK_HEIGHT = 3;

    @Mock
    private Image2D.Readable imageMock;

    @Mock
    private Vector4.Consumer rgba;

    @BeforeEach
    public void setUpImageMock() {
        Mockito.doReturn(DEFAULT_MOCK_WIDTH).when(imageMock).getWidth();
        Mockito.doReturn(DEFAULT_MOCK_HEIGHT).when(imageMock).getHeight();
    }


    @Test
    public void sampleWithinLimitsForClampShouldReadFromFourNearestPixels() {
        createClampingSampler().sample(rgba, 0.751, 0.332);
        verifyReadsFromCoordinates(2, 0, 3, 1);
    }

    @Test
    public void sampleBelowZeroForClampShouldReadFromZeroCoordinates() {
        createClampingSampler().sample(rgba, -0.001, -0.9);
        verifyReadsFromCoordinates(0, 0, 0, 0);
    }

    @Test
    public void sampleAboveMaximumForClampShouldReadFromMaximumCoordinates() {
        createClampingSampler().sample(rgba, 1.001, 1.9);
        verifyReadsFromCoordinates(DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1,
                DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1);
    }


    @Test
    public void sampleWithinLimitsForRepeatShouldReadFromFourNearestPixels() {
        createRepeatingSampler().sample(rgba, 0.751, 0.332);
        verifyReadsFromCoordinates(2, 0, 3, 1);
    }

    @Test
    public void sampleAroundZeroForRepeatShouldReadFromZeroAndWrappedAroundMaximumCoordinates() {
        createRepeatingSampler().sample(rgba, 0.001, -0.001);
        verifyReadsFromCoordinates(0, 0,
                DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1);
    }

    @Test
    public void sampleAroundOneForRepeatShouldReadFromMaximumAndWrappedAroundZeroCoordinates() {
        createRepeatingSampler().sample(rgba, 0.999, 1.001);
        verifyReadsFromCoordinates(0, 0,
                DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1);
    }


    private LinearSampler2D createClampingSampler() {
        return new LinearSampler2D(imageMock, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
    }

    private LinearSampler2D createRepeatingSampler() {
        return new LinearSampler2D(imageMock, SamplingWrapMode.REPEAT, SamplingWrapMode.REPEAT);
    }

    private void verifyReadsFromCoordinates(int minX, int minY, int maxX, int maxY) {
        ArgumentCaptor<Integer> xArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> yArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(imageMock, Mockito.atLeastOnce())
                .readPixel(Mockito.any(), xArgumentCaptor.capture(), yArgumentCaptor.capture());

        List<Integer> xArguments = xArgumentCaptor.getAllValues();
        List<Integer> yArguments = yArgumentCaptor.getAllValues();

        Assertions.assertEquals(xArguments.size(), yArguments.size());

        List<ImmutableVector2i> coordinates = IntStream.range(0, xArguments.size())
                .mapToObj(i -> new ImmutableVector2i(xArguments.get(i), yArguments.get(i)))
                .collect(Collectors.toList());

        for (ImmutableVector2i coordinate : coordinates) {
            Assertions.assertTrue(coordinate.getX() >= minX && coordinate.getX() <= maxX);
            Assertions.assertTrue(coordinate.getY() >= minY && coordinate.getY() <= maxY);
        }

        Assertions.assertTrue(coordinates.contains(new ImmutableVector2i(minX, minY)));
        Assertions.assertTrue(coordinates.contains(new ImmutableVector2i(maxX, minY)));
        Assertions.assertTrue(coordinates.contains(new ImmutableVector2i(minX, maxY)));
        Assertions.assertTrue(coordinates.contains(new ImmutableVector2i(maxX, maxY)));
    }

}
