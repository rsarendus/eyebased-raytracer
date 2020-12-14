package ee.ristoseene.raytracer.eyebased.demo.scene.scenery;

import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantDoubleValueProvider;
import ee.ristoseene.raytracer.eyebased.core.providers.constant.ConstantValueProvider;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.core.transformation.transforms.RotationMatrixAndPositionTransform;
import ee.ristoseene.raytracer.eyebased.demo.impl.Constants;
import ee.ristoseene.raytracer.eyebased.demo.impl.OrientedVectorBuilder;
import ee.ristoseene.raytracer.eyebased.demo.impl.shading.FreshnelReflectivityRatioProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.shading.FreshnelValueProvider;
import ee.ristoseene.raytracer.eyebased.demo.impl.shading.FresnelReflectionColorProvider;
import ee.ristoseene.raytracer.eyebased.demo.scene.Camera;
import ee.ristoseene.raytracer.eyebased.demo.scene.Scenery;
import ee.ristoseene.raytracer.eyebased.demo.scene.camera.PerspectiveProjection;
import ee.ristoseene.raytracer.eyebased.demo.scene.camera.TurntableCamera;
import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.AbstractClosedSurface;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.Disk;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.Sphere;
import ee.ristoseene.raytracer.eyebased.shading.aggregation.MixingShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.reflection.DiffusiveShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.reflection.ReflectiveShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.shading.transparency.TransparentShadingConfiguration;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;

import java.util.stream.Stream;

import static ee.ristoseene.raytracer.eyebased.demo.impl.Constants.sRGBtoLINEAR_FACTORY;

public class SimpleScenery implements Scenery, RefractiveConstants {

    private static final FreshnelValueProvider freshnelValueProvider = new FreshnelValueProvider(0.25);

    private final Sphere largeSphere;
    private final Sphere mediumSphere;

    private final Sphere smallSphereOuter;
    private final Sphere smallSphereInner;

    private final Sphere tinySphere1;
    private final Sphere tinySphere2;

    private final Sphere lightSphere1;
    private final Sphere lightSphere2;

    private final Disk ground;

    public SimpleScenery() {
        largeSphere = createGroundedSphere(1.0,
                OrientedVectorBuilder.positionBuilder(),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createSphereShader(
                        sRGBtoLINEAR_FACTORY.create(1.0, 0.5, 0.5),
                        sRGBtoLINEAR_FACTORY.create(1.0, 0.2, 0.2)
                ));

        mediumSphere = createGroundedSphere(0.5,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(-0.25)
                        .withRightMultiplier(1.2),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createSphereShader(
                        sRGBtoLINEAR_FACTORY.create(0.5, 0.5, 1.0),
                        sRGBtoLINEAR_FACTORY.create(0.2, 0.2, 1.0)
                ));

        smallSphereOuter = createGroundedSphere(0.3,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(0.5)
                        .withRightMultiplier(-1.0),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createTransparentShader(
                        sRGBtoLINEAR_FACTORY.create(0.9, 0.9, 0.9),
                        REFRACTIVE_INDEX_AIR / REFRACTIVE_INDEX_GLASS,
                        true
                ));
        smallSphereInner = createGroundedSphere(0.3,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(0.5)
                        .withRightMultiplier(-1.0),
                AbstractClosedSurface.Facing.INWARD_FACING,
                createTransparentShader(
                        sRGBtoLINEAR_FACTORY.create(0.9, 0.9, 0.9),
                        REFRACTIVE_INDEX_GLASS / REFRACTIVE_INDEX_AIR,
                        false
                ));

        tinySphere1 = createGroundedSphere(0.125,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(-1.0)
                        .withRightMultiplier(-1.0),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createSphereShader(
                        sRGBtoLINEAR_FACTORY.create(0.5, 0.5, 0.5),
                        sRGBtoLINEAR_FACTORY.create(0.8, 0.8, 0.8)
                ));

        tinySphere2 = createGroundedSphere(0.125,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(1.0)
                        .withRightMultiplier(1.0),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createSphereShader(
                        sRGBtoLINEAR_FACTORY.create(0.5, 0.5, 0.5),
                        sRGBtoLINEAR_FACTORY.create(0.8, 0.8, 0.8)
                ));

        lightSphere1 = createSphere(0.2,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(-1.2)
                        .withRightMultiplier(-0.8)
                        .withUpMultiplier(2.6),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createLightShader(new ImmutableVector3(20.0, 18.0, 12.0)));

        lightSphere2 = createSphere(0.3,
                OrientedVectorBuilder.positionBuilder()
                        .withForwardMultiplier(1.8)
                        .withRightMultiplier(0.8)
                        .withUpMultiplier(2.0),
                AbstractClosedSurface.Facing.OUTWARD_FACING,
                createLightShader(new ImmutableVector3(11.0, 14.0, 15.0)));

        ground = new Disk()
                .withDiameter(4.0)
                .withNormal(Constants.ORIENTATION.getUpAxis())
                .withGeometryContextFactory(Constants.GEOMETRY_CONTEXT_FACTORY)
                .withShadingConfiguration(new DiffusiveShadingConfiguration()
                        .withDiffuseColor(new ConstantValueProvider<>(sRGBtoLINEAR_FACTORY.create(0.5, 0.5, 0.5))));
    }

