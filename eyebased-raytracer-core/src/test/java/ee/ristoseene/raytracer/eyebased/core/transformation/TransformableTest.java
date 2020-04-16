package ee.ristoseene.raytracer.eyebased.core.transformation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public abstract class TransformableTest {

    protected abstract Transformable createDefaultInstance();

    @Test
    public void setParentTransformShouldSetTheParentTransform() {
        CompilableTransform parentTransform = Mockito.mock(CompilableTransform.class);
        Transformable transformable = createDefaultInstance();

        transformable.setParentTransform(parentTransform);

        Assertions.assertSame(parentTransform, transformable.getParentTransform());
    }

    @Test
    public void withParentTransformShouldSetTheParentTransformAndReturnItself() {
        CompilableTransform parentTransform = Mockito.mock(CompilableTransform.class);
        Transformable transformable = createDefaultInstance();

        Assertions.assertSame(transformable, transformable.withParentTransform(parentTransform));
        Assertions.assertSame(parentTransform, transformable.getParentTransform());
    }

    protected static CompilableTransform createMockCompilableTransform(final CompiledTransform compilationResult) {
        CompilableTransform compilableTransform = Mockito.mock(CompilableTransform.class);
        Mockito.doReturn(compilationResult).when(compilableTransform).compile(Mockito.any());
        return compilableTransform;
    }

}
