package com.andaruhp.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderCreatedEvent(
        UUID orderId,
        String userId,
        String productName,
        String productTag,
        int productQty
) {
}
