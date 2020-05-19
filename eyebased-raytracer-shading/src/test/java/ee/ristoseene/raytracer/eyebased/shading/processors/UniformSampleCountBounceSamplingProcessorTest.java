package ee.ristoseene.raytracer.eyebased.shading.processors;

import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.raytracing.BounceContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValueAccumulator;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.shading.configuration.BounceSamplingProcessor;
import ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Map;

public class UniformSampleCountBounceSamplingProcessorTest {

    @Test
    public void bounceSamplingProcessorShouldNotAllowMissingSampleCountResolver() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new UniformSampleCountBounceSamplingProcessor(null)
        );

        Assertions.assertEquals("Sample count resolver not provided", exception.getMessage());
    }

    @Test
    public void processBounceSamplingForSampleCountOneShouldReturnTheSampleValueCreatedByBounceSamplerAndStopProcessing() {
        UniformSampleCountBounceSamplingProcessor.SampleCountResolver sampleCountResolver = Mockito.mock(UniformSampleCountBounceSamplingProcessor.SampleCountResolver.class);
        Mockito.doReturn(1).when(sampleCountResolver).resolveSampleCount(Mockito.any(BounceContext.class), Mockito.anyDouble());
        BounceSamplingProcessor bounceSamplingProcessor = new UniformSampleCountBounceSamplingProcessor(sampleCountResolver);

        SampleValue sampledBounce = Mockito.mock(SampleValue.class);
        BounceSamplingProcessor.BounceSampler bounceSampler = Mockito.mock(BounceSamplingProcessor.BounceSampler.class);
        Mockito.doReturn(sampledBounce).when(bounceSampler).sampleBounce(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock();

        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce();

        SampleValue processedSampleValue = bounceSamplingProcessor.processBounceSampling(bounceSampler, shadingContext, originalBounceContext, 3.75);

        Assertions.assertSame(sampledBounce, processedSampleValue);
        Mockito.verify(sampleCountResolver, Mockito.times(1)).resolveSampleCount(originalBounceContext, 3.75);
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce();
        Mockito.verify(bounceSampler, Mockito.times(1)).sampleBounce(shadingContext, nextBounceContext);
        Mockito.verifyNoMoreInteractions(sampleCountResolver, sampledBounce, bounceSampler, shadingContext, originalBounceContext, nextBounceContext);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 8, 10})
    public void processBounceSamplingForSampleCountGreaterThanOneShouldAccumulateSampledBouncesAndReturnTheAggregatedResult(int sampleCount) {
        UniformSampleCountBounceSamplingProcessor.SampleCountResolver sampleCountResolver = Mockito.mock(UniformSampleCountBounceSamplingProcessor.SampleCountResolver.class);
        Mockito.doReturn(sampleCount).when(sampleCountResolver).resolveSampleCount(Mockito.any(BounceContext.class), Mockito.anyDouble());
        BounceSamplingProcessor bounceSamplingProcessor = new UniformSampleCountBounceSamplingProcessor(sampleCountResolver);

        SampleValue[] sampledBounces = new SampleValue[sampleCount];
        BounceSamplingProcessor.BounceSampler bounceSampler = Mockito.mock(BounceSamplingProcessor.BounceSampler.class);
        for (int i = 0; i < sampleCount; ++i) sampledBounces[i] = Mockito.mock(SampleValue.class);
        Mockito.doReturn(sampledBounces[0], Arrays.copyOfRange(sampledBounces, 1, sampleCount))
                .when(bounceSampler).sampleBounce(Mockito.any(ShadingContext.class), Mockito.any(BounceContext.class));

        SampleValue accumulatedSampleValue = Mockito.mock(SampleValue.class);
        SampleValueAccumulator sampleValueAccumulator = Mockito.mock(SampleValueAccumulator.class);
        Mockito.doReturn(accumulatedSampleValue).when(sampleValueAccumulator).getValue();
        SampleValueAccumulatorFactory sampleValueAccumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(sampleValueAccumulator).when(sampleValueAccumulatorFactory).create();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, sampleValueAccumulatorFactory
        ));

        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce(Mockito.anyDouble());

        SampleValue processedSampleValue = bounceSamplingProcessor.processBounceSampling(bounceSampler, shadingContext, originalBounceContext, 3.75);

        Assertions.assertSame(accumulatedSampleValue, processedSampleValue);
        Mockito.verify(sampleCountResolver, Mockito.times(1)).resolveSampleCount(originalBounceContext, 3.75);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(sampleValueAccumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce(AdditionalMatchers.eq(1.0 / sampleCount, 0.000001));
        Mockito.verify(bounceSampler, Mockito.times(sampleCount)).sampleBounce(shadingContext, nextBounceContext);

        ArgumentCaptor<SampleValue> sampleValueCaptor = ArgumentCaptor.forClass(SampleValue.class);
        Mockito.verify(sampleValueAccumulator, Mockito.times(sampleCount)).accumulate(sampleValueCaptor.capture(), AdditionalMatchers.eq(1.0 / sampleCount, 0.000001));
        Assertions.assertEquals(Arrays.asList(sampledBounces), sampleValueCaptor.getAllValues());

        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(sampleCountResolver, bounceSampler, accumulatedSampleValue, sampleValueAccumulator, sampleValueAccumulatorFactory,
                shadingContext, originalBounceContext, nextBounceContext);
        Mockito.verifyNoInteractions(sampledBounces);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 0})
    public void processBounceSamplingForSampleCountLessThanOneShouldReturnTheResultOfEmptyAccumulator(int sampleCount) {
        UniformSampleCountBounceSamplingProcessor.SampleCountResolver sampleCountResolver = Mockito.mock(UniformSampleCountBounceSamplingProcessor.SampleCountResolver.class);
        Mockito.doReturn(sampleCount).when(sampleCountResolver).resolveSampleCount(Mockito.any(BounceContext.class), Mockito.anyDouble());
        BounceSamplingProcessor bounceSamplingProcessor = new UniformSampleCountBounceSamplingProcessor(sampleCountResolver);
        BounceSamplingProcessor.BounceSampler bounceSampler = Mockito.mock(BounceSamplingProcessor.BounceSampler.class);

        SampleValue accumulatedSampleValue = Mockito.mock(SampleValue.class);
        SampleValueAccumulator sampleValueAccumulator = Mockito.mock(SampleValueAccumulator.class);
        Mockito.doReturn(accumulatedSampleValue).when(sampleValueAccumulator).getValue();
        SampleValueAccumulatorFactory sampleValueAccumulatorFactory = Mockito.mock(SampleValueAccumulatorFactory.class);
        Mockito.doReturn(sampleValueAccumulator).when(sampleValueAccumulatorFactory).create();

        ShadingContext shadingContext = ShadingTestingUtilities.createShadingContextMock(Map.of(
                SampleValueAccumulatorFactory.KEY, sampleValueAccumulatorFactory
        ));

        BounceContext originalBounceContext = Mockito.mock(BounceContext.class);
        BounceContext nextBounceContext = Mockito.mock(BounceContext.class);
        Mockito.doReturn(nextBounceContext).when(originalBounceContext).nextBounce(Mockito.anyDouble());

        SampleValue processedSampleValue = bounceSamplingProcessor.processBounceSampling(bounceSampler, shadingContext, originalBounceContext, 3.75);

        Assertions.assertSame(accumulatedSampleValue, processedSampleValue);
        Mockito.verify(sampleCountResolver, Mockito.times(1)).resolveSampleCount(originalBounceContext, 3.75);
        Mockito.verify(shadingContext, Mockito.times(1)).getAttributeValue(SampleValueAccumulatorFactory.KEY);
        Mockito.verify(originalBounceContext, Mockito.times(1)).nextBounce(Mockito.anyDouble());
        Mockito.verify(sampleValueAccumulatorFactory, Mockito.times(1)).create();
        Mockito.verify(sampleValueAccumulator, Mockito.times(1)).getValue();
        Mockito.verifyNoMoreInteractions(sampleCountResolver, bounceSampler, accumulatedSampleValue, sampleValueAccumulator, sampleValueAccumulatorFactory,
                shadingContext, originalBounceContext, nextBounceContext);
    }

}
