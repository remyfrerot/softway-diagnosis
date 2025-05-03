package com.softway.diagnosis.domain.pathology;

public record HeartDisease() implements Pathology {

    @Override
    public String nameI18nKey() {
        return "heartDisease.name";
    }

    @Override
    public HealthIndex baseHealthIndex() {
        return new HealthIndex(3);
    }
}
