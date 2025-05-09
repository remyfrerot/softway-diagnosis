package com.softway.diagnosis.domain.pathology;

/**
 * Represents the pathology of "Heart Disease" within the medical domain.
 * This class implements the {@code Pathology} interface, defining a specific
 * condition.
 * <p>
 * The base health index for "Heart Disease" is set to 3, which aligns with the
 * prescribed standard of using unique prime number identifiers for various pathologies.
 */
public record HeartDisease() implements Pathology {

    @Override
    public String nameI18nKey() {
        return "heartDisease.name";
    }

    /**
     * Retrieves the base health index associated with the "Heart Disease" pathology.
     * The base health index serves as a unique identifier for this pathology and is
     * set to 3, representing a prime number as per the standard conventions.
     *
     * @return A {@code HealthIndex} object with a value of 3, uniquely identifying the "Heart Disease" pathology
     */
    @Override
    public HealthIndex baseHealthIndex() {
        return new HealthIndex(3);
    }
}
