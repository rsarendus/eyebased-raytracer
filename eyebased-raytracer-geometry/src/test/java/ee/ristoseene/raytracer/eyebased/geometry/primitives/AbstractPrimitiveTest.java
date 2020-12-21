package ee.ristoseene.raytracer.eyebased.geometry.primitives;

import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingConfiguration;
import ee.ristoseene.raytracer.eyebased.geometry.common.AbstractGeometryTest;
import ee.ristoseene.raytracer.eyebased.geometry.configuration.RaySurfaceIntersectionGeometryContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class AbstractPrimitiveTest extends AbstractGeometryTest {

    @Override
    protected abstract AbstractPrimitive createDefaultInstance();

    @Test
    public void setGeometryContextFactoryShouldSetTheGeometryContextFactory() {
        RaySurfaceIntersectionGeometryContextFactory factory = Mockito.mock(RaySurfaceIntersectionGeometryContextFactory.class);
        AbstractPrimitive primitive = createDefaultInstance();

        primitive.setGeometryContextFactory(factory);

        Assertions.assertSame(factory, primitive.getGeometryContextFactory());
    }

    @Test
    public void withGeometryContextFactoryShouldSetTheGeometryContextFactoryAndReturnItself() {
        RaySurfaceIntersectionGeometryContextFactory factory = Mockito.mock(RaySurfaceIntersectionGeometryContextFactory.class);
        AbstractPrimitive primitive = createDefaultInstance();

        Assertions.assertSame(primitive, primitive.withGeometryContextFactory(factory));
        Assertions.assertSame(factory, primitive.getGeometryContextFactory());
    }

    @Test
    public void setShadingConfigurationShouldSetTheShadingConfiguration() {
        ShadingConfiguration shadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        AbstractPrimitive primitive = createDefaultInstance();

        primitive.setShadingConfiguration(shadingConfiguration);

        Assertions.assertSame(shadingConfiguration, primitive.getShadingConfiguration());
    }

    @Test
    public void withShadingConfigurationShouldSetTheShadingConfigurationAndReturnItself() {
        ShadingConfiguration shadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        AbstractPrimitive primitive = createDefaultInstance();

        Assertions.assertSame(primitive, primitive.withShadingConfiguration(shadingConfiguration));
        Assertions.assertSame(shadingConfiguration, primitive.getShadingConfiguration());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T cloneShouldCreateValidCopyOfItself(T original) {
        AbstractPrimitive originalPrimitive = (AbstractPrimitive) original;
        RaySurfaceIntersectionGeometryContextFactory factory = Mockito.mock(RaySurfaceIntersectionGeometryContextFactory.class);
        originalPrimitive.setGeometryContextFactory(factory);
        ShadingConfiguration shadingConfiguration = Mockito.mock(ShadingConfiguration.class);
        originalPrimitive.setShadingConfiguration(shadingConfiguration);

        AbstractPrimitive clonedPrimitive = super.cloneShouldCreateValidCopyOfItself(originalPrimitive);

        Assertions.assertNotSame(originalPrimitive, clonedPrimitive);
        Assertions.assertSame(factory, clonedPrimitive.getGeometryContextFactory());
        Assertions.assertSame(shadingConfiguration, clonedPrimitive.getShadingConfiguration());

        return (T) clonedPrimitive;
    }

}
