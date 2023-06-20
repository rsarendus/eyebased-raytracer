package ee.ristoseene.raytracer.eyebased.geometry.meshes;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompilableTransform;
import ee.ristoseene.raytracer.eyebased.core.transformation.CompiledTransform;
import ee.ristoseene.raytracer.eyebased.geometry.common.AbstractGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeExtractor;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexIndexSource;
import ee.ristoseene.vecmath.Vector3;

public abstract class AbstractMesh extends AbstractGeometry {

    @FunctionalInterface
    public interface VertexAttributeSourceProvider {
        VertexAttributeSource getVertexAttributeSource(CompiledTransform transform);
    }

    private VertexAttributeSourceProvider vertexAttributeSourceProvider;
    private VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor;
    private VertexIndexSource vertexIndexSource;

    public VertexAttributeSourceProvider getVertexAttributeSourceProvider() {
        return vertexAttributeSourceProvider;
    }

    public void setVertexAttributeSourceProvider(final VertexAttributeSourceProvider vertexAttributeSourceProvider) {
        this.vertexAttributeSourceProvider = vertexAttributeSourceProvider;
    }

    public AbstractMesh withVertexAttributeSourceProvider(final VertexAttributeSourceProvider vertexAttributeSourceProvider) {
        setVertexAttributeSourceProvider(vertexAttributeSourceProvider);
        return this;
    }

    public VertexAttributeExtractor<Vector3.Accessible> getVertexPositionExtractor() {
        return vertexPositionExtractor;
    }

    public void setVertexPositionExtractor(final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor) {
        this.vertexPositionExtractor = vertexPositionExtractor;
    }

    public AbstractMesh withVertexPositionExtractor(final VertexAttributeExtractor<Vector3.Accessible> vertexPositionExtractor) {
        setVertexPositionExtractor(vertexPositionExtractor);
        return this;
    }

    public VertexIndexSource getVertexIndexSource() {
        return vertexIndexSource;
    }

    public void setVertexIndexSource(final VertexIndexSource vertexIndexSource) {
        this.vertexIndexSource = vertexIndexSource;
    }

    public AbstractMesh withVertexIndexSource(final VertexIndexSource vertexIndexSource) {
        setVertexIndexSource(vertexIndexSource);
        return this;
    }

    @Override
    public AbstractMesh withParentTransform(final CompilableTransform parentTransform) {
        return (AbstractMesh) super.withParentTransform(parentTransform);
    }

    @Override
    public AbstractMesh withShadingConfiguration(final ShadingConfiguration shadingConfiguration) {
        return (AbstractMesh) super.withShadingConfiguration(shadingConfiguration);
    }

    @Override
    public AbstractMesh clone() {
        return (AbstractMesh) super.clone();
    }

}
