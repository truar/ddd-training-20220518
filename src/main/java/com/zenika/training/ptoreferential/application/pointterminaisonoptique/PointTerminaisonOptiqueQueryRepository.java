package com.zenika.training.ptoreferential.application.pointterminaisonoptique;

import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptique;
import org.springframework.data.domain.Page;

public interface PointTerminaisonOptiqueQueryRepository {

    Page<PointTerminaisonOptique> findByNroCode();
}
