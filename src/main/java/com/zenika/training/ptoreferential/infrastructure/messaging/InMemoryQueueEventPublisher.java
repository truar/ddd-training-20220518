package com.zenika.training.ptoreferential.infrastructure.messaging;

import com.zenika.training.ptoreferential.domain.DomainEvent;
import com.zenika.training.ptoreferential.domain.EventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

@Service
@Profile("inmemory")
public class InMemoryQueueEventPublisher implements EventPublisher {

    private Queue<DomainEvent> queue = new ArrayDeque<>();

    @Override
    public void publish(DomainEvent event) {
        queue.add(event);
    }

    public DomainEvent readHead() {
        return queue.poll();
    }

}
