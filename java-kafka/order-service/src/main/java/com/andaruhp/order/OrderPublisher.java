package com.andaruhp.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
class OrderPublisher {

    static final String TOPIC = "order.created";

    private static final Logger log = LoggerFactory.getLogger(OrderPublisher.class);

    private final KafkaTemplate<String, OrderCreatedEvent> kafka;

    OrderPublisher(KafkaTemplate<String, OrderCreatedEvent> kafka) {
        this.kafka = kafka;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(OrderCreatedEvent event) {
        kafka.send(TOPIC, event.orderId().toString(), event).whenComplete((result, error) -> {
            if (error != null) {
                log.error("failed to publish {} {}", TOPIC, event.orderId(), error);
            }
        });
    }
}
