package com.andaruhp.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
class InvoicePublisher {

    static final String TOPIC = "invoice.created";

    private static final Logger log = LoggerFactory.getLogger(InvoicePublisher.class);

    private final KafkaTemplate<String, InvoiceCreatedEvent> kafka;

    InvoicePublisher(KafkaTemplate<String, InvoiceCreatedEvent> kafka) {
        this.kafka = kafka;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(InvoiceCreatedEvent event) {
        kafka.send(TOPIC, event.orderId().toString(), event).whenComplete((result, error) -> {
            if (error != null) {
                log.error("failed to publish {} {}", TOPIC, event.invoiceId(), error);
            }
        });
    }
}
