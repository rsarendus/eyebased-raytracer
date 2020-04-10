package ee.ristoseene.raytracer.eyebased.core.image.sampler2d;

import ee.ristoseene.raytracer.eyebased.core.image.Image2D;
import ee.ristoseene.raytracer.eyebased.core.image.SamplingWrapMode;
import ee.ristoseene.vecmath.Vector4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NearestSampler2DTest {

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
    public void sampleWithinLimitsForClampShouldReadFromNearestCoordinates() {
        createClampingSampler().sample(rgba, 0.751, 0.332);
        verifyReadFromCoordinates(3, 0);
    }

    @Test
    public void sampleZeroOrBelowForClampShouldReadFromZeroCoordinates() {
        createClampingSampler().sample(rgba, 0.0, -0.9);
        verifyReadFromCoordinates(0, 0);
    }

    @Test
    public void sampleMaximumOrAboveForClampShouldReadFromMaximumCoordinates() {
        createClampingSampler().sample(rgba, 1.0, 1.9);
        verifyReadFromCoordinates(DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1);
    }


    @Test
    public void sampleWithinLimitsForRepeatShouldReadFromNearestCoordinates() {
        createRepeatingSampler().sample(rgba, 0.751, 0.332);
        verifyReadFromCoordinates(3, 0);
    }

    @Test
    public void sampleNearZeroForRepeatShouldReadFromZeroCoordinates() {
        createRepeatingSampler().sample(rgba, 0.002, 0.001);
        verifyReadFromCoordinates(0, 0);
    }

    @Test
    public void sampleBelowZeroForRepeatShouldReadFromWrappedAroundCoordinates() {
        createRepeatingSampler().sample(rgba, -0.251, -0.001);
        verifyReadFromCoordinates(DEFAULT_MOCK_WIDTH - 2, DEFAULT_MOCK_HEIGHT - 1);
    }

    @Test
    public void sampleNearOneForRepeatShouldReadFromMaximumCoordinates() {
        createRepeatingSampler().sample(rgba, 0.998, 0.999);
        verifyReadFromCoordinates(DEFAULT_MOCK_WIDTH - 1, DEFAULT_MOCK_HEIGHT - 1);
    }

    @Test
    public void sampleAboveOneForRepeatShouldReadFromWrappedAroundCoordinates() {
        createRepeatingSampler().sample(rgba, 1.251, 1.001);
        verifyReadFromCoordinates(1, 0);
    }


    private NearestSampler2D createClampingSampler() {
        return new NearestSampler2D(imageMock, SamplingWrapMode.CLAMP_TO_EDGE, SamplingWrapMode.CLAMP_TO_EDGE);
    }

    private NearestSampler2D createRepeatingSampler() {
        return new NearestSampler2D(imageMock, SamplingWrapMode.REPEAT, SamplingWrapMode.REPEAT);
    }

    private void verifyReadFromCoordinates(int x, int y) {
        Mockito.verify(imageMock, Mockito.times(1)).readPixel(rgba, x, y);
        Mockito.verifyNoInteractions(rgba);
    }

}