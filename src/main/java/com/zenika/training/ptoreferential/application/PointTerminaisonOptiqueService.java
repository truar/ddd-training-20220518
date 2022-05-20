package com.zenika.training.ptoreferential.application;

import com.zenika.training.ptoreferential.domain.EventPublisher;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.ChangementContratNonValideException;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueId;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueRepository;

import java.time.Clock;
import java.time.LocalDateTime;

public record PointTerminaisonOptiqueService(PointTerminaisonOptiqueRepository repository,
                                             Clock clock,
                                             EventPublisher publisher) {

    public PointTerminaisonOptiqueId louerUnPto(String ptoOiId, LocalDateTime dateLocation, String idContrat) {
        var id = repository.nextIdentity();
        var dateCreation = LocalDateTime.now(clock);
        var result = PointTerminaisonOptique.loue(id, ptoOiId, dateLocation, dateCreation, idContrat);
        repository.save(result.aggregate());
        publisher.publish(result.event());
        return id;
    }

    public void mettreEnServiceUnPto(PointTerminaisonOptiqueId id, LocalDateTime dateMiseEnService, String idContrat) throws ChangementContratNonValideException {
        var pointTerminaisonOptique = repository.byId(id).orElseThrow();
        pointTerminaisonOptique.mettreEnService(dateMiseEnService, idContrat);
        repository.save(pointTerminaisonOptique);
    }

    public PointTerminaisonOptique getPto(PointTerminaisonOptiqueId id) {
        return repository.byId(id).orElseThrow();
    }
}
