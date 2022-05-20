package com.zenika.training.ptoreferential.domain.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.DomainEvent;

import java.time.LocalDateTime;

public record PtoRestitue(PointTerminaisonOptiqueId id, String ptoOiId, String idContrat, LocalDateTime dateRestitution) implements DomainEvent {
}
