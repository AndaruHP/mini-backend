package com.andaruhp.payment;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvoiceEntityTest {

    @Test
    void invoiceCopiesTheOrderAndIsIssued() {
        var orderId = UUID.randomUUID();
        var event = new OrderCreatedEvent(orderId, "user-1", "Shoes", "running", 2);

        var published = InvoiceEntity.issue(event).event();

        assertNotNull(published.invoiceId());
        assertEquals(orderId, published.orderId());
        assertEquals("user-1", published.userId());
        assertEquals("Shoes", published.productName());
        assertEquals("running", published.productTag());
        assertEquals(2, published.productQty());
        assertEquals("ISSUED", published.status());
    }
}
