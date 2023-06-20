package ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;
import ee.ristoseene.raytracer.eyebased.core.raytracing.RayIntersectionContext;
import ee.ristoseene.raytracer.eyebased.core.raytracing.SampleValue;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingProcessor;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RayTriangleIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;

public class ShadeableRayTraceableTriangleMeshConfiguration implements CompiledObject, ShadeableIndirectBoundedRayTraceableTriangle.Configuration {

    private final CompiledTransform transform;
    private final RayTriangleIntersectionGeometryContextFactory geometryContextFactory;
    private final ShadingPipeline shadingPipeline;

    private final VertexAttributeSource vertexAttributeSource;
    private final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor;

    public ShadeableRayTraceableTriangleMeshConfiguration(
            final CompiledTransform transform,
            final RayTriangleIntersectionGeometryContextFactory geometryContextFactory,
            final ShadingPipeline shadingPipeline,
            final VertexAttributeSource vertexAttributeSource,
            final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor
    ) {
        this.transform = Objects.requireNonNull(transform, "Transform not provided");
        this.geometryContextFactory = Objects.requireNonNull(geometryContextFactory, "Geometry context not provided");
        this.shadingPipeline = Objects.requireNonNull(shadingPipeline, "Shading pipeline not provided");

        this.vertexAttributeSource = Objects.requireNonNull(vertexAttributeSource, "Vertex position source not provided");
        this.vertexPositionExtractor = Objects.requireNonNull(vertexPositionExtractor, "Vertex position extractor not provided");
    }

    @Override
    public SampleValue shadeTriangle(final RayIntersectionContext rayIntersectionContext, final ShadingProcessor shadingProcessor, final IndexedTriangle triangle) {
        return shadingProcessor.processShading(
                geometryContextFactory.create(rayIntersectionContext, vertexAttributeSource, triangle, transform),
                rayIntersectionContext,
                shadingPipeline
        );
    }

    @Override
    public Vector3.Accessible getVertexPosition(final int vertexIndex) {
        return vertexPositionExtractor.extract(vertexAttributeSource, vertexIndex, transform);
    }

}
