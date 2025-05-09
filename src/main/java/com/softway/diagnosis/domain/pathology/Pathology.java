package com.softway.diagnosis.domain.pathology;

/**
 * Represents the concept of a pathology within a medical domain.
 * A pathology defines a specific condition or disease, uniquely identified
 * by its base health index, ideally encapsulating a prime number.
 */
public interface Pathology {

    /**
     * Retrieves the internationalization key associated with the name of the pathology.
     * This key is used for getting localized names for the pathology.
     *
     * @return A string representing the internationalization key for the pathology name
     */
    String nameI18nKey();

    /**
     * Retrieves the base health index associated with this pathology.
     * The base health index uniquely identifies the pathology and is typically
     * used for categorization or diagnosis purposes within the medical domain.
     * It ideally encapsulates a prime number.
     *
     * @return A {@code HealthIndex} object representing the base health index of the pathology
     */
    HealthIndex baseHealthIndex();
}
