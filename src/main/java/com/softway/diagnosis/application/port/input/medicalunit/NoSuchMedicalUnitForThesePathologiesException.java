package com.softway.diagnosis.application.port.input.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

/**
 * Exception thrown when no medical unit can be found that is capable of treating the specified pathologies.
 * <p>
 * This exception indicates that the search for medical units based on a given set of pathologies has failed,
 * as no suitable medical unit exists for treating at least one or more of the provided pathologies.
 * <p>
 * Usage of this exception is typically linked to the business logic of searching for medical units in
 * cases where a diagnosis yields a set of pathologies that require further treatment.
 * <p>
 * The message provides additional debug context by including the pathologies for which no medical unit is available.
 */
public class NoSuchMedicalUnitForThesePathologiesException extends Exception {

    /**
     * Constructs a new exception indicating that no medical units were found capable of treating the specified pathologies.
     *
     * @param pathologies The set of pathologies for which no suitable medical unit exists.
     */
    public NoSuchMedicalUnitForThesePathologiesException(final Set<Pathology> pathologies) {
        super("No medical unit found for the pathologies: " + pathologies);
    }
}
