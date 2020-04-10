package ee.ristoseene.raytracer.eyebased.core.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.core.rasterization.RasterToViewMapper;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;

import java.util.Objects;

public abstract class AbstractPixelProcessor implements PixelProcessor {

    protected final RasterToViewMapper horizontalRasterToViewMapper;
    protected final RasterToViewMapper verticalRasterToViewMapper;
    protected final TracingRayProducer rayProducer;

    protected AbstractPixelProcessor(final PixelProcessor.Configuration configuration) {
        horizontalRasterToViewMapper = Objects.requireNonNull(configuration.getHorizontalRasterToViewMapper());
        verticalRasterToViewMapper = Objects.requireNonNull(configuration.getVerticalRasterToViewMapper());
        rayProducer = Objects.requireNonNull(configuration.getRayProducer());
    }

}
