package com.project.pedidosapi.dto;

public record CreateOrderItemRequest(
   Long productId,
   Integer quantity
) {}
