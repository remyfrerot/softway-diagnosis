package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.medicalunit.FindMedicalUnitsUseCase;
import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThisPathologyException;
import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class FindMedicalUnitsService implements FindMedicalUnitsUseCase {

    private final MedicalUnitRepository medicalUnitRepository;

    public FindMedicalUnitsService(final MedicalUnitRepository medicalUnitRepository) {
        this.medicalUnitRepository = medicalUnitRepository;
    }


    @Override
    public Collection<MedicalUnit> findByPathologies(final Set<Pathology> pathologies) throws NoSuchMedicalUnitForThisPathologyException {
        return List.of();
    }
}
