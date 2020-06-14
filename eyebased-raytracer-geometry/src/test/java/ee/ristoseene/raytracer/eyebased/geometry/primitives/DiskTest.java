package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.raytracer.eyebased.geometry.CompiledGeometry;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk.DoubleSidedShadeableRayTraceableDisk;
import ee.ristoseene.raytracer.eyebased.geometry.primitives.compiled.disk.FrontFacingShadeableRayTraceableDisk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DiskTest extends AbstractFlatSurfaceTest {

    @Override
    protected Disk createEmptyInstance() {
        return new Disk();
    }

    @Test
    public void defaultDiameterShouldBeOne() {
        Disk disk = createEmptyInstance();

        Assertions.assertEquals(1.0, disk.getDiameter());
    }

    @Test
    public void setDiameterShouldSetTheDiameter() {
        Disk disk = createEmptyInstance();

        disk.setDiameter(3.75);

        Assertions.assertEquals(3.75, disk.getDiameter());
    }

    @Test
    public void withDiameterShouldSetTheDiameterAndReturnItself() {
        Disk disk = createEmptyInstance();

        Assertions.assertSame(disk, disk.withDiameter(3.75));
        Assertions.assertEquals(3.75, disk.getDiameter());
    }

    @Test
    public void compileForDoubleSidedShouldReturnDoubleSidedDisk() {
        Disk disk = createEmptyInstance().withNormal(Axis.POSITIVE_X)
                .withFacing(Disk.Facing.DOUBLE_SIDED);

        CompiledGeometry compilationResult = disk.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DoubleSidedShadeableRayTraceableDisk);
    }

    @Test
    public void compileForFrontFacingShouldReturnFrontFacingDisk() {
        Disk disk = createEmptyInstance().withNormal(Axis.POSITIVE_X)
                .withFacing(Disk.Facing.FRONT_FACING);

        CompiledGeometry compilationResult = disk.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof FrontFacingShadeableRayTraceableDisk);
    }

    @Test
    public void compileForUnsetFacingShouldReturnFrontFacingDisk() {
        Disk disk = createEmptyInstance().withNormal(Axis.POSITIVE_X);

        CompiledGeometry compilationResult = disk.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof FrontFacingShadeableRayTraceableDisk);
    }

    @Test
    public void cloneShouldReturnValidCopyOfItself() {
        Disk originalDisk = createEmptyInstance().withDiameter(1.234);

        Disk clonedDisk = super.cloneShouldCreateValidCopyOfItself(originalDisk);

        Assertions.assertNotSame(originalDisk, clonedDisk);
        Assertions.assertEquals(1.234, clonedDisk.getDiameter());
    }

}
