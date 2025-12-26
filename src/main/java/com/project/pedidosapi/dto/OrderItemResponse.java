package com.project.pedidosapi.dto;

public record OrderItemResponse(
    Long productId,
    String productName,
    Integer quantity,
    Integer unitPriceCents
) {}
