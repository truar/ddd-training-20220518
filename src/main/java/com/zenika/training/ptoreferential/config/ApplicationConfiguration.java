package com.zenika.training.ptoreferential.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zenika.training.ptoreferential.application.PointTerminaisonOptiqueService;
import com.zenika.training.ptoreferential.domain.EventPublisher;
import com.zenika.training.ptoreferential.domain.pointterminaisonoptique.PointTerminaisonOptiqueRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Bean
    public Queue ptoLoueQueue() {
        return new Queue("ptoLoue", false);
    }

    @Bean
    public Queue crMadRecuQueue() {
        return new Queue("crMadRecu", false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    /*
    To allow RabbitTemplate.convertAndSend to transform the object OrderPlaced in JSON,
    and fetching the object in the listener as a Map<String, Object>
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
