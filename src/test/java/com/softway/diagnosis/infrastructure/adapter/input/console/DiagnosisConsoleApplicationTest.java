package com.softway.diagnosis.infrastructure.adapter.input.console;

import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.domain.medicalunit.Cardiology;
import com.softway.diagnosis.domain.medicalunit.Traumatology;
import com.softway.diagnosis.domain.pathology.Fracture;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.HeartDisease;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Stream;

import static com.softway.diagnosis.utils.TestUtils.generateHealthIndexesNotMultipleAndMultipleOf;
import static com.softway.diagnosis.utils.TestUtils.generateHealthIndexesNotMultipleOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tests for all diagnosis application use cases")
class DiagnosisConsoleApplicationTest {

    private final DiagnosisConsoleApplication diagnosisConsoleApplication = new DiagnosisConsoleApplication();

    private final Cardiology expectedCardiology = new Cardiology(Set.of(new HeartDisease()));
    private final Traumatology expectedTraumatology = new Traumatology(Set.of(new Fracture()));

    @DisplayName("Find the medical units for a given health index")
    @Nested
    class TestFindMedicalUnitsByHealthIndex {

        @DisplayName("When health index is multiple of:")
        @Nested
        class WhenHealthIndexIsMultipleOf {

            @DisplayName("- 3, shall redirect to cardiology")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfThree")
            void threeShallReturnCardiology(final HealthIndex healthIndex) throws PathologyNotFoundException, NoSuchMedicalUnitForThesePathologiesException {
                assertThat(diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex))
                        .containsExactly(expectedCardiology);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfThree() {
                return generateHealthIndexesNotMultipleAndMultipleOf(5, 3);
            }

            @DisplayName("- 5, shall redirect to traumatology")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfFive")
            void fiveShallReturnTraumatology(final HealthIndex healthIndex) throws PathologyNotFoundException, NoSuchMedicalUnitForThesePathologiesException {
                assertThat(diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex))
                        .containsExactly(expectedTraumatology);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfFive() {
                return generateHealthIndexesNotMultipleAndMultipleOf(3, 5);
            }

            @DisplayName("- 3 and 5, shall redirect to cardiology and traumatology")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfThreeAndFive")
            void threeAndFiveShallReturnCardiologyAndTraumatology(final HealthIndex healthIndex) throws PathologyNotFoundException, NoSuchMedicalUnitForThesePathologiesException {
                assertThat(diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex))
                        .containsExactlyInAnyOrder(expectedCardiology, expectedTraumatology);
            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfThreeAndFive() {
                return generateHealthIndexesNotMultipleAndMultipleOf(Integer.MAX_VALUE, 3, 5);
            }

            @DisplayName("- neither 3 nor 5, shall not find any medical unit")
            @ParameterizedTest
            @MethodSource("generateHealthIndexesMultipleOfNeitherThreeNorFive")
            void neitherThreeNorFiveShallNotFindAnyMedicalUnit(final HealthIndex healthIndex) {
                assertThatThrownBy(() -> diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex))
                        .isInstanceOf(PathologyNotFoundException.class);

            }

            private static Stream<Arguments> generateHealthIndexesMultipleOfNeitherThreeNorFive() {
                return generateHealthIndexesNotMultipleOf(3, 5);
            }
        }
    }

    @DisplayName("Test localized string representation of medical units")
    @Nested
    class TestToStringMedicalUnits {

        private final ResourceBundle bundle = ResourceBundle.getBundle("MedicalI18n");

        @DisplayName("When medical unit is cardiology, shall return localized cardiology name")
        @Test
        void whenMedicalUnitIsCardiologyShallReturnLocalizedName() {
            assertThat(DiagnosisConsoleApplication.toString(Set.of(expectedCardiology)))
                    .isEqualTo(bundle.getString("cardiology.name"));
        }

        @DisplayName("When medical unit is cardiology, shall return localized cardiology name")
        @Test
        void whenMedicalUnitIsTraumatologyShallReturnLocalizedName() {
            assertThat(DiagnosisConsoleApplication.toString(Set.of(expectedTraumatology)))
                    .isEqualTo(bundle.getString("traumatology.name"));
        }

        @DisplayName("When medical units are cardiology and traumatology, shall return coma-separated localized cardiology and traumatology names")
        @Test
        void whenMedicalUnitAreCardiologyAndTraumatologyShallReturnComaSeparatedLocalizedNames() {
            final String localizedCardiologyName = bundle.getString("cardiology.name");
            final String localizedTraumatologyName = bundle.getString("traumatology.name");
            assertThat(DiagnosisConsoleApplication.toString(Set.of(expectedCardiology, expectedTraumatology)))
                    .isIn(localizedCardiologyName + ", " + localizedTraumatologyName,
                            localizedTraumatologyName + ", " + localizedCardiologyName);
        }
    }
}