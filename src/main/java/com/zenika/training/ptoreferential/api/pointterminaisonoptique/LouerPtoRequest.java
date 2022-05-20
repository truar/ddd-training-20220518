package com.zenika.training.ptoreferential.api.pointterminaisonoptique;

import java.time.LocalDateTime;

public record LouerPtoRequest(String ptoId, LocalDateTime dateLocation, String idContrat) {
}
