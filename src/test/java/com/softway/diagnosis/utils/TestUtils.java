package com.softway.diagnosis.utils;

import com.softway.diagnosis.domain.pathology.HealthIndex;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class TestUtils {

    private static final int MIN_MULTIPLE = 2;
    private static final int MAX_MULTIPLE = 600;

    private TestUtils() {
        // Utils class
    }

    public static Stream<Arguments> generateHealthIndexesNotMultipleAndMultipleOf(final int notDivisor, final int... divisors) {
        final int productOfDivisors = IntStream.of(divisors)
                .reduce(1, (firstDivisor, secondDivisor) -> firstDivisor * secondDivisor);
        return IntStream.rangeClosed(MIN_MULTIPLE, MAX_MULTIPLE)
                .filter(multiple -> multiple % productOfDivisors == 0 && isNotMultipleOf(multiple, notDivisor))
                .mapToObj(HealthIndex::new)
                .map(Arguments::of);
    }

    public static Stream<Arguments> generateHealthIndexesNotMultipleOf(final int... divisors) {
        return IntStream.rangeClosed(MIN_MULTIPLE, MAX_MULTIPLE)
                .filter(multiple -> isNotMultipleOf(multiple, divisors))
                .mapToObj(HealthIndex::new)
                .map(Arguments::of);
    }

    public static boolean isNotMultipleOf(final int multiple, final int... divisors) {
        return IntStream.of(divisors).noneMatch(divisor -> multiple % divisor == 0);
    }
}
