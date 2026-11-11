package com.andaruhp.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "invoices")
class InvoiceEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID orderId;
    private String userId;
    private String productName;
    private String productTag;
    private int productQty;
    private String status;
    private Instant createdAt;

    @Transient
    private boolean isNew = true;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    void markNotNew() {
        isNew = false;
    }

    static InvoiceEntity issue(OrderCreatedEvent event) {
        var invoice = new InvoiceEntity();
        invoice.id = UUID.randomUUID();
        invoice.orderId = event.orderId();
        invoice.userId = event.userId();
        invoice.productName = event.productName();
        invoice.productTag = event.productTag();
        invoice.productQty = event.productQty();
        invoice.status = "ISSUED";
        invoice.createdAt = Instant.now();
        return invoice;
    }

    InvoiceCreatedEvent event() {
        return new InvoiceCreatedEvent(id, orderId, userId, productName, productTag, productQty, status);
    }

    InvoiceResponse response() {
        return new InvoiceResponse(id, orderId, userId, productName, productTag, productQty, status);
    }
}
