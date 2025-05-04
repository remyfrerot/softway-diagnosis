package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThisPathologyException;
import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.Cardiology;
import com.softway.diagnosis.domain.medicalunit.Traumatology;
import com.softway.diagnosis.domain.pathology.Fracture;
import com.softway.diagnosis.domain.pathology.HeartDisease;
import com.softway.diagnosis.domain.pathology.Pathology;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for finding medical units")
@ExtendWith(MockitoExtension.class)
class FindMedicalUnitsServiceTest {

    @Mock
    private MedicalUnitRepository medicalUnitRepository;

    @InjectMocks
    private FindMedicalUnitsService findMedicalUnitService;

    @DisplayName("Find the medical units for the given pathologies")
    @Nested
    class FindMedicalUnitsByPathologies {

        @DisplayName("When the pathologies are:")
        @Nested
        class WhenPathologiesAre {

            @Mock
            private Pathology unknownPathology1;

            @Mock
            private Pathology unknownPathology2;

            @DisplayName("- a heart disease, shall return cardiology")
            @Test
            void heartDiseaseShallReturnCardiology() throws NoSuchMedicalUnitForThisPathologyException {
                final Set<Pathology> pathologies = Set.of(new HeartDisease());
                assertThat(findMedicalUnitService.findByPathologies(pathologies))
                        .containsExactly(new Cardiology(pathologies));
            }

            @DisplayName("- a fracture, shall return traumatology")
            @Test
            void fractureShallReturnCardiology() throws NoSuchMedicalUnitForThisPathologyException {
                final Set<Pathology> pathologies = Set.of(new Fracture());
                assertThat(findMedicalUnitService.findByPathologies(pathologies))
                        .containsExactly(new Traumatology(pathologies));
            }

            @DisplayName("- a heart disease and a fracture, shall return cardiology and traumatology")
            @Test
            void heartDiseaseAndFractureShallReturnCardiologyAndTraumatology() throws NoSuchMedicalUnitForThisPathologyException {
                final HeartDisease heartDisease = new HeartDisease();
                final Fracture fracture = new Fracture();
                final Set<Pathology> pathologies = Set.of(heartDisease, fracture);
                assertThat(findMedicalUnitService.findByPathologies(pathologies))
                        .containsExactlyInAnyOrder(
                                new Cardiology(Set.of(heartDisease)),
                                new Traumatology(Set.of(fracture)));
            }

            @DisplayName("- neither a heart disease nor a fracture, shall not find any matching medical unit")
            @Test
            void neitherHeartDiseaseNorFractureShallNotFindAnyMatchingMedicalUnit() {
                Assertions.assertThatThrownBy(() -> findMedicalUnitService.findByPathologies(Set.of(unknownPathology1, unknownPathology2)))
                        .isInstanceOf(NoSuchMedicalUnitForThisPathologyException.class);
            }
        }
    }
}