package com.zenika.training.ptoreferential.messaging;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PtoLoue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public class PtoLoueRabbitEventHandler {

    // private ApplicationService service;

    @RabbitListener(queues = "ptoLoue")
    public void handlePtoLoue(PtoLoue event) {
        // service.todo(event.attribute...);
        System.out.println("RabbitMQ");
        System.out.println("event = " + event);
    }
}
