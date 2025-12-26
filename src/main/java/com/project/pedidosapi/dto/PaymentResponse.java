package com.project.pedidosapi.dto;

import java.time.LocalDateTime;

public record PaymentResponse(
   Long id,
   String method,
   Integer amountCents,
   LocalDateTime paidAt
) {}
