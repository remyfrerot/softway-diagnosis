package com.softway.diagnosis.domain.pathology;

public record Fracture() implements Pathology {

    @Override
    public HealthIndex baseHealthIndex() {
        return new HealthIndex(5);
    }

    @Override
    public String nameI18nKey() {
        return "fracture.name";
    }
}
