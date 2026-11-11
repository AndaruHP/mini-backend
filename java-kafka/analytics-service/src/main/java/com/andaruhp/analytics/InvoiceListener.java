package com.andaruhp.analytics;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class InvoiceListener {

    static final String TOPIC = "invoice.created";

    private final AnalyticsService analytics;

    InvoiceListener(AnalyticsService analytics) {
        this.analytics = analytics;
    }

    @KafkaListener(topics = TOPIC)
    public void onInvoice(InvoiceCreatedEvent event) {
        analytics.record(event);
    }
}
