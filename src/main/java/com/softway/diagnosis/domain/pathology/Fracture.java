package com.softway.diagnosis.domain.pathology;

/**
 * Represents the pathology of "Fracture" within the medical domain.
 * This class implements the {@code Pathology} interface, defining a specific
 * medical condition characterized by a fracture of bones.
 * <p>
 * The base health index for "Fracture" is set to 5, adhering to the convention
 * of using prime numbers as unique identifiers for pathologies.
 */
public record Fracture() implements Pathology {

    @Override
    public String nameI18nKey() {
        return "fracture.name";
    }

    /**
     * Retrieves the base health index associated with the "Fracture" pathology.
     * The base health index serves as a unique identifier for this pathology and is
     * set to 5, following the convention of using prime numbers as pathology identifiers.
     *
     * @return A {@code HealthIndex} object with a value of 5, uniquely identifying the "Fracture" pathology
     */
    @Override
    public HealthIndex baseHealthIndex() {
        return new HealthIndex(5);
    }
}
