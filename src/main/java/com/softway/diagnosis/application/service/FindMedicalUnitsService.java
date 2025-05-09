package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.medicalunit.FindMedicalUnitsUseCase;
import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class responsible for finding medical units capable of treating specific pathologies.
 * Implements the {@link FindMedicalUnitsUseCase} interface to encapsulate the business logic
 * required for searching medical units based on a provided set of pathologies.
 * <p>
 * This service interacts with the {@link MedicalUnitRepository} to retrieve medical units
 * and evaluates whether all the given pathologies can be treated by at least one medical unit.
 * If no medical unit is found for any pathology in the set, an exception is thrown.
 */
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

    /**
     * Identifies pathologies that are not covered by any medical unit from the given collection.
     * This method compares the input set of pathologies with the pathologies treated by
     * the provided medical units and returns the set of pathologies that are not treated.
     *
     * @param pathologies The set of pathologies to evaluate. Must not be null.
     * @param foundMedicalUnits A collection of medical units whose treated pathologies will be checked
     *                          against the given set of pathologies. Must not be null.
     * @return A set of pathologies that are not treated by any of the provided medical units.
     *         If all input pathologies are treated, an empty set is returned.
     */
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
