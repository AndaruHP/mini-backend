package com.andaruhp.payment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class PaymentService {

    private final InvoiceRepository invoices;
    private final ApplicationEventPublisher events;

    PaymentService(InvoiceRepository invoices, ApplicationEventPublisher events) {
        this.invoices = invoices;
        this.events = events;
    }

    @Transactional
    public void issue(OrderCreatedEvent event) {
        if (invoices.existsByOrderId(event.orderId())) {
            return;
        }
        var saved = invoices.save(InvoiceEntity.issue(event));
        events.publishEvent(saved.event());
    }

    public List<InvoiceResponse> list() {
        return invoices.findAll().stream().map(InvoiceEntity::response).toList();
    }
}
