package com.andaruhp.order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class InvoiceListener {

    static final String TOPIC = "invoice.created";

    private final OrderService orders;

    InvoiceListener(OrderService orders) {
        this.orders = orders;
    }

    @KafkaListener(topics = TOPIC)
    public void onInvoice(InvoiceCreatedEvent event) {
        orders.markInvoiced(event.orderId());
    }
}
