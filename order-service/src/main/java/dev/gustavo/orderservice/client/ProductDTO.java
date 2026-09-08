package dev.gustavo.orderservice.client;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price
) {
}
