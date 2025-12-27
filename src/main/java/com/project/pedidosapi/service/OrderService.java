package com.project.pedidosapi.service;

import com.project.pedidosapi.dto.CreateOrderRequest;
import com.project.pedidosapi.dto.OrderItemResponse;
import com.project.pedidosapi.dto.OrderResponse;
import com.project.pedidosapi.dto.PaymentResponse;
import com.project.pedidosapi.entity.Order;
import com.project.pedidosapi.entity.OrderItem;
import com.project.pedidosapi.repository.CustomerRepository;
import com.project.pedidosapi.repository.OrderRepository;
import com.project.pedidosapi.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        var order = new Order();
        order.setCustomer(customer);
        order.setStatus("PENDENTE");
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items =  request.items().stream()
                .map(i -> {
                    var product = productRepository.findById(i.productId())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                    var item = new OrderItem();
                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(i.quantity());
                    item.setUnitPriceCents(product.getPriceCents());
                    return item;
                }).toList();

        order.setItems(items);
        orderRepository.save(order);
        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getName(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getItems().stream().map(i -> new OrderItemResponse(
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getUnitPriceCents()
                )).toList(),
                order.getPayments().stream().map(p -> new PaymentResponse(
                        p.getId(),
                        p.getMethod(),
                        p.getAmountCents(),
                        p.getPaidAt()
                )).toList()
        );
    }

}
