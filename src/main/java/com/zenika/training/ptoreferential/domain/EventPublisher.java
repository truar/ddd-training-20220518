package com.zenika.training.ptoreferential.domain;

public interface EventPublisher {
    void publish(DomainEvent event);
}
