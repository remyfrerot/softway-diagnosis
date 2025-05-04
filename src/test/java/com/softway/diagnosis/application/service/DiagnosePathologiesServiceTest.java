package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.domain.pathology.Fracture;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.HeartDisease;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DisplayName("Tests for pathologies diagnosis")
@ExtendWith(MockitoExtension.class)
class DiagnosePathologiesServiceTest {

    @Mock
    private PathologyRepository pathologyRepository;

    @InjectMocks
    private DiagnosePathologiesService diagnosePathologiesService;

    @DisplayName("Diagnose the pathologies using a given health index")
    @Nested
    class TestDiagnosePathologiesByHealthIndex {

        private static final int MIN_MULTIPLE = 2;
        private static final int MAX_MULTIPLE = 600;

        @BeforeEach
        void setUp() {
            when(pathologyRepository.findAll()).thenReturn(List.of(new HeartDisease(), new Fracture()));
        }

        @DisplayName("When health index is multiple of:")
        @Nested
        class WhenHealthIndexIsMultipleOf {

            private final HeartDisease expectedHeartDisease = new HeartDisease();
            private final Fracture expectedFracture = new Fracture();

            private static Stream<Arguments> generateHealthIndexesNotMultipleAndMultipleOf(final int notDivisor, final int... divisors) {
                final int productOfDivisors = IntStream.of(divisors)
                        .reduce(1, (firstDivisor, secondDivisor) -> firstDivisor * secondDivisor);
                return IntStream.rangeClosed(MIN_MULTIPLE, MAX_MULTIPLE)
                        .filter(multiple -> multiple % productOfDivisors == 0 && isNotMultipleOf(multiple, notDivisor))
                        .mapToObj(HealthIndex::new)
                        .map(Arguments::of);
            }

            private static Stream<Arguments> generateHealthIndexesNotMultipleOf(final int... divisors) {
                return IntStream.rangeClosed(MIN_MULTIPLE, MAX_MULTIPLE)
                        .filter(multiple -> isNotMultipleOf(multiple, divisors))
                        .mapToObj(HealthIndex::new)
                        .map(Arguments::of);
            }

            private static boolean isNotMultipleOf(final int multiple, final int... divisors) {
                return IntStream.of(divisors).noneMatch(divisor -> multiple % divisor == 0);
            }

            @DisplayName("- 3, shall diagnose a heart disease")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfThree")
            void threeShallReturnHeartDisease(final HealthIndex healthIndex) throws PathologyNotFoundException {
                assertThat(diagnosePathologiesService.diagnosePathologiesByHealthIndex(healthIndex))
                        .containsExactly(expectedHeartDisease);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfThree() {
                return generateHealthIndexesNotMultipleAndMultipleOf(5, 3);
            }

            @DisplayName("- 5, shall diagnose a fracture")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfFive")
            void fiveShallReturnFracture(final HealthIndex healthIndex) throws PathologyNotFoundException {
                assertThat(diagnosePathologiesService.diagnosePathologiesByHealthIndex(healthIndex))
                        .containsExactly(expectedFracture);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfFive() {
                return generateHealthIndexesNotMultipleAndMultipleOf(3, 5);
            }

            @DisplayName("- 3 and 5, shall diagnose a heart disease and a fracture")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfThreeAndFive")
            void threeAndFiveShallReturnHeartDiseaseAndFracture(final HealthIndex healthIndex) throws PathologyNotFoundException {
                assertThat(diagnosePathologiesService.diagnosePathologiesByHealthIndex(healthIndex))
                        .containsExactlyInAnyOrder(expectedHeartDisease, expectedFracture);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfThreeAndFive() {
                return generateHealthIndexesNotMultipleAndMultipleOf(Integer.MAX_VALUE, 3, 5);
            }

            @DisplayName("- neither 3 nor 5, shall not find any pathology")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfNeitherThreeNorFive")
            void neitherThreeNorFiveShallNotFindAnyPathology(final HealthIndex healthIndex) {
                assertThatThrownBy(() -> diagnosePathologiesService.diagnosePathologiesByHealthIndex(healthIndex))
                        .isInstanceOf(PathologyNotFoundException.class);

            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfNeitherThreeNorFive() {
                return generateHealthIndexesNotMultipleOf(3, 5);
            }
        }
    }
}