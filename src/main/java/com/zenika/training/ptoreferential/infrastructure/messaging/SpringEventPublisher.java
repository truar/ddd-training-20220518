package com.zenika.training.ptoreferential.infrastructure.messaging;

import com.zenika.training.ptoreferential.domain.DomainEvent;
import com.zenika.training.ptoreferential.domain.EventPublisher;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

@Service
@Profile("spring")
public record SpringEventPublisher(ApplicationEventPublisher publisher) implements EventPublisher {

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}
