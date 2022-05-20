package com.zenika.training.ptoreferential.domain.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.DomainEvent;

import java.time.LocalDateTime;

public record PtoLoue(PointTerminaisonOptiqueId id, String ptoOiId, LocalDateTime dateLocation, LocalDateTime dateCreation) implements DomainEvent {
}


