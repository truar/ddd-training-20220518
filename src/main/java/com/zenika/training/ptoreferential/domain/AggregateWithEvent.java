package com.zenika.training.ptoreferential.domain;

public record AggregateWithEvent<A, E extends DomainEvent>(A aggregate, E event){
}
