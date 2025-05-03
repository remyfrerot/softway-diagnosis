package com.softway.diagnosis.application.port.input.medicalunit;

import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;

public interface FindMedicalUnitsUseCase {

    Collection<MedicalUnit> findByPathologies(Set<Pathology> pathologies) throws NoSuchMedicalUnitForThisPathologyException;
}
