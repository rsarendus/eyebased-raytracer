package ee.ristoseene.raytracer.eyebased.demo.rendering;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.cache.MapBasedCompilationCache;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.Shadeable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTracedState;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TypedAttribute;
import ee.ristoseene.raytracer.eyebased.core.raytracing.group.SimpleRayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ray.SimpleTracingRayContext;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.BounceContextImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SampleValueImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.ShadingContextImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.StatefulRayTracedState;
import ee.ristoseene.raytracer.eyebased.demo.scene.SceneHolder;
import ee.ristoseene.raytracer.eyebased.rasterization.PixelProcessor;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterArea;
import ee.ristoseene.raytracer.eyebased.rasterization.RasterSpliterator;
import ee.ristoseene.raytracer.eyebased.rasterization.RenderTarget;
import ee.ristoseene.raytracer.eyebased.rasterization.resolvers.DefaultHorizontalScanLinePixelLocationResolver;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceShadingFilter;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BouncingRayProcessor;
import ee.ristoseene.raytracer.eyebased.shading.processors.UniformSampleCountBounceSamplingProcessor;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

// TODO: Clean this mess up
public class Render implements Runnable {

    private static final Vector3.Accessible SKY_DOME_COLOR = new ImmutableVector3(0.025);

    private final RenderTarget renderTarget;
    private final Runnable renderFinishListener;

    private final Map<TypedAttribute, Object> shadingConfiguration;

    private final PixelProcessor pixelProcessor;

    private final RayTraceableGroup traceables;
    private final Shadeable skyDomeResolver;

    public Render(RenderTarget renderTarget, SceneHolder sceneHolder, Runnable renderFinishListener) {
        this.renderTarget = renderTarget;
        this.renderFinishListener = renderFinishListener;

        this.shadingConfiguration = Map.of(
                SampleValueFactory.KEY, Constants.SAMPLE_VALUE_FACTORY,
                SampleValueAccumulatorFactory.KEY, Constants.SAMPLE_VALUE_ACCUMULATOR_FACTORY,
                BouncingRayProcessor.KEY, (BouncingRayProcessor) this::processBouncingRay,
                BounceSamplingProcessor.KEY, new UniformSampleCountBounceSamplingProcessor(Render::resolve),
                BounceShadingFilter.KEY, (BounceShadingFilter) Render::filter
        );

        this.skyDomeResolver = (r, d) -> new SampleValueImpl(SKY_DOME_COLOR, r.getRayIntersectionDistance());

        final CompilationCache compilationCache = new MapBasedCompilationCache(new HashMap<>());
        this.traceables = new SimpleRayTraceableGroup(sceneHolder.compileGeometry(Optional.of(compilationCache)));
        this.pixelProcessor = sceneHolder.compilePixelProcessor(Optional.of(compilationCache),
                renderTarget.getWidth(), renderTarget.getHeight());
    }

    @Override
    public void run() {
        final Thread renderThread = Thread.currentThread();
        StreamSupport.stream(new RasterSpliterator<>(new DefaultHorizontalScanLinePixelLocationResolver(new RasterArea(renderTarget))), true)
                .filter(pl -> !renderThread.isInterrupted())
                .map(pl -> pixelProcessor.processPixel(pl, this::renderSample))
                .forEach(renderTarget::writePixel);

        renderFinishListener.run();
    }

    SampleValue renderSample(final Ray tracingRay, final double sampleWeight) {
        final StatefulRayTracedState rayTracedState = new StatefulRayTracedState(skyDomeResolver);
        processRayTracing(rayTracedState, tracingRay);
        return processShading(rayTracedState, tracingRay, new BounceContextImpl(sampleWeight));
    }

    SampleValue processBouncingRay(final Ray bouncingRay, final BounceContext bounceContext) {
        final StatefulRayTracedState rayTracedState = new StatefulRayTracedState(skyDomeResolver);
        processRayTracing(rayTracedState, bouncingRay);
        return processShading(rayTracedState, bouncingRay, bounceContext);
    }

    SampleValue processShading(final StatefulRayTracedState rayTracedState, final Ray ray, final BounceContext bounceContext) {
        return rayTracedState.shadeCurrentState(ray, (geometryContext, rayIntersectionContext, shadingPipeline) ->
                shadingPipeline.shade(new ShadingContextImpl(geometryContext, rayIntersectionContext, shadingConfiguration), bounceContext)
        );
    }

    void processRayTracing(final RayTracedState rayTracedState, final Ray ray) {
        final TracingRayContext rayContext = new SimpleTracingRayContext(ray);
        if (traceables.getAABB().intersects(rayContext, rayTracedState)) {
            traceables.interactWith(rayContext, rayTracedState);
        }
    }

    static int resolve(final BounceContext bounceContext, final double scatter) {
        return Math.max((int) (1000 * bounceContext.getAccumulatedSampleWeight() * scatter + 0.5), 1);
    }

    static boolean filter(final BounceContext bounceContext, final double r, final double g, final double b) {
        final int bounce = bounceContext.getBounce();
        if (bounce == 0) return true;
        return bounce < 10 && bounceContext.getAccumulatedSampleWeight() > Double.MIN_NORMAL;
    }

}
