package com.softway.diagnosis.application.port.output.persistence;

import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.Set;

public interface MedicalUnitRepository {

    void save(MedicalUnit medicalUnit);

    Collection<MedicalUnit> findByPathologies(Set<Pathology> pathologies);
}
