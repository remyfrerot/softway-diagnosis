package com.softway.diagnosis.application.port.output.persistence;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;

/**
 * Repository interface for managing {@link Pathology} entities.
 * This interface provides methods to persist pathologies and retrieve all stored pathologies.
 */
public interface PathologyRepository {

    /**
     * Persists the given pathology entity to the underlying storage.
     * This method allows the pathology to be retrieved and associated with other medical entities.
     *
     * @param pathology The pathology to be saved. Must not be null and should have valid attributes
     *                  defining its characteristics like name and base health index.
     */
    void save(Pathology pathology);

    /**
     * Retrieves all stored pathology entities.
     *
     * @return A collection of all pathology instances available in the repository.
     *         If no pathologies are present, an empty collection is returned.
     */
    Collection<Pathology> findAll();
}
