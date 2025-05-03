package com.softway.diagnosis.domain.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

public record Traumatology(Set<Pathology> treatedPathologies) implements MedicalUnit {

    @Override
    public String nameI18nKey() {
        return "traumatology.name";
    }
}
