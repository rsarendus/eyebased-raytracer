package ee.ristoseene.raytracer.eyebased.core.rasterization;

import ee.ristoseene.raytracer.eyebased.core.raytracing.Ray;
import ee.ristoseene.vecmath.Vector4;

@FunctionalInterface
public interface SampleProcessor {

    Vector4.Accessible processSample(Ray tracingRay, double sampleWeight);

}
