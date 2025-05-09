package com.softway.diagnosis.application.port.output.persistence;

import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;

/**
 * Repository interface for managing {@link MedicalUnit} entities.
 * This interface provides methods to save medical units and retrieve them
 * based on the pathologies they treat.
 */
public interface MedicalUnitRepository {

    /**
     * Persists the given medical unit to the underlying storage.
     * This method associates the medical unit with the pathologies it treats, allowing it to be retrieved later.
     *
     * @param medicalUnit The medical unit to be saved. Must not be null and should define the
     *                    pathologies it is capable of treating.
     */
    void save(MedicalUnit medicalUnit);

    /**
     * Retrieves a collection of medical units capable of treating the specified pathologies.
     *
     * @param pathologies The set of pathologies for which the medical units should be retrieved;
     *                    must not be null and should contain valid pathology instances.
     * @return A collection of medical units that can treat at least one of the given pathologies.
     *         If no such medical units are found, an empty collection is returned.
     */
    Collection<MedicalUnit> findByPathologies(Set<Pathology> pathologies);
}
