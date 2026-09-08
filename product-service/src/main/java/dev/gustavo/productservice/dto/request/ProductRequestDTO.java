package dev.gustavo.productservice.dto.request;

import dev.gustavo.productservice.model.Category;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        Category category,
        BigDecimal price,
        String description
) {
}
