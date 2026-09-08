package dev.gustavo.orderservice.dto.request;

import dev.gustavo.orderservice.model.ItemOrder;

import java.util.List;

public record OrderRequestDTO(
        List<ItemOrder> itens
) {
}
