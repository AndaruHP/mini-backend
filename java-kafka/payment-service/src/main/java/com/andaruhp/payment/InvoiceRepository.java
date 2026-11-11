package com.andaruhp.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface InvoiceRepository extends JpaRepository<InvoiceEntity, UUID> {

    boolean existsByOrderId(UUID orderId);
}
