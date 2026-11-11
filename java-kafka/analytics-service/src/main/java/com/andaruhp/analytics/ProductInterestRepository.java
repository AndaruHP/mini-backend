package com.andaruhp.analytics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ProductInterestRepository extends JpaRepository<ProductInterest, UUID> {

    boolean existsByInvoiceId(UUID invoiceId);
}
