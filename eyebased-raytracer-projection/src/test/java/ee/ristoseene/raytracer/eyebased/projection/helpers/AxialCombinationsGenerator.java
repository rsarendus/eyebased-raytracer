package ee.ristoseene.raytracer.eyebased.projection.helpers;

import ee.ristoseene.raytracer.eyebased.core.Axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class AxialCombinationsGenerator {

    private final Set<Axis> combinableAxes;

    public AxialCombinationsGenerator() {
        this(Axis.values());
    }

    public AxialCombinationsGenerator(final Axis... combinableAxes) {
        this.combinableAxes = Arrays.stream(combinableAxes)
                .collect(LinkedHashSet::new, (s, a) -> s.add(a), (s1, s2) -> s1.addAll(s2));
    }

    public Stream<List<Axis>> generateCombinationsOfSize(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Combination size cannot be negative");
        } else if (size == 0) {
            return Stream.empty();
        }

        Stream<List<Axis>> combinations = combinableAxes.stream().map(List::of);

        for (int i = 1; i < size; ++i) {
            combinations = combinations.flatMap(list -> combinableAxes.stream().map(axis -> {
                final List<Axis> newList = new ArrayList<>(list.size() + 1);
                newList.addAll(list);
                newList.add(axis);
                return newList;
            }));
        }

        return combinations;
    }

    public static class AnyPairPredicate implements Predicate<List<Axis>> {

        private final BiPredicate<Axis, Axis> pairMatcher;

        public AnyPairPredicate(final BiPredicate<Axis, Axis> pairMatcher) {
            this.pairMatcher = Objects.requireNonNull(pairMatcher);
        }

        @Override
        public boolean test(final List<Axis> axes) {
            final int combinationSize = axes.size();

            for (int i = 0; i < combinationSize - 1; ++i) {
                for (int j = i + 1; j < combinationSize; ++j) {
                    if (pairMatcher.test(axes.get(i), axes.get(j))) {
                        return true;
                    }
                }
            }

            return false;
        }

    }

    public static class AllPairsPredicate implements Predicate<List<Axis>> {

        private final BiPredicate<Axis, Axis> pairMatcher;

        public AllPairsPredicate(final BiPredicate<Axis, Axis> pairMatcher) {
            this.pairMatcher = Objects.requireNonNull(pairMatcher);
        }

        @Override
        public boolean test(final List<Axis> axes) {
            final int combinationSize = axes.size();

            for (int i = 0; i < combinationSize - 1; ++i) {
                for (int j = i + 1; j < combinationSize; ++j) {
                    if (!pairMatcher.test(axes.get(i), axes.get(j))) {
                        return false;
                    }
                }
            }

            return true;
        }

    }

}
