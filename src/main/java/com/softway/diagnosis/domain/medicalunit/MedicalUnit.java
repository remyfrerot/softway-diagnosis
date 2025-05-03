package com.softway.diagnosis.domain.medicalunit;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Set;

public interface MedicalUnit {

    String nameI18nKey();

    Set<Pathology> treatedPathologies();
}
