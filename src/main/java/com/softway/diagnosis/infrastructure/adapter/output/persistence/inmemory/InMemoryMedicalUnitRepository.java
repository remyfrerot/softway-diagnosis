package com.softway.diagnosis.infrastructure.adapter.output.persistence.inmemory;

import com.softway.diagnosis.application.port.output.persistence.MedicalUnitRepository;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.Pathology;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the {@link MedicalUnitRepository} interface.
 * This implementation provides a temporary, non-persistent storage for {@link MedicalUnit} entities
 * and their associations with the {@link Pathology} instances they are capable of treating.
 * <p>
 * Designed primarily for use in scenarios where a dedicated database or persistent storage solution
 * is not required or practical (e.g., testing or prototyping environments).
 * <p>
 * The storage is backed by a {@link HashMap}, where each {@link Pathology} serves as a key, and its
 * corresponding {@link MedicalUnit} is the value. This allows for efficient lookups of medical units
 * based on treated pathologies.
 */
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
