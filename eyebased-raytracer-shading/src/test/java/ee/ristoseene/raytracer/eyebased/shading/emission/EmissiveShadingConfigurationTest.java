package ee.ristoseene.raytracer.eyebased.shading.emission;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.common.AbstractShadingConfigurationTest;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.ConstantColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.emission.compiled.DynamicColorShadingPipeline;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.vecmath.Vector3;
import ee.ristoseene.vecmath.immutable.ImmutableVector3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.createCompilableValueProviderMock;
import static ee.ristoseene.raytracer.eyebased.shading.helpers.ShadingTestingUtilities.createValueProviderMock;

public class EmissiveShadingConfigurationTest extends AbstractShadingConfigurationTest {

    @Override
    protected EmissiveShadingConfiguration createDefaultInstance() {
        return new EmissiveShadingConfiguration();
    }

    @Test
    public void setColorShouldSetTheColor() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        EmissiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        shadingConfiguration.setColor(color);

        Assertions.assertSame(color, shadingConfiguration.getColor());
    }

    @Test
    public void withColorShouldSetTheColorAndReturnItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        EmissiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        Assertions.assertSame(shadingConfiguration, shadingConfiguration.withColor(color));
        Assertions.assertSame(color, shadingConfiguration.getColor());
    }

    @Test
    public void compileForUnsetColorShouldReturnConstantColorPipeline() {
        EmissiveShadingConfiguration shadingConfiguration = createDefaultInstance();

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForConstantColorShouldReturnConstantColorPipeline() {
        EmissiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(new ConstantValueProvider<>(new ImmutableVector3(1.1, 2.2, 3.3)));

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof ConstantColorShadingPipeline);
    }

    @Test
    public void compileForNonConstantColorShouldReturnDynamicColorPipeline() {
        ValueProvider<Vector3.Accessible> colorProvider = createValueProviderMock();
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock(colorProvider);
        EmissiveShadingConfiguration shadingConfiguration = createDefaultInstance()
                .withColor(color);

        ShadingPipeline compilationResult = shadingConfiguration.compile(Optional.empty());

        Assertions.assertTrue(compilationResult instanceof DynamicColorShadingPipeline);
    }

    @Test
    public void cloneShouldCreateValidCopyOfItself() {
        CompilableValueProvider<Vector3.Accessible> color = createCompilableValueProviderMock();
        EmissiveShadingConfiguration originalShadingConfiguration = createDefaultInstance()
                .withColor(color);

        EmissiveShadingConfiguration clonedShadingConfiguration = super.cloneShouldCreateValidCopyOfItself(originalShadingConfiguration);

        Assertions.assertNotSame(originalShadingConfiguration, clonedShadingConfiguration);
        Assertions.assertSame(color, clonedShadingConfiguration.getColor());
    }

}
