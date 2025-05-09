package com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory;

import com.softway.diagnosis.application.port.output.persistence.PathologyRepository;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * In-memory implementation of the {@link PathologyRepository} interface.
 * This implementation provides a volatile storage mechanism for managing {@link Pathology} entities.
 * <p>
 * It is designed for use in scenarios where permanent storage is not required,
 * such as testing or prototyping environments. The storage is backed by a {@link Collection},
 * providing support for adding and retrieving pathology data.
 */
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
