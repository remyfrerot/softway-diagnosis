package com.softway.diagnosis.application.port.input.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

public class NoSuchMedicalUnitForThisPathologyException extends Exception {

    public NoSuchMedicalUnitForThisPathologyException(final Pathology pathology) {
        super("Medical unit for the pathology '" + pathology + "' does not exist.");
    }
}
