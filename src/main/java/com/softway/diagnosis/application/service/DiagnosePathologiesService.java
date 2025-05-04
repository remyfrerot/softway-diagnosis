package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.pathology.DiagnosePathologiesUseCase;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.List;

public class DiagnosePathologiesService implements DiagnosePathologiesUseCase {

    private final PathologyRepository pathologyRepository;

    public DiagnosePathologiesService(final PathologyRepository pathologyRepository) {
        this.pathologyRepository = pathologyRepository;
    }

    @Override
    public Collection<Pathology> diagnosePathologiesByHealthIndex(final HealthIndex healthIndex) throws PathologyNotFoundException {
        return List.of();
    }
}
