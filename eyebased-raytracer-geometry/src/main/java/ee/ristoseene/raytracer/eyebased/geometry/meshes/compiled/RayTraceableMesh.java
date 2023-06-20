package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled;

import ee.ristoseene.raytracer.eyebased.core.raytracing.RayTraceable;
import ee.ristoseene.raytracer.eyebased.core.raytracing.group.SimpleRayTraceableGroup;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.CompilableRayTraceableGeometry;

import java.util.Collection;
import java.util.stream.Stream;

public class RayTraceableMesh extends SimpleRayTraceableGroup implements CompilableRayTraceableGeometry {

    public RayTraceableMesh(final Collection<CompiledGeometry> elements) {
        this(elements.stream());
    }

    public RayTraceableMesh(final Stream<CompiledGeometry> elements) {
        super(elements
                .filter(RayTraceableMesh::nonEmpty)
                .toArray(RayTraceable[]::new)
        );
    }

}
