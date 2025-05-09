package com.softway.diagnosis.domain.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

/**
 * Represents a medical unit that specializes in treating specific pathologies.
 * Implementers of this interface define the localized name key and the specific
 * pathologies they are equipped to treat.
 */
public interface MedicalUnit {

    /**
     * Retrieves the internationalization key representing the name of the medical unit.
     * This key is used to get the localized name of the medical unit from a resource bundle or
     * similar localization mechanism.
     *
     * @return The internationalization key for the name of this medical unit.
     */
    String nameI18nKey();

    /**
     * Retrieves the set of pathologies that this medical unit is specialized in treating.
     * This method provides information about the specific conditions handled by the
     * medical unit, enabling mapping between pathologies and the corresponding units
     * equipped to treat them.
     *
     * @return A set of pathologies that this medical unit is capable of treating.
     */
    Set<Pathology> treatedPathologies();
}
