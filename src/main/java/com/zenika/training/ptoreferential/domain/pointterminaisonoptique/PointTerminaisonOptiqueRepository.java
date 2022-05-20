package com.zenika.training.ptoreferential.domain.pointterminaisonoptique;

import java.util.Optional;

public interface PointTerminaisonOptiqueRepository {

    Optional<PointTerminaisonOptique> byId(PointTerminaisonOptiqueId id);

    void save(PointTerminaisonOptique pto);

    PointTerminaisonOptiqueId nextIdentity();
}
