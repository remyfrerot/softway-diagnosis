package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.medicalunit.FindMedicalUnitsUseCase;
import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class FindMedicalUnitsService implements FindMedicalUnitsUseCase {

    private final MedicalUnitRepository medicalUnitRepository;

    public FindMedicalUnitsService(final MedicalUnitRepository medicalUnitRepository) {
        this.medicalUnitRepository = medicalUnitRepository;
    }

    @Override
    public Collection<MedicalUnit> findByPathologies(final Set<Pathology> pathologies) throws NoSuchMedicalUnitForThesePathologiesException {
        final Collection<MedicalUnit> foundMedicalUnits = medicalUnitRepository.findByPathologies(pathologies);

        final Set<Pathology> unknownPathologies = getUnknownPathologies(pathologies, foundMedicalUnits);
        if (!unknownPathologies.isEmpty()) {
            throw new NoSuchMedicalUnitForThesePathologiesException(unknownPathologies);
        }

        return foundMedicalUnits;
    }

    private static Set<Pathology> getUnknownPathologies(final Set<Pathology> pathologies, final Collection<MedicalUnit> foundMedicalUnits) {
        final Set<Pathology> treatedPathologies = foundMedicalUnits.stream()
                .map(MedicalUnit::treatedPathologies)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
        return pathologies.stream()
                .filter(pathology -> !treatedPathologies.contains(pathology))
                .collect(Collectors.toUnmodifiableSet());
    }
}
