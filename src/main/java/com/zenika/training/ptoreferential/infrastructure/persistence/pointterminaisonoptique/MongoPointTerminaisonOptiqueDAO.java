package com.zenika.training.ptoreferential.infrastructure.persistence.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPointTerminaisonOptiqueDAO extends MongoRepository<PointTerminaisonOptique, PointTerminaisonOptiqueId> {
}
