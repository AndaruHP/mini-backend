package com.andaruhp.analytics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class AnalyticsService {

    private final ProductInterestRepository interests;

    AnalyticsService(ProductInterestRepository interests) {
        this.interests = interests;
    }

    @Transactional
    public void record(InvoiceCreatedEvent event) {
        if (interests.existsByInvoiceId(event.invoiceId())) {
            return;
        }
        interests.save(ProductInterest.from(event));
    }

    public List<AnalyticsResponse> list() {
        return interests.findAll().stream().map(ProductInterest::response).toList();
    }
}
