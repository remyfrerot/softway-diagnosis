package com.softway.diagnosis.application.port.input.pathology;

import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;

public interface DiagnosePathologiesUseCase {

    Collection<Pathology> diagnosePathologiesByHealthIndex(HealthIndex healthIndex) throws PathologyNotFoundException;
}
