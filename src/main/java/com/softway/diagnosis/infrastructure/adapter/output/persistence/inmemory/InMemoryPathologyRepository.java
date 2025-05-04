package com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory;

import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class InMemoryPathologyRepository implements PathologyRepository {

    private final Collection<Pathology> pathologies = new ArrayList<>();

    @Override
    public void save(final Pathology pathology) {
        pathologies.add(pathology);
    }

    @Override
    public Collection<Pathology> findAll() {
        return Collections.unmodifiableCollection(pathologies);
    }
}
