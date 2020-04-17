package ee.ristoseene.raytracer.eyebased.projection.ray;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.TracingRayProducer;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.TransformableTest;
import ee.ristoseene.raytracer.eyebased.projection.CompiledRayProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayDirectionProducer;
import ee.ristoseene.raytracer.eyebased.projection.RayOriginProducer;
import ee.ristoseene.vecmath.immutable.ImmutableMatrix4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class DefaultTransformableEyeTest extends TransformableTest {

    @Override
    protected DefaultTransformableEye createDefaultInstance() {
        return new DefaultTransformableEye();
    }

    @Test
    public void setRayDirectionProducerShouldSetTheDirectionProducer() {
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance();

        transformableEye.setRayDirectionProducer(directionProducer);

        Assertions.assertSame(directionProducer, transformableEye.getRayDirectionProducer());
    }

    @Test
    public void withRayDirectionProducerSetTheDirectionProducerAndReturnItself() {
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance();

        Assertions.assertSame(transformableEye, transformableEye.withRayDirectionProducer(directionProducer));
        Assertions.assertSame(directionProducer, transformableEye.getRayDirectionProducer());
    }

    @Test
    public void setRayOriginProducerShouldSetTheOriginProducer() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance();

        transformableEye.setRayOriginProducer(originProducer);

        Assertions.assertSame(originProducer, transformableEye.getRayOriginProducer());
    }

    @Test
    public void withRayOriginProducerSetTheOriginProducerAndReturnItself() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance();

        Assertions.assertSame(transformableEye, transformableEye.withRayOriginProducer(originProducer));
        Assertions.assertSame(originProducer, transformableEye.getRayOriginProducer());
    }

    @Test
    public void compileShouldReturnNewCompiledTransformIfNotYetCached() {
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withRayDirectionProducer(Mockito.mock(RayDirectionProducer.class));

        CompiledRayProducer compilationResult = transformableEye.compile(Optional.of(compilationCache));
        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnCachedCompiledTransformIfAlreadyInCache() {
        CompiledRayProducer cachedRayProducer = Mockito.mock(CompiledRayProducer.class);
        CompilationCache compilationCache = Mockito.mock(CompilationCache.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withRayDirectionProducer(Mockito.mock(RayDirectionProducer.class));
        Mockito.doReturn(cachedRayProducer).when(compilationCache).get(transformableEye);

        CompiledRayProducer compilationResult = transformableEye.compile(Optional.of(compilationCache));
        Assertions.assertSame(cachedRayProducer, compilationResult);
    }

    @Test
    public void compileShouldFailOnMissingRayDirectionProducer() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withRayOriginProducer(originProducer);

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> transformableEye.compile(Optional.empty())
        );

        Assertions.assertEquals("Ray direction producer not provided", exception.getMessage());
    }

    @Test
    public void compileShouldNotFailOnMissingRayOriginProducer() {
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withRayDirectionProducer(directionProducer);

        TracingRayProducer compilationResult = transformableEye.compile(Optional.empty());

        Assertions.assertNotNull(compilationResult);
    }

    @Test
    public void compileShouldReturnSimpleRayProducerOnMissingTransformer() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withRayOriginProducer(originProducer)
                .withRayDirectionProducer(directionProducer);

        TracingRayProducer compilationResult = transformableEye.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SimpleRayProducer);
    }

    @Test
    public void compileShouldReturnSimpleRayProducerWhenIdentityTransformerProvided() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withParentTransform(createMockCompilableTransform(CompiledTransform.IDENTITY_TRANSFORM))
                .withRayOriginProducer(originProducer)
                .withRayDirectionProducer(directionProducer);

        TracingRayProducer compilationResult = transformableEye.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof SimpleRayProducer);
    }

    @Test
    public void compileShouldReturnTransformingRayProducerWhenTransformerProvided() {
        RayOriginProducer originProducer = Mockito.mock(RayOriginProducer.class);
        RayDirectionProducer directionProducer = Mockito.mock(RayDirectionProducer.class);
        DefaultTransformableEye transformableEye = createDefaultInstance()
                .withParentTransform(createMockCompilableTransform(new CompiledTransform(new ImmutableMatrix4x4(3.7))))
                .withRayOriginProducer(originProducer)
                .withRayDirectionProducer(directionProducer);

        TracingRayProducer compilationResult = transformableEye.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof TransformingRayProducer);
    }

}
