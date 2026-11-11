package com.andaruhp.payment;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class OrderListener {

    static final String TOPIC = "order.created";

    private final PaymentService payments;

    OrderListener(PaymentService payments) {
        this.payments = payments;
    }

    @KafkaListener(topics = TOPIC)
    public void onOrder(OrderCreatedEvent event) {
        payments.issue(event);
    }
}
