package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
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
import static org.mockito.Mockito.when;

@DisplayName("Tests for finding medical units")
@ExtendWith(MockitoExtension.class)
class FindMedicalUnitsServiceTest {

    @Mock
    private MedicalUnitRepository medicalUnitRepository;

    @InjectMocks
    private FindMedicalUnitsService findMedicalUnitService;

    @DisplayName("Find the medical units for given pathologies")
    @Nested
    class TestFindMedicalUnitsByPathologies {

        @DisplayName("When the pathologies are:")
        @Nested
        class WhenPathologiesAre {

            @Mock
            private Pathology unknownPathology1;

            @Mock
            private Pathology unknownPathology2;

            @DisplayName("- a heart disease, shall return cardiology")
            @Test
            void heartDiseaseShallReturnCardiology() throws NoSuchMedicalUnitForThesePathologiesException {
                final Set<Pathology> pathologies = Set.of(new HeartDisease());
                final Cardiology cardiology = new Cardiology(pathologies);
                when(medicalUnitRepository.findByPathologies(pathologies)).thenReturn(Set.of(cardiology));
                assertThat(findMedicalUnitService.findByPathologies(pathologies)).containsExactly(cardiology);
            }

            @DisplayName("- a fracture, shall return traumatology")
            @Test
            void fractureShallReturnCardiology() throws NoSuchMedicalUnitForThesePathologiesException {
                final Set<Pathology> pathologies = Set.of(new Fracture());
                final Traumatology traumatology = new Traumatology(pathologies);
                when(medicalUnitRepository.findByPathologies(pathologies)).thenReturn(Set.of(traumatology));
                assertThat(findMedicalUnitService.findByPathologies(pathologies)).containsExactly(traumatology);
            }

            @DisplayName("- a heart disease and a fracture, shall return cardiology and traumatology")
            @Test
            void heartDiseaseAndFractureShallReturnCardiologyAndTraumatology() throws NoSuchMedicalUnitForThesePathologiesException {
                final HeartDisease heartDisease = new HeartDisease();
                final Fracture fracture = new Fracture();
                final Set<Pathology> pathologies = Set.of(heartDisease, fracture);
                final Cardiology cardiology = new Cardiology(Set.of(heartDisease));
                final Traumatology traumatology = new Traumatology(Set.of(fracture));
                when(medicalUnitRepository.findByPathologies(pathologies)).thenReturn(Set.of(cardiology, traumatology));
                assertThat(findMedicalUnitService.findByPathologies(pathologies))
                        .containsExactlyInAnyOrder(cardiology, traumatology);
            }

            @DisplayName("- neither a heart disease nor a fracture, shall not find any matching medical unit")
            @Test
            void neitherHeartDiseaseNorFractureShallNotFindAnyMatchingMedicalUnit() {
                final Set<Pathology> unknownPathologies = Set.of(unknownPathology1, unknownPathology2);
                when(medicalUnitRepository.findByPathologies(unknownPathologies)).thenReturn(Set.of());
                Assertions.assertThatThrownBy(() -> findMedicalUnitService.findByPathologies(unknownPathologies))
                        .isInstanceOf(NoSuchMedicalUnitForThesePathologiesException.class);
            }
        }
    }
}