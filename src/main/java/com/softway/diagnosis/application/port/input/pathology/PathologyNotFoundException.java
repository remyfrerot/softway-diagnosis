package com.softway.diagnosis.application.port.input.pathology;

import com.softway.diagnosis.domain.pathology.HealthIndex;

public class PathologyNotFoundException extends Exception {

    public PathologyNotFoundException(final HealthIndex healthIndex) {
        super("Pathology not found for health index: " + healthIndex);
    }
}
