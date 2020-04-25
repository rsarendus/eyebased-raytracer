package ee.ristoseene.raytracer.eyebased.rasterization;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;

@FunctionalInterface
public interface SampleProcessor {

    SampleValue processSample(Ray tracingRay, double sampleWeight);

}
