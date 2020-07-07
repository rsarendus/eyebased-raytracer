package ee.ristoseene.raytracer.eyebased.demo.scene;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.mappers.DefaultFlippedRasterToViewMapper;
import ee.ristoseene.raytracer.eyebased.rasterization.mappers.DefaultRasterToViewMapper;
import ee.ristoseene.raytracer.eyebased.rasterization.processors.AbstractMultisamplingPixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.processors.SingleSamplePixelProcessor;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class SceneHolder {

    private Camera camera;
    private Scenery scenery;

    private Function<AbstractMultisamplingPixelProcessor.Configuration, PixelProcessor> pixelProcessorFactory;

    public void setScenery(final Scenery scenery) {
        this.scenery = scenery;
        if (scenery != null) {
            camera = scenery.createDefaultCamera();
        }
    }

    public Stream<CompilableGeometry> getGeometry() {
        if (scenery != null) {
            return scenery.getGeometry();
        } else {
            return Stream.empty();
        }
    }

    public Stream<RayTraceable> compileGeometry(final Optional<CompilationCache> compilationCache) {
        return getGeometry().map(g -> g.compile(compilationCache));
    }

    public void setPixelProcessorFactory(final Function<AbstractMultisamplingPixelProcessor.Configuration, PixelProcessor> pixelProcessorFactory) {
        this.pixelProcessorFactory = pixelProcessorFactory;
    }

    public PixelProcessor compilePixelProcessor(final Optional<CompilationCache> compilationCache, final int width, final int height) {
        final AbstractMultisamplingPixelProcessor.Configuration configuration = AbstractMultisamplingPixelProcessor.configuration()
                .withHorizontalPixelToViewMapper(new DefaultRasterToViewMapper(width))
                .withVerticalPixelToViewMapper(new DefaultFlippedRasterToViewMapper(height))
                .withRayProducer(camera.compileRayProducer(compilationCache, width, height))
                .withSampleValueAccumulatorFactory(Constants.SAMPLE_VALUE_ACCUMULATOR_FACTORY);

        return (pixelProcessorFactory == null
                ? (Function<AbstractMultisamplingPixelProcessor.Configuration, PixelProcessor>) SingleSamplePixelProcessor::new
                : pixelProcessorFactory
        ).apply(configuration);
    }

    public Camera getCamera() {
        return camera;
    }

}
