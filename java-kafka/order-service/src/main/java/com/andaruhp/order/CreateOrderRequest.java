package com.andaruhp.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateOrderRequest(
        @NotBlank String userId,
        @NotBlank String productName,
        @NotBlank String productTag,
        @Min(1) int productQty
) {
}
