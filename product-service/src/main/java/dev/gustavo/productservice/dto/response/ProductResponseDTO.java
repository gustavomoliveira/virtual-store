package dev.gustavo.productservice.dto.response;

import dev.gustavo.productservice.model.Category;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        Category category,
        BigDecimal price,
        String description
) {
}
