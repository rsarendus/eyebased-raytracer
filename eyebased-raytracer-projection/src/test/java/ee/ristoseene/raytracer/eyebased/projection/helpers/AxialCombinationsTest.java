package ee.ristoseene.raytracer.eyebased.projection.helpers;

import ee.ristoseene.raytracer.eyebased.core.Axis;
import ee.ristoseene.vecmath.VecMath;

import java.util.List;
import java.util.stream.Stream;

public interface AxialCombinationsTest {

    static Stream<List<Axis>> validCombinationsOfAxesForOrientation() {
        return new AxialCombinationsGenerator().generateCombinationsOfSize(3)
                .filter(new AxialCombinationsGenerator.AllPairsPredicate((a1, a2) -> VecMath.dot(a1, a2) == 0.0));
    }

}
