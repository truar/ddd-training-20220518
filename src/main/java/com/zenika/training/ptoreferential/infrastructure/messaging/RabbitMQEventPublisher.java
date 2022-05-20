package com.zenika.training.ptoreferential.infrastructure.messaging;

import com.zenika.training.ptoreferential.domain.DomainEvent;
import com.zenika.training.ptoreferential.domain.EventPublisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public record RabbitMQEventPublisher(RabbitTemplate template) implements EventPublisher {

    @Override
    public void publish(DomainEvent event) {
        template.convertAndSend("ptoLoue", event);
    }
}
