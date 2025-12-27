package com.project.pedidosapi.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        LocalDateTime timestamp,
        String path
) {}
