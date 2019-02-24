package ee.ristoseene.raytracer.eyebased.core.image;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;
import ee.ristoseene.raytracer.eyebased.core.vecmath.vector4.MutableVector4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class PixelTransferUtilsTest {

    @Mock
    private Image2D.Readable sourceImage;

    @Mock
    private Image2D.Writable destinationImage;


    @Test
    public void copyShouldReadFromAllSourcePixelsAndWriteToCorrespondingDestinationPixels() {
        mockImageResolution(sourceImage, 2, 2);
        mockImageResolution(destinationImage, 2, 2);
        mockPixelReads(sourceImage);

        PixelTransferUtils.copy(sourceImage, destinationImage);

        List<ImmutableVector2i> reads = capturePixelReads(sourceImage, 2, 2);
        List<ImmutableVector2i> writes = capturePixelWrites(destinationImage, 2, 2);
        Assertions.assertEquals(reads, writes);
    }


    private void mockImageResolution(Image2D image, int desiredWidth, int desiredHeight) {
        Mockito.doReturn(desiredWidth).when(image).getWidth();
        Mockito.doReturn(desiredHeight).when(image).getHeight();
    }

    private void mockPixelReads(Image2D.Readable image) {
        Mockito.doAnswer(invocationOnMock -> {
                MutableVector4 rgba = invocationOnMock.getArgument(0);
                int x = invocationOnMock.getArgument(1);
                int y = invocationOnMock.getArgument(2);
                rgba.xyzw(x, y, x + y, x * y);
                return null;
        }).when(image).readPixel(
                Mockito.any(MutableVector4.class),
                Mockito.anyInt(),
                Mockito.anyInt()
        );
    }


    private List<ImmutableVector2i> capturePixelReads(Image2D.Readable image, int imageWidth, int imageHeight) {
        ArgumentCaptor<Integer> xArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> yArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(image, Mockito.times(imageWidth * imageHeight))
                .readPixel(Mockito.any(Vector4.Mutable.class), xArgumentCaptor.capture(), yArgumentCaptor.capture());

        return verifyAndConsolidateCapturedCoordinates(xArgumentCaptor, yArgumentCaptor, imageWidth, imageHeight);
    }

    private List<ImmutableVector2i> capturePixelWrites(Image2D.Writable image, int imageWidth, int imageHeight) {
        ArgumentCaptor<Integer> xArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> yArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(image, Mockito.times(imageWidth * imageHeight))
                .writePixel(Mockito.any(Vector4.Accessible.class), xArgumentCaptor.capture(), yArgumentCaptor.capture());

        return verifyAndConsolidateCapturedCoordinates(xArgumentCaptor, yArgumentCaptor, imageWidth, imageHeight);
    }

    private List<ImmutableVector2i> verifyAndConsolidateCapturedCoordinates(
            ArgumentCaptor<Integer> xArgumentCaptor, ArgumentCaptor<Integer> yArgumentCaptor, int imageWidth, int imageHeight
    ) {
        final int expectedArgumentCount = imageWidth * imageHeight;

        List<Integer> xArguments = xArgumentCaptor.getAllValues();
        List<Integer> yArguments = yArgumentCaptor.getAllValues();

        Assertions.assertEquals(expectedArgumentCount, xArguments.size());
        Assertions.assertEquals(expectedArgumentCount, yArguments.size());

        List<ImmutableVector2i> coordinates = IntStream.range(0, expectedArgumentCount)
                .mapToObj(i -> new ImmutableVector2i(xArguments.get(i), yArguments.get(i)))
                .collect(Collectors.toList());

        for (ImmutableVector2i coordinate : coordinates) {
            Assertions.assertTrue(coordinate.getX() >= 0 && coordinate.getX() < imageWidth);
            Assertions.assertTrue(coordinate.getY() >= 0 && coordinate.getY() < imageHeight);
            Assertions.assertEquals(1, Collections.frequency(coordinates, coordinate));
        }

        return coordinates;
    }

}