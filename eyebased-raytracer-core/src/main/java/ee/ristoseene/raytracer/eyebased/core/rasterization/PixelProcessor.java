package ee.ristoseene.raytracer.eyebased.core.rasterization;

import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.vecmath.Vector4;

@FunctionalInterface
public interface PixelProcessor {

    Vector4.Accessible processPixel(PixelLocation pixelLocation, SampleProcessor sampleProcessor);

    class Configuration {

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

    }

    static Configuration configuration() {
        return new Configuration();
    }

}
