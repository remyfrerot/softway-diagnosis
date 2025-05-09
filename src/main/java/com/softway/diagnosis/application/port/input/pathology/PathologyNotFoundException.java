package com.softway.diagnosis.application.port.input.pathology;

import com.softway.diagnosis.domain.pathology.HealthIndex;

/**
 * Exception indicating that no pathology could be found for a given health index.
 * This exception is thrown when attempting to diagnose pathologies based on a health index
 * with no corresponding pathology mapping.
 * <p>
 * This exception is primarily used in scenarios within the domain where diagnosing pathologies
 * is essential and the provided health index does not yield any results. It aids in signaling
 * the absence of pathology data for the given input, enabling the handling of such cases
 * appropriately in the application layer or domain logic.
 */
public class PathologyNotFoundException extends Exception {

    /**
     * Constructs a new {@code PathologyNotFoundException} with a message indicating the provided health index
     * that yielded no pathology results.
     *
     * @param healthIndex The health index that did not map to any pathology
     */
    public PathologyNotFoundException(final HealthIndex healthIndex) {
        super("Pathology not found for health index: " + healthIndex);
    }
}
