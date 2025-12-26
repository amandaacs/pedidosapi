package com.project.pedidosapi.dto;

public record ProductResponse(
        Long id,
        String name,
        String category,
        Integer priceCents,
        Boolean active
) {}
