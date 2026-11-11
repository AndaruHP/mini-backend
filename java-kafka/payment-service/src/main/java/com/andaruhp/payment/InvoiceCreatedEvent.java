package com.andaruhp.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InvoiceCreatedEvent(
        UUID invoiceId,
        UUID orderId,
        String userId,
        String productName,
        String productTag,
        int productQty,
        String status
) {
}
