package com.andaruhp.payment;

import java.util.UUID;

public record InvoiceResponse(
        UUID invoiceId,
        UUID orderId,
        String userId,
        String productName,
        String productTag,
        int productQty,
        String status
) {
}
