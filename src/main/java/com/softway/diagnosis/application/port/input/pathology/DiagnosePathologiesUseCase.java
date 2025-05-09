package com.softway.diagnosis.application.port.input.pathology;

import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

/**
 * Represents the use case for diagnosing pathologies based on a given health index.
 * This interface encapsulates the business logic required to map a health index to a set of pathologies.
 */
public interface DiagnosePathologiesUseCase {

    /**
     * Diagnoses a set of pathologies based on the provided health index.
     * The method evaluates the health index and maps it to potential pathologies.
     * If no pathology matches the given health index, a {@code PathologyNotFoundException} is thrown.
     *
     * @param healthIndex The health index used to diagnose pathologies
     * @return A set of pathologies that correspond to the provided health index
     * @throws PathologyNotFoundException If no pathology could be diagnosed for the given health index
     */
    Set<Pathology> diagnosePathologiesByHealthIndex(HealthIndex healthIndex) throws PathologyNotFoundException;
}
