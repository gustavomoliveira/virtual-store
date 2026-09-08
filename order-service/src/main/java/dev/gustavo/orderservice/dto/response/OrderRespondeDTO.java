package dev.gustavo.orderservice.dto.response;

import dev.gustavo.orderservice.model.ItemOrder;
import dev.gustavo.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderRespondeDTO(
        Long id,
        List<ItemOrder> itens,
        BigDecimal total,
        OrderStatus status
) {
}
