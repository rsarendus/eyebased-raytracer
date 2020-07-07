package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueAccumulatorFactory;
import ee.ristoseene.raytracer.eyebased.core.configuration.SampleValueFactory;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SampleValueAccumulatorImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SampleValueImpl;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.SimpleGeometryContextImpl;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.image.utilities.StandardRGB;
import ee.ristoseene.raytracer.eyebased.projection.Orientation;
import ee.ristoseene.vecmath.Vector3;

import java.awt.Color;

public final class Constants {

    public static final double MINIMUM_DEPTH = 0.000001;
    public static final double MAXIMUM_DEPTH = Double.MAX_VALUE;

    public static final Orientation ORIENTATION = Orientation.builder()
            .withUpAxis(Axis.POSITIVE_Y)
            .withForwardAxis(Axis.NEGATIVE_Z)
            .withRightAxis(Axis.POSITIVE_X)
            .build();

    public static final Color WIREFRAME_DEBUG_COLOR = new Color(0.9f, 0.4f, 0.1f, 1.0f);
    public static final Color WIREFRAME_GEOMETRY_COLOR = new Color(0.2f, 0.2f, 0.2f, 1.0f);

    public static final SampleValueAccumulatorFactory SAMPLE_VALUE_ACCUMULATOR_FACTORY = SampleValueAccumulatorImpl::new;
    public static final SampleValueFactory SAMPLE_VALUE_FACTORY = (shadingContext, red, green, blue, alpha) -> new
            SampleValueImpl(red, green, blue, shadingContext.getRayIntersectionContext().getRayIntersectionDistance());

    public static final RaySurfaceIntersectionGeometryContextFactory GEOMETRY_CONTEXT_FACTORY = SimpleGeometryContextImpl::new;

    public static final Vector3.Factory<Vector3.Accessible> sRGBtoLINEAR_FACTORY = (r, g, b) -> StandardRGB
            .sRGBToLinear(r, g, b, Factories.FACTORY_CONST_VECTOR3_xyz);

}
