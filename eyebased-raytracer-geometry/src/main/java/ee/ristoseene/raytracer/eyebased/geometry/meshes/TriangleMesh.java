package ee.ristoseene.raytracer.eyebased.geometry.meshes;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.common.compiled.UnRayTraceableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RayTriangleIntersectionGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.RayTraceableMesh;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.DegenerateTriangleException;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.ShadeableIndirectBoundedRayTraceableTriangle;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.compiled.triangles.ShadeableRayTraceableTriangleMeshConfiguration;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexIndexSource;
import ee.ristoseene.vecmath.Vector3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class TriangleMesh extends AbstractMesh {

    private RayTriangleIntersectionGeometryContextFactory geometryContextFactory;

    public RayTriangleIntersectionGeometryContextFactory getGeometryContextFactory() {
        return geometryContextFactory;
    }

    public void setGeometryContextFactory(final RayTriangleIntersectionGeometryContextFactory geometryContextFactory) {
        this.geometryContextFactory = geometryContextFactory;
    }

    public TriangleMesh withGeometryContextFactory(final RayTriangleIntersectionGeometryContextFactory geometryContextFactory) {
        setGeometryContextFactory(geometryContextFactory);
        return this;
    }

    @Override
    public TriangleMesh withParentTransform(final CompilableTransform parentTransform) {
        return (TriangleMesh) super.withParentTransform(parentTransform);
    }

    @Override
    public TriangleMesh withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (TriangleMesh) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public TriangleMesh withVertexAttributeSourceProvider(final VertexAttributeSourceProvider vertexAttributeSourceProvider) {
        return (TriangleMesh) super.withVertexAttributeSourceProvider(vertexAttributeSourceProvider);
    }

    @Override
    public TriangleMesh withVertexPositionExtractor(final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor) {
        return (TriangleMesh) super.withVertexPositionExtractor(vertexPositionExtractor);
    }

    @Override
    public TriangleMesh withVertexIndexSource(final VertexIndexSource vertexIndexSource) {
        return (TriangleMesh) super.withVertexIndexSource(vertexIndexSource);
    }

    @Override
    public TriangleMesh clone() {
        return (TriangleMesh) super.clone();
    }

    @Override
    public CompiledGeometry compile(final Optional<CompilationCache> compilationCache) {
        if (getVertexAttributeSourceProvider() != null || getVertexIndexSource() != null) {
            Objects.requireNonNull(getVertexAttributeSourceProvider(), "Vertex attribute source not provided");
            Objects.requireNonNull(getVertexPositionExtractor(), "Vertex position extractor not provided");
            Objects.requireNonNull(getVertexIndexSource(), "Vertex index source not provided");
        }

        return super.compile(compilationCache);
    }

    @Override
    protected CompiledGeometry createCompiledGeometry(final Optional<CompilationCache> compilationCache) {
        final CompiledTransform transform = getCompiledParentTransform(compilationCache);

        if (getVertexAttributeSourceProvider() == null || getVertexIndexSource() == null || getVertexIndexSource().getIndexCount() == 0) {
            return UnRayTraceableGeometry.INSTANCE;
        }

        final VertexIndexSource vertexIndexSource = getVertexIndexSource();
        final int indexCount = vertexIndexSource.getIndexCount();

        if (indexCount % 3 != 0) {
            throw new IllegalStateException("Invalid index count: " + indexCount);
        }

        final VertexAttributeSource vertexAttributeSource = getVertexAttributeSourceProvider().getVertexAttributeSource(transform);

        for (int i = 0; i < indexCount; ++i) {
            final int vertexIndex = vertexIndexSource.getIndex(i);

            if (!vertexAttributeSource.isValidIndex(vertexIndex)) {
                throw new IllegalStateException("Invalid vertex index: " + vertexIndex);
            }
        }

        final ShadeableRayTraceableTriangleMeshConfiguration triangleMeshConfiguration = new ShadeableRayTraceableTriangleMeshConfiguration(
                transform,
                getGeometryContextFactoryOrNoOp(),
                getCompiledShadingPipeline(compilationCache),
                vertexAttributeSource,
                getVertexPositionExtractor()
        );

        final int triangleCount = indexCount / 3;
        final ArrayList<CompiledGeometry> triangles = new ArrayList<>(triangleCount);

        for (int i = 0; i < triangleCount; ++i) {
            try {
                triangles.add(new ShadeableIndirectBoundedRayTraceableTriangle(
                        triangleMeshConfiguration,
                        vertexIndexSource.getIndex(3 * i),
                        vertexIndexSource.getIndex(3 * i + 1),
                        vertexIndexSource.getIndex(3 * i + 2)
                ));
            } catch (DegenerateTriangleException e) {
                // Swallow the exception and skip this triangle
            }
        }

        if (triangles.size() == 1) {
            return triangles.get(0);
        } else if (triangles.size() > 1) {
            return new RayTraceableMesh(triangles);
        } else {
            return UnRayTraceableGeometry.INSTANCE;
        }
    }

    protected RayTriangleIntersectionGeometryContextFactory getGeometryContextFactoryOrNoOp() {
        return (getGeometryContextFactory() == null)
                ? RayTriangleIntersectionGeometryContextFactory.NO_OP_INSTANCE
                : getGeometryContextFactory();
    }

}
