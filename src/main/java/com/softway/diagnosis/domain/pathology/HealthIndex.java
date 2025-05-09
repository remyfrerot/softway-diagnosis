package com.softway.diagnosis.domain.pathology;

/**
 * Represents a health index within the medical domain, encapsulating a single integer value.
 * A health index serves as a unique identifier for pathologies and can be used for diagnosis
 * and categorization. This value often aligns with specific properties of a pathology,
 * such as being a prime number in certain cases.
 * <p>
 * This value object is used across the domain to standardize the representation of health indices
 * and ensure uniformity in pathology-related calculations.
 *
 * @param value The integer value representing the health index
 */
public record HealthIndex(int value) {

    /**
     * Determines if the current health index is a multiple of another health index.
     *
     * @param otherHealthIndex The health index to compare against.
     * @return {@code true} if the current health index is a multiple of the specified health index,
     *         {@code false} otherwise.
     */
    public boolean isMultipleOf(final HealthIndex otherHealthIndex) {
        return value() % otherHealthIndex.value() == 0;
    }
}
