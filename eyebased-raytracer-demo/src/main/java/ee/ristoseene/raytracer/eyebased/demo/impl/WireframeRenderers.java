package ee.ristoseene.raytracer.eyebased.demo.impl;

import ee.ristoseene.raytracer.eyebased.demo.wireframe.MultiTypeWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.WireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.debug.AabbWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.CircularMapper;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.mappers.SphericalMapper;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.primitives.PrimitiveDiskWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.demo.wireframe.primitives.PrimitiveSphereWireframeRenderer;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;

import java.util.Set;

public final class WireframeRenderers {

    private static final CircularMapper CIRCULAR_MAPPER = new CircularMapperImpl();
    private static final SphericalMapper SPHERICAL_MAPPER = new SphericalMapperImpl();

    public static final PrimitiveDiskWireframeRenderer PRIMITIVE_DISK_RENDERER = new PrimitiveDiskWireframeRenderer(CIRCULAR_MAPPER);
    public static final PrimitiveSphereWireframeRenderer PRIMITIVE_SPHERE_RENDERER = new PrimitiveSphereWireframeRenderer(SPHERICAL_MAPPER);

    public static final WireframeRenderer<CompilableGeometry> GEOMETRY_RENDERER = new MultiTypeWireframeRenderer<>(CompilableGeometry.class, Set.of(
            PRIMITIVE_DISK_RENDERER, PRIMITIVE_SPHERE_RENDERER
    ));

    public static final AabbWireframeRenderer AABB_RENDERER = new AabbWireframeRenderer();

}
