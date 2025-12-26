package com.project.pedidosapi.dto;

public record CreatePaymentRequest(
   Long orderId,
   String method,
   Integer amountCents
) {}
