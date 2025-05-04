package com.softway.diagnosis.infrastructure.adapter.input.console;

import com.softway.diagnosis.application.port.input.medicalunit.FindMedicalUnitsUseCase;
import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.input.pathology.DiagnosePathologiesUseCase;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.application.service.DiagnosePathologiesService;
import com.softway.diagnosis.application.service.FindMedicalUnitsService;
import com.softway.diagnosis.domain.medicalunit.Cardiology;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.medicalunit.Traumatology;
import com.softway.diagnosis.domain.pathology.Fracture;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.HeartDisease;
import com.softway.diagnosis.domain.pathology.Pathology;
import com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory.InMemoryMedicalUnitRepository;
import com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory.InMemoryPathologyRepository;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class DiagnosisConsoleApplication {

    private final DiagnosePathologiesUseCase diagnosePathologiesUseCase;
    private final FindMedicalUnitsUseCase findMedicalUnitsUseCase;

    public DiagnosisConsoleApplication() {
        final HeartDisease heartDisease = new HeartDisease();
        final Fracture fracture = new Fracture();

        final PathologyRepository pathologyRepository = new InMemoryPathologyRepository();
        pathologyRepository.save(heartDisease);
        pathologyRepository.save(fracture);

        final MedicalUnitRepository medicalUnitRepository = new InMemoryMedicalUnitRepository();
        medicalUnitRepository.save(new Cardiology(Set.of(heartDisease)));
        medicalUnitRepository.save(new Traumatology(Set.of(fracture)));

        diagnosePathologiesUseCase = new DiagnosePathologiesService(pathologyRepository);
        findMedicalUnitsUseCase = new FindMedicalUnitsService(medicalUnitRepository);
    }

    public Collection<MedicalUnit> findMedicalUnitsByHealthIndex(final HealthIndex healthIndex)
            throws PathologyNotFoundException, NoSuchMedicalUnitForThesePathologiesException {
        final Set<Pathology> diagnosedPathologies = diagnosePathologiesUseCase.diagnosePathologiesByHealthIndex(healthIndex);
        return findMedicalUnitsUseCase.findByPathologies(diagnosedPathologies);
    }

    public static String toString(final Collection<MedicalUnit> medicalUnits) {
        final ResourceBundle bundle = ResourceBundle.getBundle("MedicalI18n");
        return medicalUnits.stream()
                .map(MedicalUnit::nameI18nKey)
                .map(bundle::getString)
                .collect(Collectors.joining(", "));
    }
}
