package com.softway.diagnosis.application.port.input.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

public class NoSuchMedicalUnitForThesePathologiesException extends Exception {

    public NoSuchMedicalUnitForThesePathologiesException(final Set<Pathology> pathologies) {
        super("No medical unit found for the pathologies: " + pathologies);
    }
}
