package com.softway.diagnosis.application.port.input.medicalunit;

import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;

/**
 * Represents the use case for finding medical units capable of treating specific pathologies.
 * This interface encapsulates the business logic required to search for medical units.
 */
public interface FindMedicalUnitsUseCase {

    /**
     * Finds and retrieves medical units that are capable of treating the specified pathologies.
     *
     * @param pathologies Pathologies for which medical units need to be identified
     * @return Medical units that treat the given pathologies
     * @throws NoSuchMedicalUnitForThesePathologiesException If no medical unit exists that can treat some of the specified pathologies.
     */
    Collection<MedicalUnit> findByPathologies(Set<Pathology> pathologies) throws NoSuchMedicalUnitForThesePathologiesException;
}
