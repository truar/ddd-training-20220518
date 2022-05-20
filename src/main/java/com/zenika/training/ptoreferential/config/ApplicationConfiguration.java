package com.zenika.training.ptoreferential.config;

import com.zenika.training.ptoreferential.application.PointTerminaisonOptiqueService;
import com.zenika.training.ptoreferential.domain.EventPublisher;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public PointTerminaisonOptiqueService pointTerminaisonOptiqueService(PointTerminaisonOptiqueRepository repository,
                                                                         Clock clock,
                                                                         EventPublisher eventPublisher) {
        return new PointTerminaisonOptiqueService(repository, clock, eventPublisher);
    }

}
