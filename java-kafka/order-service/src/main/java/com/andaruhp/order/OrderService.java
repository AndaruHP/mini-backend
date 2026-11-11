package com.andaruhp.order;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
class OrderService {

    private final OrderRepository orders;
    private final ApplicationEventPublisher events;

    OrderService(OrderRepository orders, org.springframework.context.ApplicationEventPublisher events) {
        this.orders = orders;
        this.events = events;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest request) {
        var saved = orders.save(OrderEntity.create(request));
        events.publishEvent(saved.event());
        return saved.response();
    }

    @Transactional
    public void markInvoiced(UUID orderId) {
        orders.findById(orderId).ifPresent(order -> {
            order.markInvoiced();
            orders.save(order);
        });
    }

    public List<OrderResponse> list() {
        return orders.findAll().stream().map(OrderEntity::response).toList();
    }
}
