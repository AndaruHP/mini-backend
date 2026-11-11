package com.andaruhp.analytics;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductInterestTest {

    @Test
    void keepsUserAndTagAndIgnoresTheRestOfTheInvoice() {
        var invoiceId = UUID.randomUUID();
        var event = new InvoiceCreatedEvent(
                invoiceId, UUID.randomUUID(), "user-1", "Shoes", "running", 2, "ISSUED");

        var row = ProductInterest.from(event);

        assertEquals(invoiceId, row.invoiceId());
        assertEquals(new AnalyticsResponse("user-1", "running"), row.response());
    }
}
