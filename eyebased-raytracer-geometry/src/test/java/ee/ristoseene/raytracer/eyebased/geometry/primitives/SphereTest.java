package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.DoubleSidedShadeableRayTraceableSphere;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.InwardFacingShadeableRayTraceableSphere;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.sphere.OutwardFacingShadeableRayTraceableSphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class SphereTest extends AbstractClosedSurfaceTest {

    @Override
    protected Sphere createDefaultInstance() {
        return new Sphere();
    }

    @Test
    public void defaultDiameterShouldBeOne() {
        Sphere sphere = createDefaultInstance();

        Assertions.assertEquals(1.0, sphere.getDiameter());
    }

    @Test
    public void setDiameterShouldSetTheDiameter() {
        Sphere sphere = createDefaultInstance();

        sphere.setDiameter(3.75);

        Assertions.assertEquals(3.75, sphere.getDiameter());
    }

    @Test
    public void withDiameterShouldSetTheDiameterAndReturnItself() {
        Sphere sphere = createDefaultInstance();

        Assertions.assertSame(sphere, sphere.withDiameter(3.75));
        Assertions.assertEquals(3.75, sphere.getDiameter());
    }

    @Test
    public void compileForDoubleSidedShouldReturnDoubleSidedSphere() {
        Sphere sphere = createDefaultInstance().withFacing(Sphere.Facing.DOUBLE_SIDED);

        CompiledGeometry compilationResult = sphere.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DoubleSidedShadeableRayTraceableSphere);
    }

    @Test
    public void compileForInwardFacingShouldReturnInwardFacingSphere() {
        Sphere sphere = createDefaultInstance().withFacing(Sphere.Facing.INWARD_FACING);

        CompiledGeometry compilationResult = sphere.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof InwardFacingShadeableRayTraceableSphere);
    }

    @Test
    public void compileForOutwardFacingShouldReturnOutwardFacingSphere() {
        Sphere sphere = createDefaultInstance().withFacing(Sphere.Facing.OUTWARD_FACING);

        CompiledGeometry compilationResult = sphere.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof OutwardFacingShadeableRayTraceableSphere);
    }

    @Test
    public void compileForUnsetFacingShouldReturnOutwardFacingSphere() {
        Sphere sphere = createDefaultInstance();

        CompiledGeometry compilationResult = sphere.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof OutwardFacingShadeableRayTraceableSphere);
    }

    @Test
    public void cloneShouldReturnValidCopyOfItself() {
        Sphere originalSphere = createDefaultInstance().withDiameter(1.234);

        Sphere clonedSphere = super.cloneShouldCreateValidCopyOfItself(originalSphere);

        Assertions.assertNotSame(originalSphere, clonedSphere);
        Assertions.assertEquals(1.234, clonedSphere.getDiameter());
    }

}
