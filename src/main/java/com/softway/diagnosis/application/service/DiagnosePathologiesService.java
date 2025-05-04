package com.softway.diagnosis.application.service;

import com.softway.diagnosis.application.port.input.pathology.DiagnosePathologiesUseCase;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;
import java.util.stream.Collectors;

public class DiagnosePathologiesService implements DiagnosePathologiesUseCase {

    private final PathologyRepository pathologyRepository;

    public DiagnosePathologiesService(final PathologyRepository pathologyRepository) {
        this.pathologyRepository = pathologyRepository;
    }

    @Override
    public Collection<Pathology> diagnosePathologiesByHealthIndex(final HealthIndex healthIndex) throws PathologyNotFoundException {
        final Collection<Pathology> diagnosedPathologies = pathologyRepository.findAll().stream()
                .filter(pathology -> healthIndex.isMultipleOf(pathology.baseHealthIndex()))
                .collect(Collectors.toUnmodifiableSet());
        if (diagnosedPathologies.isEmpty()) {
            throw new PathologyNotFoundException(healthIndex);
        }
        return diagnosedPathologies;
    }
}
