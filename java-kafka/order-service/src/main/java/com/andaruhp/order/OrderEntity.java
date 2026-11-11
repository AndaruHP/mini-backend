package com.andaruhp.order;

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
@Table(name = "orders")
class OrderEntity implements Persistable<UUID> {

    @Id
    private UUID id;
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

    static OrderEntity create(CreateOrderRequest request) {
        var order = new OrderEntity();
        order.id = UUID.randomUUID();
        order.userId = request.userId();
        order.productName = request.productName();
        order.productTag = request.productTag();
        order.productQty = request.productQty();
        order.status = "CREATED";
        order.createdAt = Instant.now();
        return order;
    }

    // to know does this service also consume or not
    void markInvoiced() {
        status = "INVOICED";
    }

    OrderCreatedEvent event() {
        return new OrderCreatedEvent(id, userId, productName, productTag, productQty);
    }

    OrderResponse response() {
        return new OrderResponse(id, userId, productName, productTag, productQty, status);
    }
}
