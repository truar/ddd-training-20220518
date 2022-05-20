package com.zenika.training.ptoreferential.api.pointterminaisonoptique;

import java.time.LocalDateTime;

public record MettreEnServiceRequest(LocalDateTime dateMiseEnService, String idContrat) {
}
