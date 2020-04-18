package ee.ristoseene.raytracer.eyebased.rasterization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PixelLocationTest {

    @Test
    public void testPixelLocation() {
        PixelLocation pixelLocation = new PixelLocation(4, 3);
        Assertions.assertEquals(4, pixelLocation.getX());
        Assertions.assertEquals(3, pixelLocation.getY());
    }

}
