package com.softway.diagnosis.domain.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

/**
 * Represents the traumatology unit within a medical context.
 * This medical unit specializes in diagnosing and treating pathologies related
 * to traumas or injuries. The treated pathologies are defined via the constructor.
 * The name of the medical unit can be obtained in a localized, internationalized
 * format using the {@code nameI18nKey} method.
 * <p>
 * This class is a concrete implementation of the {@code MedicalUnit} interface
 * and provides the necessary details specific to traumatology.
 *
 * @param treatedPathologies A set of pathologies that the traumatology unit is specialized in treating.
 */
public record Traumatology(Set<Pathology> treatedPathologies) implements MedicalUnit {

    @Override
    public String nameI18nKey() {
        return "traumatology.name";
    }
}
