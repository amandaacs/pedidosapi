package com.project.pedidosapi.controller;

import com.project.pedidosapi.dto.CreateOrderRequest;
import com.project.pedidosapi.dto.OrderResponse;
import com.project.pedidosapi.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    public OrderResponse get(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

}
