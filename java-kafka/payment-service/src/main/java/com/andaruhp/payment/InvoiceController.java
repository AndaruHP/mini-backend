package com.andaruhp.payment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoices")
class InvoiceController {

    private final PaymentService payments;

    InvoiceController(PaymentService payments) {
        this.payments = payments;
    }

    @GetMapping
    List<InvoiceResponse> list() {
        return payments.list();
    }
}
