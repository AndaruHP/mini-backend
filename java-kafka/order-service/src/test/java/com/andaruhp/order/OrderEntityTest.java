package com.andaruhp.order;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderEntityTest {

    @Test
    void newOrderIsCreatedAndCopiedOntoTheEvent() {
        var request = new CreateOrderRequest("user-1", "Shoes", "running", 2);

        var event = OrderEntity.create(request).event();

        assertEquals("user-1", event.userId());
        assertEquals("Shoes", event.productName());
        assertEquals("running", event.productTag());
        assertEquals(2, event.productQty());
        assertEquals("CREATED", OrderEntity.create(request).response().status());
    }

    @Test
    void invoiceMovesTheOrderToInvoiced() {
        var order = OrderEntity.create(new CreateOrderRequest("user-1", "Shoes", "running", 2));

        order.markInvoiced();

        assertEquals("INVOICED", order.response().status());
    }
}
