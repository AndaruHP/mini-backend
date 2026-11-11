package com.andaruhp.order;

import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        String userId,
        String productName,
        String productTag,
        int productQty,
        String status
) {
}
