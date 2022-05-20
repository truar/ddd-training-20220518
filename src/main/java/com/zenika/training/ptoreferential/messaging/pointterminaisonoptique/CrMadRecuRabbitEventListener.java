package com.zenika.training.ptoreferential.messaging.pointterminaisonoptique;

import com.zenika.training.ptoreferential.application.PointTerminaisonOptiqueService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public record CrMadRecuRabbitEventListener(PointTerminaisonOptiqueService service) {

    @RabbitListener(queues = "crMadRecu")
    public void handle(CrMadDto event) {
        service.louerUnPto(event.ptoId(), event.dateLocation(), event.idContrat());
    }
}
