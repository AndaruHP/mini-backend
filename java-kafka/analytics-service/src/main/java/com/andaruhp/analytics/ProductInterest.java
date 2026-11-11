package com.andaruhp.analytics;

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
@Table(name = "product_interest")
class ProductInterest implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID invoiceId;
    private String userId;
    private String productTag;
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

    static ProductInterest from(InvoiceCreatedEvent event) {
        var row = new ProductInterest();
        row.id = UUID.randomUUID();
        row.invoiceId = event.invoiceId();
        row.userId = event.userId();
        row.productTag = event.productTag();
        row.createdAt = Instant.now();
        return row;
    }

    UUID invoiceId() {
        return invoiceId;
    }

    AnalyticsResponse response() {
        return new AnalyticsResponse(userId, productTag);
    }
}
