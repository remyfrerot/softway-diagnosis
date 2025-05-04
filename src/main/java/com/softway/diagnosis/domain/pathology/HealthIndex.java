package com.softway.diagnosis.domain.pathology;

public record HealthIndex(int value) {

    public boolean isMultipleOf(final HealthIndex otherHealthIndex) {
        return value() % otherHealthIndex.value() == 0;
    }
}
