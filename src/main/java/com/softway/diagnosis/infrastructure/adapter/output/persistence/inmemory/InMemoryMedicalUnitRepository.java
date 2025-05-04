package com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory;

import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryMedicalUnitRepository implements MedicalUnitRepository {

    private final Map<Pathology, MedicalUnit> medicalUnitByPathology = new HashMap<>();

    @Override
    public void save(final MedicalUnit medicalUnit) {
        final Map<Pathology, MedicalUnit> medicalUnitByTreatedPathology = medicalUnit.treatedPathologies().stream()
                .collect(Collectors.toMap(Function.identity(), _ -> medicalUnit));
        medicalUnitByPathology.putAll(medicalUnitByTreatedPathology);
    }

    @Override
    public Collection<MedicalUnit> findByPathologies(final Set<Pathology> pathologies) {
        return pathologies.stream()
                .map(medicalUnitByPathology::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableSet());
    }
}
