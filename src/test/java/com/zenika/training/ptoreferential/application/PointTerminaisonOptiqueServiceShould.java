package com.zenika.training.ptoreferential.application;

import com.zenika.training.ptoreferential.domain.DomainEvent;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.*;
import com.zenika.training.ptoreferential.infrastructure.InMemoryQueueEventPublisher;
import com.zenika.training.ptoreferential.infrastructure.persistence.pointterminaisonoptique.InMemoryPointTerminaisonOptiqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class PointTerminaisonOptiqueServiceShould {

    private InMemoryPointTerminaisonOptiqueRepository ptoRepository;
    private InMemoryQueueEventPublisher publisher;
    private PointTerminaisonOptiqueService service;
    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(LocalDate.of(2022, 5, 19).atStartOfDay(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC);
        ptoRepository = new InMemoryPointTerminaisonOptiqueRepository();
        publisher = new InMemoryQueueEventPublisher();
        service = new PointTerminaisonOptiqueService(ptoRepository, clock, publisher);
    }

    @Test
    void louer_un_pto() {
        String ptoOiId = "ptoOiId";
        LocalDateTime dateLocation = LocalDateTime.now();
        String idContrat = "idContrat";
        var dateCreation = LocalDateTime.now(clock);

        PointTerminaisonOptiqueId id = service.louerUnPto(ptoOiId, dateLocation, idContrat);

        var pto = ptoRepository.byId(id).orElseThrow();
        assertThat(pto).isNotNull();
        assertThat(pto.getId()).isEqualTo(id);
        assertThat(pto.getPtoOiId()).isEqualTo(ptoOiId);
        assertThat(pto.getDateLocation()).isEqualTo(dateLocation);
        assertThat(pto.getDateCreation()).isEqualTo(dateCreation);
        assertThat(pto.getIdContrat()).isEqualTo(idContrat);
        assertThat(pto.getStatus()).isEqualTo(PtoStatus.LOUE);

        DomainEvent domainEvent = publisher.readHead();
        // assertDomainEvent
    }

    @Test
    void mettre_en_service_un_pto() throws ChangementContratNonValideException {
        ptoRepository.save(unPtoLoue());
        LocalDateTime dateMiseEnService = LocalDateTime.now();
        String idContrat = "idContrat";
        PointTerminaisonOptiqueId id = new PointTerminaisonOptiqueId("id");

        service.mettreEnServiceUnPto(id, dateMiseEnService, idContrat);

        var pto = ptoRepository.byId(id).orElseThrow();
        assertThat(pto).isNotNull();
    }

    private PointTerminaisonOptique unPtoLoue() {
        var id = new PointTerminaisonOptiqueId("id");
        var ptoOiId = "ptoOiId";
        var dateLocation = LocalDateTime.now();
        var dateCreation = LocalDateTime.now();
        var idContrat = "idContrat";
        return PointTerminaisonOptique.loue(id, ptoOiId, dateLocation, dateCreation, idContrat).aggregate();
    }
}
