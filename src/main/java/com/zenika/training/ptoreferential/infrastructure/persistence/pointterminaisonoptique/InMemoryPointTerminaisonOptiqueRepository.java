package com.zenika.training.ptoreferential.infrastructure.persistence.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueId;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("inmemory")
public class InMemoryPointTerminaisonOptiqueRepository implements PointTerminaisonOptiqueRepository {

    private Map<PointTerminaisonOptiqueId, PointTerminaisonOptique> db = new HashMap<>();

    @Override
    public Optional<PointTerminaisonOptique> byId(PointTerminaisonOptiqueId id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void save(PointTerminaisonOptique pto) {
        db.put(pto.getId(), pto);
    }

    @Override
    public PointTerminaisonOptiqueId nextIdentity() {
        return new PointTerminaisonOptiqueId(UUID.randomUUID().toString());
    }
}
