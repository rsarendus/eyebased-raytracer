package ee.ristoseene.raytracer.eyebased.rasterization.processors;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterToViewMapper;

import java.util.Objects;

public abstract class AbstractPixelProcessor implements PixelProcessor {

    protected final RasterToViewMapper horizontalRasterToViewMapper;
    protected final RasterToViewMapper verticalRasterToViewMapper;
    protected final TracingRayProducer rayProducer;

    protected AbstractPixelProcessor(final Configuration configuration) {
        horizontalRasterToViewMapper = Objects.requireNonNull(
                configuration.getHorizontalRasterToViewMapper(),
                "Horizontal raster to view mapper not provided"
        );
        verticalRasterToViewMapper = Objects.requireNonNull(
                configuration.getVerticalRasterToViewMapper(),
                "Vertical raster to view mapper not provided"
        );
        rayProducer = Objects.requireNonNull(
                configuration.getRayProducer(),
                "Ray producer not provided"
        );
    }

    public static class Configuration {

        private RasterToViewMapper horizontalRasterToViewMapper;
        private RasterToViewMapper verticalRasterToViewMapper;
        private TracingRayProducer rayProducer;

        public RasterToViewMapper getHorizontalRasterToViewMapper() {
            return horizontalRasterToViewMapper;
        }

        public void setHorizontalRasterToViewMapper(final RasterToViewMapper horizontalRasterToViewMapper) {
            this.horizontalRasterToViewMapper = horizontalRasterToViewMapper;
        }

        public Configuration withHorizontalPixelToViewMapper(final RasterToViewMapper horizontalRasterToViewMapper) {
            setHorizontalRasterToViewMapper(horizontalRasterToViewMapper);
            return this;
        }

        public RasterToViewMapper getVerticalRasterToViewMapper() {
            return verticalRasterToViewMapper;
        }

        public void setVerticalRasterToViewMapper(final RasterToViewMapper verticalRasterToViewMapper) {
            this.verticalRasterToViewMapper = verticalRasterToViewMapper;
        }

        public Configuration withVerticalPixelToViewMapper(final RasterToViewMapper verticalRasterToViewMapper) {
            setVerticalRasterToViewMapper(verticalRasterToViewMapper);
            return this;
        }

        public TracingRayProducer getRayProducer() {
            return rayProducer;
        }

        public void setRayProducer(final TracingRayProducer rayProducer) {
            this.rayProducer = rayProducer;
        }

        public Configuration withRayProducer(final TracingRayProducer rayProducer) {
            setRayProducer(rayProducer);
            return this;
        }

        public Configuration withConfiguration(final Configuration configuration) {
            setHorizontalRasterToViewMapper(configuration.getHorizontalRasterToViewMapper());
            setVerticalRasterToViewMapper(configuration.getVerticalRasterToViewMapper());
            setRayProducer(configuration.getRayProducer());
            return this;
        }

    }

    public static Configuration configuration() {
        return new Configuration();
    }

}
