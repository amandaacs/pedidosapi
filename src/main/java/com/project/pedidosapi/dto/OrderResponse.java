package com.project.pedidosapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    Long customerId,
    String customerName,
    String status,
    LocalDateTime createdAt,
    List<OrderItemResponse> items,
    List<PaymentResponse> payments
) {}
