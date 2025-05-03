package com.softway.diagnosis.application.port.output.persistence;

import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.Collection;

public interface PathologyRepository {

    void save(Pathology pathology);

    Collection<Pathology> findAll();
}
