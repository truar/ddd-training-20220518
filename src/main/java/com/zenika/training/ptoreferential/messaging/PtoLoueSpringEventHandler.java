package com.zenika.training.ptoreferential.messaging;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PtoLoue;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Profile("spring")
public class PtoLoueSpringEventHandler {

    // private ApplicationService service;

    @EventListener
    public void handlePtoLoue(PtoLoue event) {
        // service.todo(event.attribute...);
        System.out.println("event = " + event);
    }
}
