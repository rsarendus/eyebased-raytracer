package ee.ristoseene.raytracer.eyebased.demo.scene.scenery;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.core.transformation.transforms.AxisAngleAndScaleAndPositionTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.impl.OrientedVectorBuilder;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.BufferBackedVertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.BufferBackedVertexAttributeSource.AttributeBinding;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.CompiledVertexPositionAndNormalSource;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.VertexAttributes;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.io.VertexAttributeLoader3;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.PreTransformedTriangleMeshFlatShadedGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.PreTransformedTriangleMeshSmoothShadedGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.TransformingVertexNormalProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.TransformingVertexPositionProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.pretransformed.VertexPositionExtractor;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.DeferredTriangleMeshFlatShadedGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.DeferredTriangleMeshSmoothShadedGeometryContextFactory;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.TransformingVertexPositionExtractor;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.VertexNormalProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.raytracer.vertices.untransformed.VertexPositionProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.shading.SurfaceNormalToColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.demo.io.Format;
import ee.ristoseene.raytracer.eyebased.demo.io.Vertices;
import ee.ristoseene.raytracer.eyebased.demo.scene.Camera;
import ee.ristoseene.raytracer.eyebased.demo.scene.Scenery;
import ee.ristoseene.raytracer.eyebased.demo.scene.camera.PerspectiveProjection;
import ee.ristoseene.raytracer.eyebased.demo.scene.camera.TurntableCamera;
import ee.ristoseene.raytracer.eyebased.demo.scene.interfaces.MeshShading;
import ee.ristoseene.raytracer.eyebased.demo.scene.interfaces.VertexSourcing;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.meshes.TriangleMesh;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.Disk;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.VertexAttributeSource;
import ee.ristoseene.raytracer.eyebased.geometry.vertices.indices.ArrayBackedVertexIndexSource;
import ee.ristoseene.raytracer.eyebased.shading.reflection.ReflectiveShadingConfiguration;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

import java.nio.ByteOrder;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SuzanneScenery implements Scenery, MeshShading, VertexSourcing {

    private final VertexAttributeSource vertexAttributeSource;
    private final int vertexCount;

    private final TriangleMesh suzanne;
    private final Disk ground;

    private VertexSourceMode vertexSourceMode;
    private MeshShadingMode meshShadingMode;

    public SuzanneScenery() {
        vertexAttributeSource = new BufferBackedVertexAttributeSource(
                Vertices.loadVertexBuffer("/suzanne/suzanne.vb.deflate", ByteOrder.BIG_ENDIAN),
                new AttributeBinding<>(VertexAttributes.VERTEX_POSITION, 0, 16, new VertexAttributeLoader3(Format.R32G32B32_SFLOAT)),
                new AttributeBinding<>(VertexAttributes.VERTEX_NORMAL, 12, 16, new VertexAttributeLoader3(Format.A2B10G10R10_SNORM_PACK32))
        );

        final int[] indexArray = Vertices.loadIndices16("/suzanne/suzanne.ib.deflate", ByteOrder.BIG_ENDIAN);
        final ArrayBackedVertexIndexSource vertexIndexSource = new ArrayBackedVertexIndexSource(indexArray);
        vertexCount = IntStream.of(indexArray).max().orElse(-1) + 1;

        suzanne = new TriangleMesh()
                .withVertexIndexSource(vertexIndexSource)
                .withShadingConfiguration(SurfaceNormalToColorShadingPipeline.INSTANCE)
                .withParentTransform(new AxisAngleAndScaleAndPositionTransform()
                        .withAxisAngle(Axis.POSITIVE_X, -35.9 * Math.PI / 180.0)
                        .withPosition(new ImmutableVector3(0.0, 0.495, 0.0)));

        ground = new Disk()
                .withDiameter(4.0)
                .withNormal(Constants.ORIENTATION.getUpAxis())
                .withGeometryContextFactory(Constants.SIMPLE_GEOMETRY_CONTEXT_FACTORY)
                .withShadingConfiguration(new ReflectiveShadingConfiguration()
                        .withReflectionColor(new ConstantValueProvider<>(Vectors.VECTOR3_ONE_ONE_ONE))
                        .withSurfaceRoughness(new ConstantDoubleValueProvider(0.25)));

        meshShadingMode = MeshShadingMode.FLAT_SHADED;
        setVertexSourceMode(VertexSourceMode.RAW_DEFERRED);
        setMeshShadingMode(MeshShadingMode.SMOOTH_SHADED);
    }

    @Override
    public Camera createDefaultCamera() {
        TurntableCamera camera = new TurntableCamera(OrientedVectorBuilder
                .positionBuilder().withUpMultiplier(0.8).build(Factories.FACTORY_CONST_VECTOR3_xyz))
                .withProjection(new PerspectiveProjection());
        camera.rotate(0.85, -0.05);
        camera.zoom(4.0, false);
        return camera;
    }

    @Override
    public Stream<CompilableGeometry> getGeometry() {
        return Stream.of(suzanne, ground);
    }

    @Override
    public void setVertexSourceMode(final VertexSourceMode mode) {
        switch (mode) {
            case RAW_DEFERRED:
                suzanne
                        .withVertexAttributeSourceProvider(transform -> vertexAttributeSource)
                        .withVertexPositionExtractor(TransformingVertexPositionExtractor.INSTANCE);
                break;
            case COMPILED_DEFERRED:
                suzanne
                        .withVertexAttributeSourceProvider(transform -> new CompiledVertexPositionAndNormalSource(
                                vertexCount,
                                new VertexPositionProvider(vertexAttributeSource),
                                new VertexNormalProvider(vertexAttributeSource)
                        ))
                        .withVertexPositionExtractor(TransformingVertexPositionExtractor.INSTANCE);
                break;
            case COMPILED_PRE_TRANSFORMED:
                suzanne
                        .withVertexAttributeSourceProvider(transform -> new CompiledVertexPositionAndNormalSource(
                                vertexCount,
                                new TransformingVertexPositionProvider(vertexAttributeSource, transform),
                                new TransformingVertexNormalProvider(vertexAttributeSource, transform)
                        ))
                        .withVertexPositionExtractor(VertexPositionExtractor.INSTANCE);
                break;
            default:
                throw new IllegalArgumentException("Unsupported vertex source mode: " + mode);
        }
        vertexSourceMode = mode;
        setMeshShadingMode(getMeshShadingMode());
    }

    @Override
    public void setMeshShadingMode(final MeshShadingMode mode) {
        switch (mode) {
            case FLAT_SHADED:
                suzanne
                        .withGeometryContextFactory(getVertexSourceMode() == VertexSourceMode.COMPILED_PRE_TRANSFORMED
                                ? PreTransformedTriangleMeshFlatShadedGeometryContextFactory.INSTANCE
                                : DeferredTriangleMeshFlatShadedGeometryContextFactory.INSTANCE
                        );
                break;
            case SMOOTH_SHADED:
                suzanne
                        .withGeometryContextFactory(getVertexSourceMode() == VertexSourceMode.COMPILED_PRE_TRANSFORMED
                                ? PreTransformedTriangleMeshSmoothShadedGeometryContextFactory.INSTANCE
                                : DeferredTriangleMeshSmoothShadedGeometryContextFactory.INSTANCE
                        );
                break;
            default:
                throw new IllegalArgumentException("Unsupported mesh shading mode: " + mode);
        }
        meshShadingMode = mode;
    }

    @Override
    public VertexSourceMode getVertexSourceMode() {
        return vertexSourceMode;
    }

    @Override
    public MeshShadingMode getMeshShadingMode() {
        return meshShadingMode;
    }

}
