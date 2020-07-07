package ee.ristoseene.raytracer.eyebased.demo.impl.shading;

import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.constants.Factories;
import ee.ristoseene.raytracer.eyebased.core.constants.Vectors;
import ee.ristoseene.raytracer.eyebased.core.raytracing.ShadingContext;
import ee.ristoseene.raytracer.eyebased.shading.providers.CompilableValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.ValueProvider;
import ee.ristoseene.raytracer.eyebased.shading.providers.constant.ConstantValueProvider;
import ee.ristoseene.vecmath.VecMath;
import ee.ristoseene.vecmath.Vector3;

import java.util.Objects;
import java.util.Optional;

public class FresnelReflectionColorProvider implements CompilableValueProvider<Vector3.Accessible>, ValueProvider<Vector3.Accessible> {

    private final FreshnelValueProvider freshnelValueProvider;
    private final ValueProvider<Vector3.Accessible> reflectionColorProvider;

    public FresnelReflectionColorProvider(
            final FreshnelValueProvider freshnelValueProvider,
            final Vector3.Accessible reflectionColor
    ) {
        this(freshnelValueProvider, new ConstantValueProvider<>(reflectionColor));
    }

    public FresnelReflectionColorProvider(
            final FreshnelValueProvider freshnelValueProvider,
            final ValueProvider<Vector3.Accessible> reflectionColorProvider
    ) {
        this.freshnelValueProvider = Objects.requireNonNull(freshnelValueProvider);
        this.reflectionColorProvider = Objects.requireNonNull(reflectionColorProvider);
    }

    @Override
    public ValueProvider<Vector3.Accessible> compile(final Optional<CompilationCache> compilationCache) {
        return this;
    }

    @Override
    public Vector3.Accessible getValue(ShadingContext shadingContext) {
        return VecMath.lerp(
                Vectors.VECTOR3_ONE_ONE_ONE,
                reflectionColorProvider.getValue(shadingContext),
                freshnelValueProvider.getDoubleValue(shadingContext),
                Factories.FACTORY_CONST_VECTOR3_xyz
        );
    }

}
