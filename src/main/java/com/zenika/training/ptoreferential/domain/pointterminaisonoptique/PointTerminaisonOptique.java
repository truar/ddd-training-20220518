package com.zenika.training.ptoreferential.domain.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.AggregateWithEvent;
import com.zenika.training.ptoreferential.domain.DomainEvent;

import java.time.LocalDateTime;

public class PointTerminaisonOptique {
    private final PointTerminaisonOptiqueId id;
    private final String ptoOiId;
    private final LocalDateTime dateLocation;
    private final LocalDateTime dateCreation;
    private PtoStatus status;
    private LocalDateTime dateMiseEnService;
    private LocalDateTime dateResiliation;
    private LocalDateTime dateRestitution;
    private String idContrat;

//    private List<DomainEvent> events;

    private PointTerminaisonOptique(PointTerminaisonOptiqueId id, String ptoOiId, LocalDateTime dateLocation, LocalDateTime dateCreation, String idContrat) {
        this.id = id;
        this.ptoOiId = ptoOiId;
        this.dateLocation = dateLocation;
        this.dateCreation = dateCreation;
        this.idContrat = idContrat;
        this.status = PtoStatus.LOUE;
    }

    public static AggregateWithEvent<PointTerminaisonOptique, DomainEvent> loue(PointTerminaisonOptiqueId id, String ptoOiId, LocalDateTime dateLocation, LocalDateTime dateCreation, String idContrat) {
        var pto = new PointTerminaisonOptique(id, ptoOiId, dateLocation, dateCreation, idContrat);
        // pourquoi pas
//        pto.events.add(new DomainEvent());
        // Ne pas faire
//        DomainEventPublisher.getInstance().publish(new DomainEvent());
        return new AggregateWithEvent<>(pto, new PtoLoue(id, ptoOiId, dateLocation, dateCreation));
    }

    public void mettreEnService(LocalDateTime dateMiseEnService, String idContrat) throws ChangementContratNonValideException {
        if (status == PtoStatus.LOUE && !this.idContrat.equals(idContrat)) {
            throw new ChangementContratNonValideException();
        }
        this.status = PtoStatus.MIS_EN_SERVICE;
        this.dateMiseEnService = dateMiseEnService;
        this.idContrat = idContrat;
    }

    public void resilier(LocalDateTime dateResiliation) {
        this.status = PtoStatus.RESILIE;
        this.dateResiliation = dateResiliation;
    }

    public PtoRestitue restituer(LocalDateTime dateRestitution) {
        this.status = PtoStatus.RESTITUE;
        this.dateRestitution = dateRestitution;
        return new PtoRestitue(id, ptoOiId, idContrat, dateRestitution);
    }

    public PointTerminaisonOptiqueId getId() {
        return id;
    }

    public String getPtoOiId() {
        return ptoOiId;
    }

    public LocalDateTime getDateLocation() {
        return dateLocation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public PtoStatus getStatus() {
        return status;
    }

    public LocalDateTime getDateMiseEnService() {
        return dateMiseEnService;
    }

    public LocalDateTime getDateResiliation() {
        return dateResiliation;
    }

    public LocalDateTime getDateRestitution() {
        return dateRestitution;
    }

    public String getIdContrat() {
        return idContrat;
    }
}
