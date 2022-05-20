package com.zenika.training.ptoreferential.infrastructure.persistence.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueId;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("mongo")
public class MongoPointTerminaisonOptiqueRepository implements PointTerminaisonOptiqueRepository {

    private final MongoPointTerminaisonOptiqueDAO dao;

    public MongoPointTerminaisonOptiqueRepository(MongoPointTerminaisonOptiqueDAO dao) {
        this.dao = dao;
    }

    @Override
    public Optional<PointTerminaisonOptique> byId(PointTerminaisonOptiqueId id) {
        return dao.findById(id);
    }

    @Override
    public void save(PointTerminaisonOptique pto) {
        dao.save(pto);
    }

    @Override
    public PointTerminaisonOptiqueId nextIdentity() {
        return new PointTerminaisonOptiqueId(UUID.randomUUID().toString());
    }
}
