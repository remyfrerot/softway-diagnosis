package com.softway.diagnosis.domain.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

/**
 * Represents the cardiology unit within a medical context.
 * This unit specializes in diagnosing and treating pathologies related to the heart
 * or cardiovascular system. The treated pathologies are defined via the constructor.
 * The name of the medical unit can be obtained in a localized, internationalized format
 * using the {@code nameI18nKey} method.
 * <p>
 * This class is a concrete implementation of the {@code MedicalUnit} interface
 * and provides the necessary details specific to cardiology.
 *
 * @param treatedPathologies A set of pathologies that the cardiology unit is specialized in treating.
 */
public record Cardiology(Set<Pathology> treatedPathologies) implements MedicalUnit {

    @Override
    public String nameI18nKey() {
        return "cardiology.name";
    }
}
