package com.zenika.training.ptoreferential.infrastructure;

import com.zenika.training.ptoreferential.domain.DomainEvent;
import com.zenika.training.ptoreferential.domain.EventPublisher;

import java.util.ArrayDeque;
import java.util.Queue;

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