    @Override
    public Camera createDefaultCamera() {
        TurntableCamera camera = new TurntableCamera(OrientedVectorBuilder
                .positionBuilder().withUpMultiplier(1.0).build(Factories.FACTORY_CONST_VECTOR3_xyz))
                .withProjection(new PerspectiveProjection());
        camera.zoom(5.0, false);
        return camera;
    }

    @Override
    public Stream<CompilableGeometry> getGeometry() {
        return Stream.of(
                largeSphere, mediumSphere, tinySphere1, tinySphere2,
                smallSphereOuter, smallSphereInner,
                lightSphere1, lightSphere2,
                ground
        );
    }

    private static Sphere createGroundedSphere(
            double radius, OrientedVectorBuilder positionBuilder, AbstractClosedSurface.Facing facing, ShadingConfiguration shadingConfiguration
    ) {
        return createSphere(radius, positionBuilder.withUpMultiplier(radius), facing, shadingConfiguration);
    }

    private static Sphere createSphere(
            double radius, OrientedVectorBuilder positionBuilder, AbstractClosedSurface.Facing facing, ShadingConfiguration shadingConfiguration
    ) {
        return new Sphere()
                .withFacing(facing)
                .withDiameter(radius * 2)
                .withShadingConfiguration(shadingConfiguration)
                .withGeometryContextFactory(Constants.GEOMETRY_CONTEXT_FACTORY)
                .withParentTransform(new RotationMatrixAndPositionTransform()
                        .withPosition(positionBuilder
                                .build(Factories.FACTORY_CONST_VECTOR3_xyz)));
    }

    private static ShadingConfiguration createSphereShader(final Vector3.Accessible reflectionColor, final Vector3.Accessible diffuseColor) {
        return new MixingShadingConfiguration()
                .withFirstShader(new DiffusiveShadingConfiguration()
                        .withDiffuseColor(new ConstantValueProvider<>(diffuseColor)))
                .withSecondShader(new ReflectiveShadingConfiguration()
                        .withReflectionColor(new FresnelReflectionColorProvider(freshnelValueProvider, reflectionColor))
                        .withSurfaceRoughness(new ConstantDoubleValueProvider(0.05)))
                .withRatio(new FreshnelReflectivityRatioProvider(freshnelValueProvider, 0.4));
    }

    private static ShadingConfiguration createTransparentShader(final Vector3.Accessible color, final double ior, final boolean reflect) {
        final ShadingConfiguration reflectiveShader = new ReflectiveShadingConfiguration();
        final ShadingConfiguration refractiveShader = new TransparentShadingConfiguration()
                .withTransparencyColor(new ConstantValueProvider<>(color))
                .withIndexOfRefraction(new ConstantDoubleValueProvider(ior))
                .withTotalInternalReflectionShader(reflectiveShader);
        return reflect
                ? new MixingShadingConfiguration()
                        .withFirstShader(refractiveShader)
                        .withSecondShader(reflectiveShader)
                        .withRatio(new FreshnelReflectivityRatioProvider(freshnelValueProvider, 0.1))
                : refractiveShader;
    }

    private static ShadingConfiguration createLightShader(final Vector3.Accessible color) {
        return new MixingShadingConfiguration()
                .withFirstShader(new ConstantColorShadingPipeline(color))
                .withSecondShader(new ReflectiveShadingConfiguration())
                .withRatio(new FreshnelReflectivityRatioProvider(freshnelValueProvider, 0.0));
    }

}
